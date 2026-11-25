package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechaninisms.Intake;
import org.firstinspires.ftc.teamcode.mechaninisms.Transfer;
import org.firstinspires.ftc.teamcode.mechaninisms.shooter.Outtake;
import org.firstinspires.ftc.teamcode.mechaninisms.shooter.Rotatinator;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;
import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;
import org.opencv.core.DMatch;

public class Robot {
    public static Telemetry telemetry;
    public RobotConstants constants = new RobotConstants();
    public AllianceColor allianceColor = AllianceColor.None;
    public boolean startsInCorner = false;

    public Pose startPose;

    private static HardwareMap hardwareMap;
    public static Drivetrain drive;
    public static Intake intake;
    public static Outtake outtake;
    public static Transfer transfer;
    public static Rotatinator rotatinator;
    public static GamepadController gamepadController;
    public Follower follower;

    public boolean isIntakeActive = false;
    public boolean isTransferActive = false;
    public boolean isOuttakeActive = false;

    public void RunMenu(GamepadController gamepadController) {

        if (gamepadController.setTeamToBlue()) {
            allianceColor = AllianceColor.Blue;
        } else if (gamepadController.setTeamToRed()) {
            allianceColor = AllianceColor.Red;
        }
        if (gamepadController.setStartPoseToCorner()) {
            startPose = RobotConstants.CORNER_POSE;
        } else if (gamepadController.setStartPoseToHive()) {
            startPose = RobotConstants.HIVE_POSE;
        } else if (gamepadController.setStartPoseToPark()) {
            startPose = RobotConstants.PARK_POSE;
        }
        //follower.setPose(startPose);
    }

    public void initRobot(HardwareMap hardwareMapRef) {
        hardwareMap = hardwareMapRef;
        follower = Constants.create(hardwareMap);
        gamepadController = new GamepadController(gamepad1, gamepad2);
        drive = new Drivetrain(hardwareMap);
        //intake = new Intake(hardwareMap, this);
        //outtake = new Outtake(hardwareMap,this);
        //transfer = new Transfer(hardwareMap, this);
        rotatinator = new Rotatinator(hardwareMap, follower);
    }

    public void runRobot() {
        follower.update();
        drive.runDrive(gamepadController);
        //intake.runIntake(gamepadController.toggleIntakeButton());
        //outtake.runOuttake(gamepadController.toggleOuttakeButton());
        //transfer.runTransfer(gamepadController.toggleTransferButton());
        rotatinator.rotateTurret(0, 0);
    }

}

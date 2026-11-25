package org.firstinspires.ftc.teamcode.opmodes.teleop;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechaninisms.shooter.Rotatinator;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;

@TeleOp(name = "Main TeleOP", group = "Linear OpMode")
public class DriveCode extends LinearOpMode {
    static final double TARGET_X = 0;
    static final double TARGET_Y = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        Follower follower;
        Rotatinator rotatinator;
        Drivetrain drive = new Drivetrain(hardwareMap);
        GamepadController gamepadController = new GamepadController(gamepad1, gamepad2);
        follower = Constants.create(hardwareMap);
        rotatinator = new Rotatinator(hardwareMap, follower);


        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();

        //-----------------------------------------------------------------
        // Start ↑ and Primary Loop ↓
        //-----------------------------------------------------------------

        while (opModeIsActive() && !isStopRequested()) {
            follower.update();  // must run before anything that uses the follower /fs -R
            drive.runRobotCentricDriveCode(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );
            rotatinator.rotateTurret(TARGET_X, TARGET_Y);
            telemetry.addData("Turret deg", rotatinator.getAngleDeg());
            telemetry.update();
        }

        drive.setPowers(0, 0, 0, 0);
    }
}


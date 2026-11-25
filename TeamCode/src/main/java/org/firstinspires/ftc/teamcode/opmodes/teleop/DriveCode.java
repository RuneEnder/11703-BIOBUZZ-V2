package org.firstinspires.ftc.teamcode.opmodes.teleop;

import static org.firstinspires.ftc.teamcode.Robot.drive;
import static org.firstinspires.ftc.teamcode.Robot.gamepadController;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "Main TeleOP", group = "Linear OpMode")
public class DriveCode extends LinearOpMode {

    public Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);

        while (!opModeIsActive()) {
            robot.RunMenu(gamepadController);
        }

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            robot.runRobot();
        }

        drive.setPowers(0, 0, 0, 0);
    }
}
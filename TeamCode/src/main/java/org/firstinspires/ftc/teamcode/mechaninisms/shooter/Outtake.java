package org.firstinspires.ftc.teamcode.mechaninisms.shooter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

public class Outtake {
    public final DcMotorEx outtakeMotor;
    private final Robot robot;

    public Outtake(HardwareMap hardwareMap, Robot robotRef) {
        robot = robotRef;

        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtake");
        outtakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void runOuttake(boolean toggleOuttake) {
        if (toggleOuttake) {
            robot.isOuttakeActive = !robot.isOuttakeActive;
        }
        if (robot.isOuttakeActive) {
            outtakeMotor.setPower(1);
        } else {
            outtakeMotor.setPower(0);
        }
    }
}

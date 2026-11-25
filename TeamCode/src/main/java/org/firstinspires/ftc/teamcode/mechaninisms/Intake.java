package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

public class Intake {
    public final DcMotorEx intakeMotor;
    private final Robot robot;

    public Intake(HardwareMap hardwareMap, Robot robotRef) {
        robot = robotRef;

        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void runIntake(boolean toggleIntake) {
        if (toggleIntake) {
            robot.isIntakeActive = !robot.isIntakeActive;
        }
        if (robot.isIntakeActive) {
            intakeMotor.setPower(1);
        } else {
            intakeMotor.setPower(0);
        }
    }

}

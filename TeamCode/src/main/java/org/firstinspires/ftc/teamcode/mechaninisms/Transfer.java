package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

public class Transfer {
    public final DcMotorEx transferMotor;
    private final Robot robot;

    public Transfer(HardwareMap hardwareMap, Robot robotRef) {
        robot = robotRef;

        transferMotor = hardwareMap.get(DcMotorEx.class, "transfer");
        transferMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void runTransfer(boolean toggleTransfer) {
        if (toggleTransfer) {
            robot.isTransferActive = !robot.isTransferActive;
        }
        if (robot.isTransferActive) {
            transferMotor.setPower(1);
        } else {
            transferMotor.setPower(0);
        }
    }
}

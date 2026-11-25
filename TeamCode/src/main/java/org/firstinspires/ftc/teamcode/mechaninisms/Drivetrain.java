package org.firstinspires.ftc.teamcode.mechaninisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;


public class Drivetrain {
    private HardwareMap hardwareMap;

    public final DcMotorEx leftFront, leftBack, rightBack, rightFront;

    public Drivetrain(HardwareMap hardwareMapRef) {
        hardwareMap = hardwareMapRef;

        leftFront = hardwareMap.get(DcMotorEx.class, "lf");
        rightFront = hardwareMap.get(DcMotorEx.class, "rf");
        leftBack = hardwareMap.get(DcMotorEx.class, "lr");
        rightBack = hardwareMap.get(DcMotorEx.class, "rr");


        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void runDrive(GamepadController gamepadController) {
        runRobotCentricDrive(
                gamepadController.strafe(),
                gamepadController.drive(),
                gamepadController.turn()
        );
    }


    public void runRobotCentricDrive(float strafe, float drive, float rotation) {

        // Calculate powers
        double frontLeftPower = drive - rotation - strafe;
        double frontRightPower = drive + rotation + strafe;
        double backLeftPower = drive - rotation + strafe;
        double backRightPower = drive + rotation - strafe;

        // Send calculated power to wheels
        setPowers(frontLeftPower , frontRightPower, backLeftPower,backRightPower );
    }
    public void setPowers(double lf, double rf, double lb, double rb) {
        leftFront.setPower(lf);
        rightFront.setPower(rf);
        leftBack.setPower(lb);
        rightBack.setPower(rb);
    }

}

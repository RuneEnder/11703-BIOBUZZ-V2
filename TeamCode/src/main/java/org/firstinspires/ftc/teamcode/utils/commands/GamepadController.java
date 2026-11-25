package org.firstinspires.ftc.teamcode.utils.commands;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadController {

    public GamepadController(Gamepad input1, Gamepad input2){
        gamepad1 = input1;
        gamepad2 = input2;
    }


    private final Gamepad gamepad1;
    private final Gamepad gamepad2;

    public boolean setStartPoseToPark() { return gamepad1.dpad_down; }
    public boolean setStartPoseToCorner() { return gamepad1.dpad_up; }
    public boolean setStartPoseToHive() { return gamepad1.dpad_left; }
    public boolean setTeamToBlue() { return gamepad1.x; }
    public boolean setTeamToRed() { return gamepad1.b; }
    public float strafe() { return gamepad1.left_stick_x; }
    public float drive() { return gamepad1.left_stick_y; }
    public float turn() { return gamepad1.right_stick_x; }

    public boolean toggleIntakeButton() { return gamepad1.rightBumperWasPressed(); }
    public boolean toggleTransferButton() { return gamepad1.rightBumperWasPressed(); }
    public boolean toggleOuttakeButton() { return gamepad1.leftBumperWasPressed(); }
    public boolean shootButton() { return gamepad1.a; }
    public boolean toggleReverseIntakeButton() { return gamepad1.right_trigger <= 0.1; }
    public boolean toggleReverseOuttakeButton() { return gamepad1.left_trigger <= 0.1; }



}

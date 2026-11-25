package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.mechaninisms.shooter.Rotatinator;

public class RobotConstants {
    public void build() {
        Rotatinator.CENTER_POSITION = 0.5;
        Rotatinator.GEAR_RATIO = 52.0 / 40.0;
        Rotatinator.SERVO_RANGE_DEG  = 300;
        Rotatinator.maxDeg = 180.0;
        Rotatinator.minDeg = -180.0;
        Rotatinator.REVERSED = false;
    }
}

package org.firstinspires.ftc.teamcode;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.mechaninisms.shooter.Rotatinator;

public class RobotConstants {
    public static Pose HIVE_POSE = new Pose(57.75840643274855, 6.862207602339179, Math.toRadians(-90));
    public static Pose PARK_POSE = new Pose(7.695540935672524, 83.09429824561404, Math.toRadians(0));
    public static Pose CORNER_POSE = new Pose(7.695540935672523, 133.98464912280704, Math.toRadians(180));
    public void build() {
        Rotatinator.CENTER_POSITION = 0.5;
        Rotatinator.GEAR_RATIO = 1;
        Rotatinator.SERVO_RANGE_DEG  = 360;
        Rotatinator.maxDeg = 180.0;
        Rotatinator.minDeg = -180.0;
        Rotatinator.REVERSED = false;
    }
}

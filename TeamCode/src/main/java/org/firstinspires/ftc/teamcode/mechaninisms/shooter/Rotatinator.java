package org.firstinspires.ftc.teamcode.mechaninisms.shooter;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

/**
 * Turret controller for a goBILDA 5-turn dual mode servo in POSITION mode.
 *
 * Turret angle convention: 0 deg = turret facing the front of the robot,
 * positive = counterclockwise (same as Pedro heading).
 *
 * The angle this class reports is the last COMMANDED angle, not a measured one.
 */
public class Rotatinator {

    public final Servo Rotator;
    private final Follower follower;

    public static double SERVO_RANGE_DEG;   // 5 turns at full PWM range
    public static double GEAR_RATIO;   // servo turns per turret turn (40 servo, 52 turret)
    public static double CENTER_POSITION;      // servo position where turret angle = 0
    public static boolean REVERSED;          // flip if the turret moves the wrong way
    public static double minDeg;
    public static double maxDeg;

    /**
     * @param hardwareMap the OpMode's hardwareMap
     * @param follower    the ONE follower your OpMode already created (don't make a new one here)
     */
    public Rotatinator(HardwareMap hardwareMap, Follower follower) {
        this.follower = follower;

        Rotator = hardwareMap.get(Servo.class, "turretServo");
        ((ServoImplEx) Rotator).setPwmRange(new PwmControl.PwmRange(500, 2500));

        setAngleDeg(0);   // center the turret on init (it will move)
    }

    // ---- Commanding ----

    /**
     * Moves the turret to an angle relative to the robot (degrees). Clamped to the limits.
     */
    public void setAngleDeg(double turretDeg) {
        turretDeg = clamp(turretDeg, minDeg, maxDeg);
        double servoDeg = turretDeg * GEAR_RATIO * (REVERSED ? -1 : 1);
        double position = CENTER_POSITION + servoDeg / SERVO_RANGE_DEG;
        Rotator.setPosition(clamp(position, 0.0, 1.0));
    }

    /**
     * Points the turret at a field point (same units/frame as Pedro, e.g. inches).
     * Reads the robot pose from the follower, so follower.update() must run every loop.
     * Returns the turret angle (degrees, relative to the robot) that was commanded.
     */
    public double rotateTurret(double TargetX, double TargetY) {
        double RobotX = follower.pose().x();
        double RobotY = follower.pose().y();
        double RobotHeading = follower.pose().heading();   // radians

        double fieldAngleRad = Math.atan2(TargetY - RobotY, TargetX - RobotX);
        double relativeDeg = Math.toDegrees(fieldAngleRad - RobotHeading);

        setAngleDeg(pickReachable(relativeDeg, getAngleDeg()));
        return getAngleDeg();
    }

    // ---- Tracking ----

    /**
     * Turret angle relative to the robot, in degrees (last commanded).
     */
    public double getAngleDeg() {
        double servoDeg = (Rotator.getPosition() - CENTER_POSITION) * SERVO_RANGE_DEG * (REVERSED ? -1 : 1);
        return servoDeg / GEAR_RATIO;
    }

    /**
     * Turret angle relative to the robot, in radians.
     */
    public double getAngleRad() {
        return Math.toRadians(getAngleDeg());
    }

    /**
     * Turret heading on the field in radians (wrapped to -pi..pi).
     */
    public double getFieldHeadingRad() {
        return normalizeRad(follower.pose().heading() + getAngleRad());
    }

    public boolean atLimit() {
        double a = getAngleDeg();
        return a <= minDeg + 0.5 || a >= maxDeg - 0.5;
    }
    /**
     * Turns any angle into the equivalent (+/- 360) one inside the limits that is closest to the
     * current angle. If none fits, falls back to clamping.
     */
    private double pickReachable(double angleDeg, double currentDeg) {
        angleDeg = normalizeDeg(angleDeg);
        double best = clamp(angleDeg, minDeg, maxDeg);
        double bestDist = Double.MAX_VALUE;
        boolean found = false;
        for (int k = -2; k <= 2; k++) {
            double candidate = angleDeg + 360.0 * k;
            if (candidate < minDeg || candidate > maxDeg) continue;
            double dist = Math.abs(candidate - currentDeg);
            if (dist < bestDist) {
                bestDist = dist;
                best = candidate;
                found = true;
            }
        }
        return found ? best : clamp(angleDeg, minDeg, maxDeg);
    }

    private static double normalizeDeg(double deg) {
        deg %= 360.0;
        if (deg > 180.0) deg -= 360.0;
        if (deg <= -180.0) deg += 360.0;
        return deg;
    }

    private double normalizeRad(double rad) {
        rad %= 2 * Math.PI;
        if (rad > Math.PI) rad -= 2 * Math.PI;
        if (rad <= -Math.PI) rad += 2 * Math.PI;
        return rad;
    }

    private double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}
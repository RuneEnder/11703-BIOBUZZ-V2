package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechaninisms.shooter.Rotatinator;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

public class Robot {
    public static Telemetry telemetry;
    public RobotConstants constants = new RobotConstants();
    public AllianceColor allianceColor = AllianceColor.None;
    public boolean startsInCorner = false;
}

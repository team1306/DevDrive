package frc.robot;

public class RobotStats {

    public static final int PulsesPerRotation = 1024;
    public static final double WheelDiameterFeet=6.0 / 12.0;
    private static final double WheelCircumferenceFeet = Math.PI*WheelDiameterFeet;
    public static final double TopRotationsPerSecond = 9800 / PulsesPerRotation;// 980u/100ms * rot/1024u
    public static final double TopFeetPerSecond = TopRotationsPerSecond * WheelCircumferenceFeet;// currently
}
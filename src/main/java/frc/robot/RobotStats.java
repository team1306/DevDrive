package frc.robot;


public class RobotStats{

    public static final int PulsesPerRotation=1024;
    public static final double TopRotationsPerSecond=99.8/PulsesPerRotation;//998u/100ms by 1024u/4rot= 0.974609375‬rot/100ms
    private static final double WheelCircumference=Math.PI*6/12;//in feet
    public static final double TopFeetPerSecond=TopRotationsPerSecond*WheelCircumference/10;
    public static final double WheelDiameterMeters=0.1524;
}
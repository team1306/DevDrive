package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DriveCommand extends Command {

    public DriveCommand() {
        requires(Robot.driveTrain);
        Robot.driveTrain.differentialDrive.setDeadband(0.001);
    }

    Trajectory trajectoryLeft, trajectoryRight;
    EncoderFollower leftFollower, rightFollower;

    @Override
    protected void initialize() {
        super.initialize();
        Robot.driveTrain.setLeftEncoderPosition(0);
        Robot.driveTrain.setRightEncoderPosition(0);
        try {
            trajectoryLeft = PathfinderFRC.getTrajectory("Line.left");
            trajectoryRight = PathfinderFRC.getTrajectory("Line.right");
        } catch (IOException e) {
            e.printStackTrace();
        }
        leftFollower = new EncoderFollower(trajectoryLeft);
        rightFollower = new EncoderFollower(trajectoryRight);

        leftFollower.configureEncoder(Robot.driveTrain.getLeftEncoderPosition(), RobotStats.PulsesPerRotation,
                RobotStats.WheelDiameterFeet);
        rightFollower.configureEncoder(Robot.driveTrain.getRightEncoderPosition(), RobotStats.PulsesPerRotation,
                RobotStats.WheelDiameterFeet);

        leftFollower.configurePIDVA(0.2, 0, -0.01, 1/RobotStats.TopFeetPerSecond, 0);
        rightFollower.configurePIDVA(0.2, 0, -0.01, 1/RobotStats.TopFeetPerSecond, 0);
    }

    @Override
    protected void execute() {
        System.out.println("Maximum velocity: "+RobotStats.TopFeetPerSecond);
        double leftVel =leftFollower.calculate(Robot.driveTrain.getLeftEncoderPosition());
        double rightVel =rightFollower.calculate(Robot.driveTrain.getRightEncoderPosition());
        Robot.driveTrain.TankDrive(leftVel,rightVel);
     }

    @Override
    public synchronized void cancel() {
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
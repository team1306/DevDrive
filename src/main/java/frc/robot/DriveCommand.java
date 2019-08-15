package frc.robot;

import java.io.IOException;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DriveCommand extends Command {

    public DriveCommand() {
        requires(Robot.driveTrain);
        Robot.driveTrain.differentialDrive.setDeadband(0.0001);
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
                RobotStats.WheelDiameterMeters);
        rightFollower.configureEncoder(Robot.driveTrain.getRightEncoderPosition(), RobotStats.PulsesPerRotation,
                RobotStats.WheelDiameterMeters);

        leftFollower.configurePIDVA(1.5, 0, 0.0, 1 / RobotStats.TopFeetPerSecond, 0.0);
        rightFollower.configurePIDVA(1.5, 0, 0.0, 1 / RobotStats.TopFeetPerSecond, 0.0);
    }

    @Override
    protected void execute() {
        double leftVel = leftFollower.calculate(Robot.driveTrain.getLeftEncoderPosition());
        double rightVel = rightFollower.calculate(Robot.driveTrain.getRightEncoderPosition());
        System.out.println("Left vel: "+leftVel);
        Robot.driveTrain.rightLeader.set(ControlMode.Velocity, rightVel);
        Robot.driveTrain.leftLeader.set(ControlMode.Velocity, leftVel);
    }

    @Override
    public synchronized void cancel() {
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return rightFollower.isFinished() && leftFollower.isFinished();
    }

}
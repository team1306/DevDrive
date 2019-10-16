package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

    public DriveCommand() {
        //"requires" is a function (a reusable block of code) that tells something else
        // in the program that this command, and this command only, can use the robots drive train.
        requires(Robot.driveTrain);

        //A deadband is an area near the center of a joystick (on a controller) that is set to 0 so that the robot
        //can be piloted to come to a stop
        Robot.driveTrain.differentialDrive.setDeadband(0.001);
    }

    @Override
    protected void initialize() {
       //This method (or block of code) is run once, at the beginning of the program
    }

    @Override
    protected void execute() {
        //This method (or block of code, within the { } brackets) is called multiple times

        //Tank drive sets the left and the right side of wheels of the robot to a speed between
        //-1 and 1, with zero being at a stop. Can you make the robot drive forward? How about backward? Turning?
        Robot.driveTrain.TankDrive(0,0);
     }

    @Override
    public synchronized void cancel() {
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        //This method notifies the program that this command has finished. Since we always want to drive, it is never finished.
        return false;
    }

}
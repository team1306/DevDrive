package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfigUtil;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

    WPI_TalonSRX rightLeader;
    WPI_TalonSRX rightFollower;

    WPI_TalonSRX leftLeader;
    WPI_TalonSRX leftFollower;

    DifferentialDrive differentialDrive;

    public DriveTrain() {
        super();

        TalonSRXPIDSetConfiguration pidVals=new TalonSRXPIDSetConfiguration();

        rightLeader = new WPI_TalonSRX(RobotMap.RightLeaderID);
        rightLeader.config_kP(0, 1);
        rightFollower = new WPI_TalonSRX(RobotMap.RightFollowerID);

        leftLeader = new WPI_TalonSRX(RobotMap.LeftLeaderID);
        leftLeader.config_kP(0, 1);
        leftFollower = new WPI_TalonSRX(RobotMap.LeftFollowerID);

        rightFollower.follow(rightLeader);
        leftFollower.follow(leftLeader);

        differentialDrive = new DifferentialDrive(leftLeader, rightLeader);

    }

    public void ArcadeDrive(double vel, double theta){
        differentialDrive.arcadeDrive(vel, theta);
    }

    public void TankDrive(double left,double right){
        differentialDrive.tankDrive(left, right);
    }

    public int getLeftEncoderPosition(){
        return -leftLeader.getSensorCollection().getQuadraturePosition();
    }

    public int getRightEncoderPosition(){
        return -rightLeader.getSensorCollection().getQuadraturePosition();
    }

    public void setLeftEncoderPosition(int pos){
        leftLeader.getSensorCollection().setQuadraturePosition(-pos, 0);
    }

    public void setRightEncoderPosition(int pos){
        rightLeader.getSensorCollection().setQuadraturePosition(-pos, 0);
    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new DriveCommand());
    }
}
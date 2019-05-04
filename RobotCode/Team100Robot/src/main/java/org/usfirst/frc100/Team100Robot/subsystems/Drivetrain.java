// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Team100Robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Drivetrain.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
    private WPI_TalonSRX leftMaster;
    private WPI_TalonSRX rightMaster;
    private DifferentialDrive differentialDrive;
    private WPI_VictorSPX leftFollower;
    private WPI_VictorSPX rightFollower;
    public PIDController turnPID;
    public Solenoid shift;

    public Drivetrain() {
        shift = new Solenoid(Constants.PCM_CANID,Constants.DRIVETRAIN_SHIFTER_PCMID);
        leftMaster = new WPI_TalonSRX(Constants.DRIVE_TRAIN_LEFT_MASTER_CANID);
        rightMaster = new WPI_TalonSRX(Constants.DRIVE_TRAIN_RIGHT_MASTER_CANID);
        
        differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
        addChild("DifferentialDrive",differentialDrive);
        differentialDrive.setSafetyEnabled(false);
        differentialDrive.setExpiration(0.7);
        differentialDrive.setMaxOutput(Constants.DRIVE_TRAIN_MAX_MOTOR_OUTPUT);
        
        leftFollower = new WPI_VictorSPX(Constants.DRIVE_TRAIN_LEFT_FOLLOWER_CANID);
        rightFollower = new WPI_VictorSPX(Constants.DRIVE_TRAIN_RIGHT_FOLLOWER_CANID);
        
        leftFollower.follow(leftMaster);
        rightFollower.follow(rightMaster);
        leftMaster.configContinuousCurrentLimit(Constants.DT_MAX_AMP);
        rightMaster.configContinuousCurrentLimit(Constants.DT_MAX_AMP);



        leftMaster.setInverted(Constants.DRIVE_TRAIN_LEFT_MASTER_INVERT);
        leftFollower.setInverted(Constants.DRIVE_TRAIN_LEFT_FOLLOWER_INVERT);
        rightMaster.setInverted(Constants.DRIVE_TRAIN_RIGHT_MASTER_INVERT);
        rightFollower.setInverted(Constants.DRIVE_TRAIN_RIGHT_FOLLOWER_INVERT);
        leftMaster.overrideLimitSwitchesEnable(false);
        rightMaster.overrideLimitSwitchesEnable(false);
        

        /*turnPID = new PIDController(Constants.DT_TURN_P, Constants.DT_TURN_I, Constants.DT_TURN_D, Robot.ahrs, this);
        turnPID.setInputRange(Constants.DT_TURN_MIN_ROTATION_ANGLE, Constants.DT_TURN_MAX_ROTATION_ANGLE);
        turnPID.setContinuous(true);
        turnPID.setOutputRange(Constants.DT_TURN_MIN_OUTPUT, Constants.DT_TURN_MAX_OUTPUT);
        turnPID.setAbsoluteTolerance(Constants.DT_TURN_ABSOLUTE_TOLERANCE);
   */
    }

    public void turn(double leftPower, double rightPower){
        leftMaster.set(ControlMode.PercentOutput, leftPower);
        rightMaster.set(ControlMode.PercentOutput, rightPower);
    }

    public void pidTurn(){
        //turn(turnPID.get(), turnPID.get());
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void stop(){
        leftMaster.set(ControlMode.PercentOutput, 0);
        rightMaster.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putNumber("ENC LEFT",leftMaster.getSelectedSensorPosition());
        SmartDashboard.putNumber("ENC RIGHT", rightMaster.getSelectedSensorPosition());
        SmartDashboard.putNumber("PO LEFT",leftMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("PO RIGHT",rightMaster.getMotorOutputPercent());
        SmartDashboard.putBoolean("SHIFT State", shift.get());
        SmartDashboard.putNumber("SHIFT ID", Constants.DRIVETRAIN_SHIFTER_PCMID);
        //SmartDashboard.putData("SHIFT PCM", shift);
    }

    public void drive(){
        //System.out.println("DRIVE");
        if(!Constants.EXPO_MODE){
            differentialDrive.arcadeDrive(-Robot.oi.getLeftStick().getY(), Robot.oi.getRightStick().getX());
        }
        else{
            //System.out.println("IN EXPO MODE");
            differentialDrive.arcadeDrive(-Robot.oi.getLeftStick().getY()*Constants.EXPO_MODE_REDUCER, Robot.oi.getRightStick().getX()*Constants.EXPO_MODE_REDUCER);

        }
    }

	@Override
	public void pidWrite(double output) {
        leftMaster.set(ControlMode.PercentOutput, output*Constants.DT_TURN_MOTOR_OUTPUT);
        rightMaster.set(ControlMode.PercentOutput, output*Constants.DT_TURN_MOTOR_OUTPUT);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


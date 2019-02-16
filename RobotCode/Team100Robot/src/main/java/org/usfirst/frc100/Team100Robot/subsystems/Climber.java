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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc100.Team100Robot.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;

import org.usfirst.frc100.Team100Robot.Robot;

/**
 *
 */
public class Climber extends Subsystem {

    private WPI_TalonSRX climberMaster;
    private Solenoid deploy;
    private WPI_VictorSPX climberFollower;

    public final Encoder climberMasterEncoder = Robot.climberEncoder; 

    public Climber() {
        climberMaster = new WPI_TalonSRX(Constants.CLIMBER_MASTER_CANID);
        
        deploy = new Solenoid(Constants.PCM_CANID, Constants.CLIMBER_DEPLOY_PCMID);
        addChild("Deploy",deploy);
        
        climberFollower = new WPI_VictorSPX(Constants.CLIMBER_FOLLOWER_CANID);

        climberFollower.follow(climberMaster);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void updateDashboard(){
        SmartDashboard.putString(Constants.SB_GROUP_PREFIX_CLIMBER + "CurrentCommand", getCurrentCommandName());
        SmartDashboard.putNumber(Constants.SB_GROUP_PREFIX_CLIMBER + "Voltage", climberMaster.getBusVoltage());
        SmartDashboard.putNumber(Constants.SB_GROUP_PREFIX_CLIMBER + "Position", climberMaster.getSelectedSensorPosition());
        SmartDashboard.putNumber(Constants.SB_GROUP_PREFIX_CLIMBER + "Velocity", climberMaster.getSelectedSensorVelocity());
    }

}


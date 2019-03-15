/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Shoulder;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Homing position for shoulder... THE SHOULDER DOES NOT HOME
 */
public class ShoulderLevel1Cargo extends Command {
  boolean done;
  public ShoulderLevel1Cargo() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.carriageShoulder);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done = false;
    Robot.carriageShoulder.updateSetpoint(Robot.carriageShoulder.degreesToSetpointConverter(Robot.carriageShoulder.LEVEL_ONE_CARGO_SETPOINT));    
    SmartDashboard.putBoolean("Shoulder home pos done", false);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("@@@@@@"+Math.abs(Robot.carriageShoulder.currentSetpoint-Robot.carriageShoulder.carriageShoulderMotor.getSelectedSensorPosition()));
    System.out.println("SETPOTNT"+Robot.carriageShoulder.currentSetpoint);
    
    if(Math.abs(Robot.carriageShoulder.currentSetpoint-Robot.carriageShoulder.carriageShoulderMotor.getSelectedSensorPosition())<Constants.SHOULDER_BUFFER){
      done = true;
      SmartDashboard.putBoolean("Shoulder home pos done",true);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  
  @Override
  protected void end() {
    System.out.println("SHOULDER LV 2 CARGO DONE");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

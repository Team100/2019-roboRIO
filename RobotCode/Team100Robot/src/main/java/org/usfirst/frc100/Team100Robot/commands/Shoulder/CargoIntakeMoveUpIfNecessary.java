/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Shoulder;

import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CargoIntakeMoveUpIfNecessary extends Command {
  boolean done = false;
  public CargoIntakeMoveUpIfNecessary() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.global);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done = true;
    if(Robot.elevator.previousSetpointLevel == 3 || Robot.elevator.previousSetpointLevel == 4 ){
      done = false;
      new ShoulderHoming().start();
    }

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(Robot.carriageShoulder.carriageShoulderMotor.getSelectedSensorPosition() - Robot.carriageShoulder.currentSetpoint ) < Robot.carriageShoulder.HOMING_SETPOINT || done){
      done = true;
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

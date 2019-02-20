/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator;

import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorPageUp extends Command {
  public ElevatorPageUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.elevator.setpointLevel < Robot.elevator.setpointsArray.length){
      Robot.elevator.setpointLevel += 1;

    }
    Robot.elevator.setpoint = Robot.elevator.setpointsArray[Robot.elevator.setpointLevel].setpoint;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    new ElevatorMoveToSetpoint().start();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

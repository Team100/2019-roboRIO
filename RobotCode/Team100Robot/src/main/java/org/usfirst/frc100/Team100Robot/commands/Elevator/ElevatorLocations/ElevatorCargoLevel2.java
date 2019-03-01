/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.Constants;


import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCargoLevel2 extends Command {
  private boolean done = false;

  public ElevatorCargoLevel2() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.moveToLevel(3);
    done = false;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(Robot.elevator.elevatorMaster.getSelectedSensorPosition() - Robot.elevator.setpoint) < Constants.ELEVATOR_POSITION_BUFFER){
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

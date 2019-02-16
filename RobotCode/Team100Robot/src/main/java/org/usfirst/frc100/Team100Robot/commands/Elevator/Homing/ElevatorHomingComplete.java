/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator.Homing;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.Elevator.homingStates;
import org.usfirst.frc100.Team100Robot.subsystems.Elevator.States;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHomingComplete extends Command {
  public ElevatorHomingComplete() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.hs = homingStates.COMPLETE;
    //TODO Change base robot state
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.homed = true;

    Robot.elevator.state= States.MOVE_TO_SETPOINT;
    System.out.println("HOMING COMPLETE");
    Robot.elevator.updateSetpoint(4000);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

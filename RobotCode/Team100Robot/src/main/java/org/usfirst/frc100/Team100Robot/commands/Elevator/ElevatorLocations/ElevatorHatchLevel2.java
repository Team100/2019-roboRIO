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

public class ElevatorHatchLevel2 extends Command {
  private boolean done = false;

  public ElevatorHatchLevel2() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.global);

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
    System.out.println("In hatch 2");
    System.out.println(Robot.elevator.currentPosition);
    System.out.println(Robot.elevator.getSetpoint());
    System.out.println(Constants.ELEVATOR_POSITION_BUFFER);
    if(Math.abs(Robot.elevator.currentPosition - Robot.elevator.getSetpoint()) < Constants.ELEVATOR_POSITION_BUFFER){
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
    System.out.println("^^^^^^^^^^^^ELEV LEVEL 2 END");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

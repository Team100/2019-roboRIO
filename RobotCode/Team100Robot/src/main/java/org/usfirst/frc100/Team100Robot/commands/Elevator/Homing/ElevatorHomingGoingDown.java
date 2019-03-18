/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator.Homing;


import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.Elevator.homingStates;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHomingGoingDown extends Command {
  private boolean complete = false;
  public ElevatorHomingGoingDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
    complete = false;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.hs = homingStates.ELEV_GOING_DOWN;
    Robot.elevator.elevatorMaster.set(ControlMode.PercentOutput, Constants.HOMING_GOING_DOWN_POWER);
    complete = false;
    System.out.println("In Init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.elevator.atMinHeight){
      complete = true;
      System.out.println("atMinHeight");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return complete;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("END");
    new ElevatorHomingDown().start();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

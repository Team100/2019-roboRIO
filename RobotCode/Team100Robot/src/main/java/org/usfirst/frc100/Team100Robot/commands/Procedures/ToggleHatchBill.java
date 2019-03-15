/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Procedures;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorHatchIntake;
import org.usfirst.frc100.Team100Robot.commands.HatchManipulator.Bill.BillLower;
import org.usfirst.frc100.Team100Robot.commands.Procedures.ElevatorTravel.ElevatorGoToHatchIntake;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleHatchBill extends Command {
  public ToggleHatchBill() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Robot.manipulator.hatchIntakeOut){
      new RetractHatchSystem().start();
    }else{
      new ElevatorGoToHatchIntake().start();
    }
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

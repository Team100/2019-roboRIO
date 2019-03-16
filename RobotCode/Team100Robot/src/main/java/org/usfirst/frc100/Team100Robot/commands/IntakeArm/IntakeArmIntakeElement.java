/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.IntakeArm;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.Manipulator.ScoringObjects;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeArmIntakeElement extends Command {
  private boolean done = false;
  public IntakeArmIntakeElement() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoPickup);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //System.out.println("INTAKE INIT");
    Robot.cargoPickup.setOutput(-Constants.CARGO_MANIPULATOR_INTAKE_SPEED);

    done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("Intake Arm Execute");

    if(Robot.manipulator.holding == ScoringObjects.CARGO){
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
    Robot.cargoPickup.setOutput(0);
    //System.out.println("#################### DONE WITH INTAKING");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

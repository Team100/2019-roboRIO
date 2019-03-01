/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Procedures.Scoring;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.Manipulator.ScoringObjects;

import edu.wpi.first.wpilibj.command.Command;

public class ScoreProcessing extends Command {
  public ScoreProcessing() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.manipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

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
    if(Robot.manipulator.holding == ScoringObjects.HATCH){
      new HatchScore().start();

    }else if(Robot.manipulator.holding == ScoringObjects.CARGO){
      new CargoScore().start();
    }else{
      System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% No object posessed");
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

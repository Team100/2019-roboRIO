/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.CargoManipulator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CargoManipulatorOuttake extends Command {
  private boolean done = false;
  double startTime = -1;
  public CargoManipulatorOuttake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.manipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.manipulator.topRoller.set(ControlMode.PercentOutput,Constants.CARGO_MANIPULATOR_OUTTAKE_SPEED);
    Robot.manipulator.bottomRoller.set(ControlMode.PercentOutput, Constants.CARGO_MANIPULATOR_OUTTAKE_SPEED);
    done = false;
    startTime = Timer.getFPGATimestamp();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Timer.getFPGATimestamp() - startTime >= Constants.CARGO_EXPEL_DURATION){
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
    Robot.manipulator.topRoller.set(ControlMode.PercentOutput,0);
    Robot.manipulator.bottomRoller.set(ControlMode.PercentOutput,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

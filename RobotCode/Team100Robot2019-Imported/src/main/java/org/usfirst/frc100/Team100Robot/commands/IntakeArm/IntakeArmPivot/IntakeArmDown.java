/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.CargoPickup.CargoPickupStates;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeArmDown extends Command {
  public IntakeArmDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoPickup);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.cargoPickup.cargoIntakePivotDoubleSolenoid.set(Value.kReverse);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("INTAKE ARM EXECUTE");
    System.out.println(Robot.cargoPickup.cargoIntakePivotDoubleSolenoid.get().toString());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.cargoPickup.cps ==  CargoPickupStates.DOWN;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("IntakeArmEnded");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

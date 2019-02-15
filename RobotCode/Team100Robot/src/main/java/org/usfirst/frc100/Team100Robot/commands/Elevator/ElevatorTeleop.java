/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorTeleop extends Command {
  public ElevatorTeleop() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("INITIALIZED TELEOP ELEVATOR");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*
    if(Robot.oi.getManipulatorControl().getRawAxis(3) > 0 && (!Robot.elevator.upperLimitSwitch.get() && !Robot.elevator.intermediateLimitSwitch.get()) ){ //Going up and elev not at top
      Robot.elevator.elevatorMaster.set(ControlMode.PercentOutput,-Robot.oi.getManipulatorControl().getRawAxis(3));
    }
    else if(Robot.oi.getManipulatorControl().getRawAxis(3) < 0 && (!Robot.elevator.lowerLimitSwitch.get() && !Robot.elevator.intermediateDownLimitSwitch.get()) ){ //down up and elev not at bottom
      Robot.elevator.elevatorMaster.set(ControlMode.PercentOutput,-Robot.oi.getManipulatorControl().getRawAxis(3));
    }
    else{
    }*/
    SmartDashboard.putNumber("ELEV OI AXIS",Robot.oi.getManipulatorControl().getRawAxis(3));
    if(Robot.oi.getManipulatorControl().getRawAxis(3) < 0 && !Robot.elevator.atMinHeight){
      Robot.elevator.elevatorMaster.set(ControlMode.PercentOutput,Robot.oi.getManipulatorControl().getRawAxis(3)*.4);

    }else if(Robot.oi.getManipulatorControl().getRawAxis(3) > 0 && !Robot.elevator.atMaxHeight){
      Robot.elevator.elevatorMaster.set(ControlMode.PercentOutput,Robot.oi.getManipulatorControl().getRawAxis(3)*.3);

    }
    else{
      Robot.elevator.elevatorMaster.set(ControlMode.PercentOutput,Robot.oi.getManipulatorControl().getRawAxis(3)*0.1);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("ELEVATOR TELEOP ENDED");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("ELEVATOR TELEOP INTERRUPTED");
  }
}

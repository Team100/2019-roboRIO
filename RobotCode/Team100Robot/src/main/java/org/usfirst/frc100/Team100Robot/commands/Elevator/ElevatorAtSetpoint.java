/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Elevator.Homing.ElevatorHomingInit;
import org.usfirst.frc100.Team100Robot.subsystems.Elevator.States;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorAtSetpoint extends Command {
  public ElevatorAtSetpoint() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!Robot.elevator.homed){
      new ElevatorHomingInit().start();
    }
    Robot.elevator.state = States.AT_SETPOINT;
    if(Math.abs(Robot.oi.getManipulatorControl().getRawAxis(3)) > 0.2){
      new ElevatorTeleop().start();
    }
    if(Robot.elevator.state == States.AT_SETPOINT){
      //Robot.elevator.elevatorMaster.set(ControlMode.Position,Robot.elevator.setpoint);

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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

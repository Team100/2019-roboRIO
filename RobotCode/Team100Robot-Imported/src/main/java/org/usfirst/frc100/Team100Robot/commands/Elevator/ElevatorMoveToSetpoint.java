/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.Elevator.States;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMoveToSetpoint extends Command {
  private boolean done;
  public ElevatorMoveToSetpoint() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

  }
  public ElevatorMoveToSetpoint(int setpoint){
    requires(Robot.elevator);
    Robot.elevator.setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.elevatorMaster.set(ControlMode.MotionMagic,Robot.elevator.setpoint);
    done = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(Robot.elevator.elevatorMaster.getSelectedSensorPosition(0) -Robot.elevator.setpoint) < Constants.ELEVATOR_POSITION_BUFFER){
      done = true;
      Robot.elevator.state = States.AT_SETPOINT;
      
    }
    System.out.println("MOVING TO SETPOINT");
    //Robot.elevator.updateSetpoint();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("ENC VAL"+Robot.elevator.elevatorMaster.getSelectedSensorPosition());

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

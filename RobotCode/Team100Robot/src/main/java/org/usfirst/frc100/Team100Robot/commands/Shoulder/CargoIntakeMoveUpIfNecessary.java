/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Shoulder;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CargoIntakeMoveUpIfNecessary extends Command {
  boolean done = false;
  boolean first = true;
  public CargoIntakeMoveUpIfNecessary() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(Robot.global);
    requires(Robot.carriageShoulder);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    first = true;
    done = false;
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(first){
      done = false;
      if(Robot.elevator.setpointLevel == 4 /*Was 4*/){
        done = false;
        System.out.println("()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()");
        SmartDashboard.putBoolean("CIMUIN DONE",done);
        Robot.carriageShoulder.updateSetpoint(Robot.carriageShoulder.degreesToSetpointConverter(Robot.carriageShoulder.HOMING_SETPOINT));    
      } else if(Robot.elevator.setpointLevel == 1){
        done = false;
        System.out.println("()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()");
        SmartDashboard.putBoolean("CIMUIN DONE",done);
        Robot.elevator.moveToLevel(0);
        Robot.carriageShoulder.updateSetpoint(Robot.carriageShoulder.degreesToSetpointConverter(Robot.carriageShoulder.HOMING_SETPOINT));    
      }
      else{
        done = true;
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ NOT NECESSARY TO PIVOT");
      }
      first = false;

    }
    System.out.println("CIMUIN Execute");
    SmartDashboard.putBoolean("CIMUIN DONE",done);

    if(Math.abs(Robot.elevator.elevatorMaster.getSelectedSensorPosition() - Robot.elevator.setpoint) < Constants.ELEVATOR_POSITION_BUFFER && (Math.abs(Robot.carriageShoulder.currentSetpoint-Robot.carriageShoulder.carriageShoulderMotor.getSelectedSensorPosition())<Constants.SHOULDER_BUFFER)){
      done = true;
    }
    if(Math.abs(Robot.carriageShoulder.currentSetpoint-Robot.carriageShoulder.carriageShoulderMotor.getSelectedSensorPosition())<Constants.SHOULDER_BUFFER){
      done = true;
      System.out.println("********************************DONE");
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
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%CIMUIN DONE");
    done = false;
    first = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.subsystems.CargoPickup.CargoPickupStates;
import org.usfirst.frc100.Team100Robot.subsystems.Elevator.SetpointGlobalLocations;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeArmConditionalDown extends Command {
  private boolean done = false;
  private boolean first = true;

  public SetpointGlobalLocations fromSetpoint = SetpointGlobalLocations.UNKNOWN;
  public SetpointGlobalLocations toSetpoint = SetpointGlobalLocations.UNKNOWN;
  public int nextSetpoint;
  public IntakeArmConditionalDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoPickup);
    nextSetpoint = 32767;
    first = true;
    done = false;
    
  }
  public IntakeArmConditionalDown(int nextSetpointIndex){
    requires(Robot.cargoPickup);
    this.nextSetpoint = nextSetpointIndex;
    first = true;
    done = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    first = true;
    done = false;
    this.nextSetpoint = Robot.elevator.desiredSetpointLevel;

    System.out.println("CONDITIONAL INITIALIZED " + nextSetpoint);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(first){
      this.nextSetpoint = Robot.elevator.desiredSetpointLevel;
      for(int i = 0; i < Robot.elevator.LOWER_SETPOINTS.length; i++){
        if(Robot.elevator.setpointLevel == Robot.elevator.LOWER_SETPOINTS[i]){
          fromSetpoint = SetpointGlobalLocations.DOWN;
        }
        if(nextSetpoint == Robot.elevator.LOWER_SETPOINTS[i]){
          toSetpoint = SetpointGlobalLocations.DOWN;
        }
      }
      for(int i = 0; i < Robot.elevator.UPPER_SETPOINTS.length; i++){
        if(Robot.elevator.setpointLevel == Robot.elevator.UPPER_SETPOINTS[i]){
          fromSetpoint = SetpointGlobalLocations.UP;
        }
        if(nextSetpoint == Robot.elevator.UPPER_SETPOINTS[i]){
          toSetpoint = SetpointGlobalLocations.UP;
        }
      }

      if(fromSetpoint == toSetpoint && fromSetpoint != SetpointGlobalLocations.UNKNOWN){
        System.out.println("CONDITION MET");
        done = true;
      }
      else{
        first = false;
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& SETTING PNEUMATIC");
        Robot.cargoPickup.cargoIntakePivotDoubleSolenoid.set(Value.kReverse);
      }
      
    }else{
      System.out.println("INTAKE ARM CONDITIONAL EXECUTE");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println((Robot.cargoPickup.cps ==  CargoPickupStates.DOWN || done));
    System.out.println(done);

    return Robot.cargoPickup.cps ==  CargoPickupStates.DOWN || done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("IntakeArmEnded");
    done = false;
    first = true;

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.subsystems;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorTeleop;
import org.usfirst.frc100.Team100Robot.commands.Procedures.GlobalRehome;
import org.usfirst.frc100.Team100Robot.commands.Procedures.HomingProcedure;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderTeleop;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class RobotAutoSwitch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public enum RobotTeleopState {SMART,MANUAL,DEFAULT};
  public RobotTeleopState rts = RobotTeleopState.DEFAULT;

  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    if(!Robot.oi.autoSwitch.get()){
      rts = RobotTeleopState.MANUAL;
    }else{
      rts = RobotTeleopState.SMART;
    }
  }
  @Override
  public void periodic(){
    SmartDashboard.putString("RTS", rts.toString());
    if(rts == RobotTeleopState.DEFAULT){
      System.out.println("DEFAULT RTS");
    }
    else if(!Robot.oi.autoSwitch.get() && rts != RobotTeleopState.MANUAL){
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&& STARTING MANUAL");
      rts = RobotTeleopState.MANUAL;
    
      Scheduler.getInstance().removeAll();
      new ElevatorTeleop().start();
      new ShoulderTeleop().start();
    }else if(Robot.oi.autoSwitch.get() && rts != RobotTeleopState.SMART && rts != RobotTeleopState.DEFAULT){
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&& STARTING SMART");
      rts = RobotTeleopState.SMART;
      Scheduler.getInstance().removeAll();
      new GlobalRehome().start();

    }

  }

}

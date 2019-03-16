/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.subsystems;

import org.usfirst.frc100.Team100Robot.commands.Global.GlobalDefault;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * Add your docs here.
 */
public class Global extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //public DigitalInput appeasement = new DigitalInput(666);
  public Global(){

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
   //
   // setDefaultCommand(new GlobalDefault());
  }
  public void thisMethodOnlyExistsToAppeaseWPILib(){
    //System.out.println("appeased");
  }
  @Override
  public void periodic() {
    super.periodic();
    SmartDashboard.putString("GLOBAL COMMAND",this.getCurrentCommandName());
  }
}

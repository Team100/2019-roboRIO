/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.commands.CargoManipulator.CargoManipulatorDefault;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX topRoller;
  public WPI_TalonSRX bottomRoller;
  public DigitalInput cargoSensor;
  public Manipulator(){
    topRoller = new WPI_TalonSRX(Constants.CARGO_MANIPULATOR_TOP_TALONSRX_ID);
    topRoller.overrideLimitSwitchesEnable(false);
    bottomRoller = new WPI_TalonSRX(Constants.CARGO_MANIPULATOR_BOTTOM_TALONSRX_ID);
    bottomRoller.overrideLimitSwitchesEnable(false);
    bottomRoller.follow(topRoller);
    cargoSensor =  new DigitalInput(4);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoManipulatorDefault());
  }
}

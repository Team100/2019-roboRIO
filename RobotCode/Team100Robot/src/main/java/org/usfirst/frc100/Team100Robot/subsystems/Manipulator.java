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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX topRoller;
  public WPI_TalonSRX bottomRoller;
  public DigitalInput cargoSensor;
  public Solenoid bill;
  public Solenoid hatchPusher;
  public Solenoid cargoScorer;

  public enum ScoringObjects{
    CARGO,HATCH,NONE
  }
  public ScoringObjects holding = ScoringObjects.NONE;
  public Manipulator(){
    topRoller = new WPI_TalonSRX(Constants.CARGO_MANIPULATOR_TOP_TALONSRX_ID);
    topRoller.overrideLimitSwitchesEnable(false);
    topRoller.configPeakOutputForward(1);
    topRoller.configPeakOutputReverse(-1);
    bottomRoller = new WPI_TalonSRX(Constants.CARGO_MANIPULATOR_BOTTOM_TALONSRX_ID);
    bottomRoller.overrideLimitSwitchesEnable(false);
    bottomRoller.configPeakOutputForward(1);
    bottomRoller.configPeakOutputReverse(-1);
    bottomRoller.follow(topRoller);
    cargoSensor =  new DigitalInput(Constants.CARGO_SENSOR_ID);
    bill = new Solenoid(Constants.PCM_CANID,Constants.HATCH_FLIPPER_PCMID);
    hatchPusher = new Solenoid(Constants.PCM_CANID,Constants.HATCH_SCORER_PCMID);
    //cargoScorer = new Solenoid(Constants.PCM_CANID,Constants.EMPTY2_PCMID);
    

  }

  @Override
  public void periodic() {
    super.periodic();
    //SmartDashboard.putData("BillSolenoid",bill);
    //SmartDashboard.putData("HatchPushSolenoid",hatchPusher);
    SmartDashboard.putString("Manipulator Current Command",this.getCurrentCommandName());

    if(cargoSensor.get()){
      holding = ScoringObjects.CARGO;
    } else if(true){ //Replace to hatch ls triggered
      holding = ScoringObjects.HATCH;
    }else{
      holding = ScoringObjects.NONE;
    }

    //SmartDashboard.putData("Bill",this.bill);
    //SmartDashboard.putData("Pusher",this.hatchPusher);
    //SmartDashboard.putData("Cargo Score",this.cargoScorer);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoManipulatorDefault());
    
  }
}

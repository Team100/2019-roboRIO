package org.usfirst.frc100.Team100Robot.subsystems;


import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Drivetrain.Drive;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetHeading extends Subsystem{
  AHRS ahrs;

  


  // public GetHeading(double compass, double fused) {
  //   compass = ahrs.getCompassHeading();
  //   fused = ahrs.getFusedHeading();
  //   return fused;
  // }


  @Override
  public void periodic() {

  }

  ahrs = new AHRS(I2C.Port.kOnboard);
  ahrs.enableLogging(true);

    @Override
    protected void initDefaultCommand() {
      
}
}
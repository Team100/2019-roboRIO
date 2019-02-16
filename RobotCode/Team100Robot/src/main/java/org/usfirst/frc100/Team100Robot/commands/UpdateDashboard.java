/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands;
import org.usfirst.frc100.Team100Robot.*;

/**
 * Add your docs here.
 */
public class UpdateDashboard {

    public static String update(){
        try{
            Robot.elevator.updateDashboard();
            Robot.drivetrain.updateDashboard();
            Robot.climber.updateDashboard();
            Robot.carriageShoulder.updateDashboard();
            Robot.cargoHatchScore.updateDashboard();
            Robot.cargoPickup.updateDashboard();
            Robot.hatchPickup.updateDashboard();
            Robot.shifter.updateDashboard();
            return null;
        } catch(Exception e) {
            return e.toString();
        }
    }
}
    
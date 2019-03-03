/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Procedures;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Elevator.Homing.ElevatorHomingInit;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmDown;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHoming;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HomingProcedure extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HomingProcedure() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    /*requires(Robot.elevator);
    requires(Robot.carriageShoulder);
    requires(Robot.cargoPickup);*/
    addSequential(new IntakeArmDown());
    //addSequential(new WaitForUserInput());
    addSequential(new ShoulderHoming());
    //addSequential(new WaitForUserInput());
    addSequential(new ElevatorHomingInit());
  }
}



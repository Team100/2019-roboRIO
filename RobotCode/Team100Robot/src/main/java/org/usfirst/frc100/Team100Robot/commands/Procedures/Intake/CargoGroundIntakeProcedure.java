/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Procedures.Intake;

import org.usfirst.frc100.Team100Robot.commands.CargoManipulator.CargoManipulatorIntake;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorCargoIntake;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorDown;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmIntakeElement;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmDown;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.CargoIntakeMoveUpIfNecessary;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHatchMid;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHoming;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoGroundIntakeProcedure extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CargoGroundIntakeProcedure() {
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
    addSequential(new CargoIntakeMoveUpIfNecessary());
    addSequential(new IntakeArmDown());

    addSequential(new ElevatorCargoIntake());
    addSequential(new ShoulderIntake());


    addParallel(new CargoManipulatorIntake());
    addSequential(new IntakeArmIntakeElement());
  }
}

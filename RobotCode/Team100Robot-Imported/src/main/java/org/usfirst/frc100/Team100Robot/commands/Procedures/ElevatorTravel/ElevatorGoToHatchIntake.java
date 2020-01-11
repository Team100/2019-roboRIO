/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot.commands.Procedures.ElevatorTravel;

import org.usfirst.frc100.Team100Robot.Robot;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorCargoLevel1;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorDown;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorLocations.ElevatorHatchIntake;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorUpdateDesiredEndpoint.ElevatorUpdateDesiredSetpointLevel10;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorUpdateDesiredEndpoint.ElevatorUpdateDesiredSetpointLevel2;
import org.usfirst.frc100.Team100Robot.commands.HatchManipulator.Bill.BillLower;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmConditionalDown;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmDown;
import org.usfirst.frc100.Team100Robot.commands.IntakeArm.IntakeArmPivot.IntakeArmUp;
import org.usfirst.frc100.Team100Robot.commands.Procedures.WaitForUserInput;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.CargoIntakeMoveUpIfNecessary;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderDown;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHatchIntake;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHatchMid;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderHoming;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderIntake;
import org.usfirst.frc100.Team100Robot.commands.Shoulder.ShoulderLevel;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevatorGoToHatchIntake extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorGoToHatchIntake() {
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
    requires(Robot.cargoPickup);
    requires(Robot.carriageShoulder);*/
    addSequential(new CargoIntakeMoveUpIfNecessary());
    addSequential(new ElevatorUpdateDesiredSetpointLevel10());

    addSequential(new IntakeArmConditionalDown());
    addSequential(new ElevatorCargoLevel1());
    addSequential(new ShoulderHatchIntake());

    addSequential(new ElevatorHatchIntake());
    addSequential(new IntakeArmUp());

    addSequential(new BillLower());
    
  }
}

package org.usfirst.frc100.Team100Robot.commands;


import org.usfirst.frc100.Team100Robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



public class UpdateDashboard extends Command {

    public UpdateDashboard() {	
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.elevator.updateDashboard();
        Robot.drivetrain.updateDashboard();
        Robot.climber.updateDashboard();
        Robot.carriageShoulder.updateDashboard();
        Robot.cargoHatchScore.updateDashboard();
    }

    protected boolean isFinished() {
        return false;
	}

	protected void end() {
	}

	protected void interrupted() {

    }
}

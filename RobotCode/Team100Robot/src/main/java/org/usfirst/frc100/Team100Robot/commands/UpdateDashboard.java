package org.usfirst.frc100.Team100Robot.commands;


import org.usfirst.frc100.Team100Robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateDashboard extends Command {

    public UpdateDashboard() {	
        Robot.elevator.UpdateDashboard();
        Robot.drivetrain.UpdateDashboard();
    //    Robot.climber.UpdateDashboard();
    }

    protected void initialize() {
        SmartDashboard.putString("Drivetrain/~TYPE~", "SubSystem");
        SmartDashboard.putString("Elevator/~TYPE~", "SubSystem");
        SmartDashboard.putString("Climber/~TYPE~", "SubSystem");
    }

    protected void execute() {
        Robot.drivetrain.UpdateDashboard();
        Robot.elevator.UpdateDashboard();
        Robot.climber.UpdateDashboard();
    }
    protected boolean isFinished() {
        return false;
	}

	protected void end() {
	}

	protected void interrupted() {

    }
}

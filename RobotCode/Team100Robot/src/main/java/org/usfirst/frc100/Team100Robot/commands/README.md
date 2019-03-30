# Team 100 2019 Robot Code
This repository contains the 2019 Robot Code for Team 100's Robot: `W19-001`.

## The 2019 Game and Robot
This code is for _FIRST_ Destination: Deep Space. You can learn more about our robot at [the 2019 robot profile page](https://team100.org/2019).


## Important Subdirectories in this Directory

This directory contains most logical code for the robot.

Individual `Command`s (steps within a state transition) are put in the following directories: `CargoManipulator|Drivetrain|Elevator|HatchManipulator|IntakeArm|Shoulder`. `Global|Legacy` are used for code storage that does not have a significant impact on the robot itself, and `Procedures` contains the `CommandGroup`s. In most cases, `OI.java` will call a `CommandGroup`, not a `Command`, because of the use of state machines and transitions for the state machines as the primary user interaction for the controller.
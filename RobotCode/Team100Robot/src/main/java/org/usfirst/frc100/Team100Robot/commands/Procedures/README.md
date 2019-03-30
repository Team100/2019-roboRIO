# Team 100 2019 Robot Code
This repository contains the 2019 Robot Code for Team 100's Robot: `W19-001`.

## The 2019 Game and Robot
This code is for _FIRST_ Destination: Deep Space. You can learn more about our robot at [the 2019 robot profile page](https://team100.org/2019).

## Important files and directories  in this directory
### ElevatorTravel
`ElevatorTravel` handles all of the procedures for moving the elevator. This includes moving the `CargoPickup`, `CarriageShoulder`, and `Elevator` subsystems.
### Scoring
`Scoring` contains the scoring mechanisms for both Hatch and Cargo scoring. The `ScoreProcessing` will detect if there is Cargo in the manipulator and will decide whether to expel cargo or hatch.
### Intake
`Intake` has the procedures to intake hatch and cargo, which is mapped to the `OI.java` buttons.
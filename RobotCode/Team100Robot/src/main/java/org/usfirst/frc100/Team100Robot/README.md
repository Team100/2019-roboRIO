# Team 100 2019 Robot Code
This repository contains the 2019 Robot Code for Team 100's Robot: `W19-001`.

## The 2019 Game and Robot
This code is for _FIRST_ Destination: Deep Space. You can learn more about our robot at [the 2019 robot profile page](https://team100.org/2019).

## Important files in this directory
### Robot.java
`Robot.java` is the main project file. It handles robot state transitions between `Autonomous` (Sandstorm) and `Teleop` and initializes the subsystems.
### OI.java
`OI.java` contains joystick code. In `OI.java`, each `JoystickButton` calls a `Command` or `CommandGroup`. This structure makes state transitions easier and also allows for all joystick calls to be in a unified place.
### Constants.java
`Constants.java` contains most important constants in our code. We are using a constants file to make everything more readable. Everything in `Constants.java` is `public static final` and complies with the [Google Style Guide convention for capitalization](https://google.github.io/styleguide/javaguide.html#s5.2.4-constant-names). 

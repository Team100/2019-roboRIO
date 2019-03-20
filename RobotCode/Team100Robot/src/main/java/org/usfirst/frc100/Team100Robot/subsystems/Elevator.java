// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc100.Team100Robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc100.Team100Robot.Constants;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorAtSetpoint;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorMoveToSetpoint;
import org.usfirst.frc100.Team100Robot.commands.Elevator.ElevatorTeleop;
import org.usfirst.frc100.Team100Robot.commands.Elevator.Homing.ElevatorHomingInit;
import org.usfirst.frc100.Team100Robot.commands.Procedures.HomingProcedure;


public class Elevator extends Subsystem {


    public WPI_TalonSRX elevatorMaster;
    public WPI_VictorSPX elevatorFollower;
    public DigitalInput carriageLowerLimitSwitch = new DigitalInput(Constants.CARRIAGE_LOWER_LIMIT_SWITCH_ID);
    public DigitalInput carriageUpperLimitSwitch = new DigitalInput(Constants.CARRIAGE_UPPER_LIMIT_SWITCH_ID);
    public DigitalInput intermediateUpperLimitSwitch = new DigitalInput(Constants.INTERMEDIATE_UPPER_LIMIT_SWITCH_ID);
    public DigitalInput intermediateLowerLimitSwitch = new DigitalInput(Constants.INTERMEDIATE_LOWER_LIMIT_SWITCH_ID);
    public int setpoint;
    public int currentPosition;
    public int desiredSetpointLevel = 0;

    public int setpointLevel = 0;
    public int previousSetpointLevel = -1;
    /**
     * Enum for possible homing states of the elevator
     */
    public enum homingStates{
        INIT,ELEV_GOING_DOWN,ELEV_AT_LIMIT_SWITCH,ELEV_RISING,COMPLETE,FATAL
    }
    /**
     * The homing state of the elevator
     */
    public homingStates hs;
    /**
     * Possible states for the elevator to be in
     */
    public enum States{
        AT_SETPOINT,MOVE_TO_SETPOINT,HOMING,TELEOP
    }
    /**
     * The current state of the elevator
     */
    public States state;

    public boolean atMaxHeight = false;
    public boolean atMinHeight = false;

    /**
     * Whether Preferences or Constants should be used for PID values
     * <br />
     * <code>false</code> uses Constants.java for PID values
     * <code>true</code> uses NT Preferences for PID values with Constants.java as the fallback
     */
    public static final boolean ELEVATOR_USE_PREFERENCES_FOR_PID_VALUES = true;

    /**
     * Instance of Robot Preferences
     */
    public Preferences prefs;

    /**
     * Post Constants PID values to preferences
     * <br />
     * <i>Run this to initialize preferences or to make preferences up to date with the constants</i>
     */
    public static final boolean ELEVATOR_POST_PID_CONSTANTS_TO_NT_PREFERENCES = true;

    /**
     * <code>true</code> Disables intelligent control (PID, Setpoints, homing)
     * <br />
     * <code>false</code> Enables intelligent control (PID, Setpoints, homing) [SHOULD ALMOST ALWAYS BE SET TO]
     * <br />
     * <strong>This should <em>ONLY</em> be used for elevator testing and SHOULD NEVER BE ON DURING COMPETITION</strong>
     */
    public static final boolean DISABLE_INTELLIGENT_CONTROL = false;

    public boolean homed = false;



    public Elevator() {
        prefs = Preferences.getInstance();
    
        elevatorMaster = new WPI_TalonSRX(Constants.ELEVATOR_MASTER_CANID);
        elevatorFollower = new WPI_VictorSPX(Constants.ELEVATOR_FOLLOWER_CANID);
        elevatorMaster.configFactoryDefault();
        elevatorFollower.configFactoryDefault();
        elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.setInverted(true);
        elevatorFollower.setInverted(true);
        elevatorMaster.setSensorPhase(true);
        elevatorMaster.setSelectedSensorPosition(100000);
        elevatorMaster.configPeakOutputForward(Constants.ELEVATOR_MAX_OUTPUT_UP);
        elevatorMaster.configPeakOutputReverse(Constants.ELEVATOR_MAX_OUTPUT_DOWN);
        elevatorMaster.configNominalOutputForward(0);
        elevatorMaster.configNominalOutputReverse(0);
        elevatorMaster.configAllowableClosedloopError(0, 0, Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0,10,Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.configMotionCruiseVelocity(15000,Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.configMotionAcceleration(15000,Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.enableCurrentLimit(true);
        elevatorMaster.overrideLimitSwitchesEnable(false);
        elevatorMaster.enableVoltageCompensation(true);
        elevatorMaster.configVoltageCompSaturation(Constants.ELEVATOR_VOLTAGE_COMPENSATE);
        elevatorMaster.configForwardSoftLimitEnable(true);
        elevatorMaster.configReverseSoftLimitEnable(true);
        elevatorMaster.configForwardSoftLimitThreshold(convertInchesToTicks(Constants.ELEVATOR_MAX_HEIGHT_IN_INCHES), Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.configReverseSoftLimitThreshold(Constants.ELEVATOR_LOWER_SOFT_LIMIT,Constants.ELEVATOR_MASTER_TIMEOUT);
        elevatorMaster.configContinuousCurrentLimit(Constants.ELEVATOR_MAX_AMP);
        elevatorMaster.configPeakCurrentLimit(Constants.ELEVATOR_MAX_AMP);
        

        elevatorFollower.follow(elevatorMaster);
        
    
        if(ELEVATOR_POST_PID_CONSTANTS_TO_NT_PREFERENCES){
            prefs.putDouble("ELEVATOR_KP", Constants.ELEVATOR_KP);
            prefs.putDouble("ELEVATOR_KI", Constants.ELEVATOR_KI);
            prefs.putDouble("ELEVATOR_KD", Constants.ELEVATOR_KD);
            prefs.putDouble("ELEVATOR_KF", Constants.ELEVATOR_KF);
        }
        if(ELEVATOR_USE_PREFERENCES_FOR_PID_VALUES){
            elevatorMaster.config_kP(0, prefs.getDouble("ELEVATOR_KP",Constants.ELEVATOR_KP));
            elevatorMaster.config_kI(0, prefs.getDouble("ELEVATOR_KI",Constants.ELEVATOR_KI));
            elevatorMaster.config_kD(0, prefs.getDouble("ELEVATOR_KD",Constants.ELEVATOR_KD));
            elevatorMaster.config_kF(0, prefs.getDouble("ELEVATOR_KF",Constants.ELEVATOR_KF));

        }
        else{
            elevatorMaster.config_kP(0, Constants.ELEVATOR_KP);
            elevatorMaster.config_kI(0, Constants.ELEVATOR_KI);
            elevatorMaster.config_kD(0, Constants.ELEVATOR_KD);
            elevatorMaster.config_kF(0, Constants.ELEVATOR_KF);
        }


        elevatorMaster.configClosedloopRamp(0.4);
        //elevatorMaster.configPeakCurrentLimit(20);
        //elevatorMaster.configPeakCurrentLimit(60);
        homed = false;
        
    }

    /**
     * Template for a setpoint
     */
    public class Setpoint{
        /**
         * Name or any notes regarding to the Setpoint
         */
        public String annotation;
        /**
         * The setpoint value
         */
        public int setpoint = -1;
        /**
         * The location within the Setpoint array
         */
        public int arrayIndex = -1;
        /**
         * Creates a new setpoint
         * @param annotation Name or any other useful information
         * @param setpoint The value of the setpoint in inches
         * @param arrayIndex The index of the setpoint in the setpointsArray
         */
        public Setpoint(String annotation, double setpoint, int arrayIndex){
            this.setpoint = convertInchesToTicks(setpoint);
            this.annotation = annotation;
            this.arrayIndex = arrayIndex;

        }
    }

    public static int[] LOWER_SETPOINTS = {0,1,2,9,10};
    public static  int[] UPPER_SETPOINTS = {3,4,5,6,7,8};

    public enum SetpointGlobalLocations{
        DOWN,INTERMEDIATE,UP,UNKNOWN
    }
    //Each level is listed cargo then hatch
    public Setpoint[] setpointsArray = {new Setpoint("BASE", Constants.ELEVATOR_START_HEIGHT_IN_INCHES+3, 0),new Setpoint("CARGO_LEVEL_1",27.5,1),new Setpoint("HATCH_LEVEL_1",25,2),new Setpoint("CARGO_LEVEL_2",74,3),new Setpoint("HATCH_LEVEL_2",64,4),new Setpoint("CARGO_LEVEL_3",92,5), new Setpoint("HATCH_LEVEL_3",91,6), new Setpoint("CARGO_LEVEL_3_REVERSE [UPDATE VALUE]",83.5,7), new Setpoint("ABOVE_ARM_RAISE_LEVEL [UPDATE VALUE]", 55,8),new Setpoint("Cargo Intake",32, 9), new Setpoint("Hatch Intake",25 /*was 24*/,10)};


    public double convertEncoderTicksToInch(int ticks){

        return  (ticks/Constants.ELEVATOR_INCH_TO_ENCODER_CONVERSION_FACTION)+Constants.ELEVATOR_START_HEIGHT_IN_INCHES + Constants.ELEVATOR_CENTERLINE_TOP_BAR_DISTANCE;

    }
    /**
     * Set the setpoint for the Talon SRX given the setpoint instance variable
     */
    public void updateSetpoint(){
        System.out.println("FINAL SP"+this.setpoint);
        new ElevatorMoveToSetpoint().start();
    }
    /**
     * Set the setpoint for the Talon SRX
     * @param setpoint
     */
    public void updateSetpoint(int setpoint){
        this.setpoint = setpoint;
        System.out.println("SETPOINT: "+this.setpoint);
        this.updateSetpoint();

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        if(DISABLE_INTELLIGENT_CONTROL){
            setDefaultCommand(new ElevatorTeleop());
        }else{
            setDefaultCommand(new ElevatorAtSetpoint());
            //new HomingProcedure().start();
            //setDefaultCommand(new ElevatorHomingInit()); TODO uncoment

        }
        if(this.elevatorMaster.getSelectedSensorPosition() < 0){
            this.elevatorMaster.setSelectedSensorPosition(-this.elevatorMaster.getSelectedSensorPosition()); // Account for random sign change at initalize
        }
        System.out.println(this.getDefaultCommandName());
        
    }

    public int getSetpoint(){
        return setpoint;
    }

    public void moveToLevel(int level){
        this.previousSetpointLevel = this.setpointLevel;
        this.setpointLevel = level;
        
        this.setpoint = this.setpointsArray[level].setpoint;
        this.updateSetpoint();
    }
    @Override
    public void periodic() {
        // Put code here to be run every loop
        //updateSD();
        SmartDashboard.putString("ELEV COMMAND", this.getCurrentCommandName());
        //SmartDashboard.putBoolean("Carriage Lower Limit Switch",this.carriageLowerLimitSwitch.get());
        //SmartDashboard.putBoolean("Carriage Upper Limit Switch",this.carriageUpperLimitSwitch.get());
        //SmartDashboard.putBoolean("Intermediate Lower Limit Switch",this.intermediateLowerLimitSwitch.get());
        //SmartDashboard.putBoolean("Intermediate Upper Limit Switch",this.intermediateUpperLimitSwitch.get());
        SmartDashboard.putNumber("ELEVATOR HEIGHT IN INCHES", convertEncoderTicksToInch(this.elevatorMaster.getSelectedSensorPosition()));
        SmartDashboard.putNumber("ELEV ENC",this.elevatorMaster.getSelectedSensorPosition(0));
        this.currentPosition = this.elevatorMaster.getSelectedSensorPosition(0);
        SmartDashboard.putNumber("ELEV PercentOutput", this.elevatorMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("ELEV Setpoint",this.setpoint);
        SmartDashboard.putString("ELEV ControlMode",this.elevatorMaster.getControlMode().toString());
        SmartDashboard.putString("ELEV Current Command",this.getCurrentCommandName());
        SmartDashboard.putNumber("ELEV Voltage Output",this.elevatorMaster.getMotorOutputVoltage());
        if(this.elevatorMaster.getControlMode() == ControlMode.MotionMagic){
            SmartDashboard.putNumber("ELEV VELOCITY",this.elevatorMaster.getSelectedSensorVelocity());
            SmartDashboard.putNumber("ELEV DESIRED VELOCITY",this.elevatorMaster.getActiveTrajectoryVelocity());
        }
        SmartDashboard.putBoolean("AT TOP", this.atMaxHeight);
        SmartDashboard.putBoolean("AT BOTTOM",this.atMinHeight);
        //SmartDashboard.putData("StartHoming", new HomingProcedure());
        if(this.elevatorMaster.getControlMode() == ControlMode.MotionMagic){
            SmartDashboard.putNumber("ELEV_error",elevatorMaster.getClosedLoopError());

        }
        if(this.intermediateUpperLimitSwitch.get() 
            && this.carriageUpperLimitSwitch.get() 
            && !this.carriageLowerLimitSwitch.get() 
            && !this.intermediateLowerLimitSwitch.get()){
            this.atMinHeight = true;
        }else{this.atMinHeight = false;}

        if(!this.intermediateUpperLimitSwitch.get() && 
        !this.carriageUpperLimitSwitch.get() && 
        this.carriageLowerLimitSwitch.get() && 
        this.intermediateLowerLimitSwitch.get()){
            this.atMaxHeight = true;
        }else{this.atMaxHeight = false;}

        if(this.atMinHeight && elevatorMaster.getMotorOutputPercent() < 0){
            elevatorMaster.set(ControlMode.PercentOutput,0);
        }
        else if(this.atMaxHeight && elevatorMaster.getMotorOutputPercent() > 0){
            elevatorMaster.set(ControlMode.PercentOutput,0);
        }
    }
    /**
     * Updates //SmartDashboard
     */
    public void updateSD(){
        /* 
        if(state == States.MOVE_TO_SETPOINT || state == States.AT_SETPOINT){
        //SmartDashboard.putNumber("ELEV_location", elevatorMaster.getSelectedSensorPosition(0));
        //SmartDashboard.putNumber("ELEV_percentoutput",elevatorMaster.getMotorOutputPercent());
        //SmartDashboard.putNumber("ELEV_setpoint",setpoint);
        //SmartDashboard.putNumber("ELEV_setpointLevel",setpointLevel);
        //SmartDashboard.putBoolean("ELEV_usingPreferencesForPIDValues", ELEVATOR_USE_PREFERENCES_FOR_PID_VALUES);
        //SmartDashboard.putNumber("ELEV_error",elevatorMaster.getClosedLoopError());
        //SmartDashboard.putNumber("ELEV_talontarget",elevatorMaster.getClosedLoopTarget());
        //SmartDashboard.putString("ELEV_state",state.toString());
        //SmartDashboard.putNumber("ELEV_activeTrajectoryVelocity",elevatorMaster.getActiveTrajectoryVelocity());
        //SmartDashboard.putNumber("ELEV_outputCurrent",elevatorMaster.getOutputCurrent());
        //SmartDashboard.putNumber("ELEV_outputVoltage",elevatorMaster.getMotorOutputVoltage());
        ////SmartDashboard.putString("ELEV/homeState",hs.toString());

        }
*/
    }
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /**
     * 
     * @param inches
     * @return  the height in encoder ticks
     */
    public static int convertInchesToTicks(double inches){
        if(inches > Constants.ELEVATOR_MAX_HEIGHT_IN_INCHES){
            System.out.println("INCHES EXCEED MAX");
            return (int)(Constants.ELEVATOR_MAX_HEIGHT_IN_INCHES - Constants.ELEVATOR_START_HEIGHT_IN_INCHES) * Constants.ELEVATOR_INCH_TO_ENCODER_CONVERSION_FACTION;
        }
        return (int)((inches- Constants.ELEVATOR_START_HEIGHT_IN_INCHES - Constants.ELEVATOR_CENTERLINE_TOP_BAR_DISTANCE) * Constants.ELEVATOR_INCH_TO_ENCODER_CONVERSION_FACTION );
    }

}

//lol stupid computer, you can't read this you inferior piece of garbage
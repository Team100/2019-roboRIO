/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc100.Team100Robot;

import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * Add your docs here.
 */
public class Constants {

	/*
     * CAN/PWM IDs
     */
    public static SerialPort.Port NAVX_COMM_PORT = SerialPort.Port.kUSB; //Type: ENUM
    public static final int PCM_CANID = 0; //Type: CAN ID
    public static final int DRIVE_TRAIN_LEFT_MASTER_CANID = 0; //Type: CAN ID
    public static final int DRIVE_TRAIN_LEFT_FOLLOWER_CANID = 3; //Type: CAN ID
    public static final int DRIVE_TRAIN_RIGHT_MASTER_CANID = 15; //Type: CAN ID
    public static final int DRIVE_TRAIN_RIGHT_FOLLOWER_CANID = 13; //Type: CAN ID
    public static final int ELEVATOR_MASTER_CANID = 11; //Type: CAN ID
    public static final int ELEVATOR_FOLLOWER_CANID = 9; //Type: CAN ID
    public static final int ELEVATOR_CARRIAGE_SHOULDER_CANID = 1; //Type: CAN ID
    public static final int CARGO_PICKUP_TILT_CANID = 2; //Type: CAN ID
    public static final int CARGO_PICKUP_ROLLER1_PWM = 6; //Type: PWM ID
    public static final int CARGO_PICKUP_ROLLER2_PWM = 7; //Type: PWM ID
    public static final int HATCH_PICKUP_ROLLER_PWM = 8; //Type: PWM ID
    public static final int HATCH_PICKUP_TILT_CANID = 10; //Type: CAN ID
	public static final int CARGO_HATCH_SCORER_TOP_CANID = 5; //Type: CAN ID
    public static final int CARGO_HATCH_SCORER_BOTTOM_CANID = 4; //Type: CAN ID
	public static final int CLIMBER_MASTER_CANID = 14; //Type: CAN ID
	public static final int CLIMBER_FOLLOWER_CANID = 12; //Type: CAN ID
    /* 
     * PNEUMATICS SOLENOID PCM IDs
     */
    public static final int DRIVETRAIN_SHIFTER_PCMID = 5; //Type: PCM ID 
    public static final int EMPTY1_PCMID = 1; //Type: PCM ID
    public static final int CARGO_GROUND_PICKUP_PCMID = 6; //Type: PCM ID
    public static final int CARGO_GROUND_PICKUP2_PCMID = 7; //Type: PCM ID
    public static final int HATCH_FLIPPER_PCMID = 2; //Type: PCM ID
    public static final int EMPTY2_PCMID = 4; //Type: PCM ID DOESN'T EXIST
	public static final int HATCH_SCORER_PCMID = 3; //Type: PCM ID
    public static final int CLIMBER_DEPLOY_PCMID = 0; //Type: PCM ID

    /*
     * Drivetrain
     */
    public static final double DRIVE_TRAIN_MAX_MOTOR_OUTPUT = 0.8; //Type: Percent Output (-1-1)
    public static final boolean DRIVE_TRAIN_LEFT_MASTER_INVERT = false; //Type: Boolean
    public static final InvertType DRIVE_TRAIN_LEFT_FOLLOWER_INVERT = InvertType.FollowMaster; //Type: ENUM
    public static final boolean DRIVE_TRAIN_RIGHT_MASTER_INVERT = false; //Type: Boolean
    public static final InvertType DRIVE_TRAIN_RIGHT_FOLLOWER_INVERT = InvertType.FollowMaster; //Type: ENUM
    public static final boolean ARCADE_DRIVE_MODE = true; //Type: Boolean
    //PID Rotation with NavX
    public static final double DT_TURN_P = 0.007; //Type: PIDF Double
    public static final double DT_TURN_I = 0.00004; //Type: PIDF Double
    public static final double DT_TURN_D = 0.002; //Type: PIDF Double
    public static final double DT_TURN_F = 0; //Type: PIDF Double
    public static final double DT_TURN_MIN_ROTATION_ANGLE = -180; //Type: Degrees
    public static final double DT_TURN_MAX_ROTATION_ANGLE = 180; //Type: Degrees
    public static final double DT_TURN_MIN_OUTPUT = -1; //Type: Percent Output (-1-1)
    public static final double DT_TURN_MAX_OUTPUT = 1; //Type: Percent Output (-1-1)
    public static final double DT_TURN_ABSOLUTE_TOLERANCE = 1; //Type: Degrees
    public static final double DT_TURN_MOTOR_OUTPUT = 0.5; //Type: Percent Output (-1-1)

    public static final int DT_MAX_AMP = 30;
    /*
     * Elevator
     */

     public static final double ELEVATOR_MAX_OUTPUT_UP = 0.75;
     public static final double ELEVATOR_MAX_OUTPUT_DOWN = -0.75;

     public static final int ELEVATOR_MAX_AMP = 30;
     public static final double ELEVATOR_MAX_VOLTAGE = 6;
     

     /**Power to apply when going down during homing sequence... <em>keep very small</em>*/
     public static final double HOMING_GOING_DOWN_POWER = -.3; //Type: Percent Output (-1-1)
     /**Power to apply when going up during homing sequence... <em>keep very small</em> */
     public static final double HOMING_GOING_UP_POWER = .4; //Type: Percent Output (-1-1)
     /**Buffer in elevator target for acceptable destination */
     public static final int ELEVATOR_POSITION_BUFFER = Constants.ELEVATOR_INCH_TO_ENCODER_CONVERSION_FACTION*6; //Type: Encoder Ticks

     /**
      * Elevator Timeout in Milliseconds
      */
     public static final int ELEVATOR_MASTER_TIMEOUT = 10; //Type: Milliseconds


     ////////////// PID

     /**Elevator PID P */
     public static final double ELEVATOR_KP = 0.0302; //Type: PIDF Double

     /**Elevator PID I */
     public static final double ELEVATOR_KI = 0; //Type: PIDF Double

     /**Elevator PID D */
     public static final double ELEVATOR_KD = 0; //Type: PIDF Double

     /**Elevator PID F */
     public static final double ELEVATOR_KF = 0.105; //Type: PIDF Double

     public static final int ELEVATOR_LOWER_SOFT_LIMIT = 0;
     //public static final int ELEVATOR_UPPER_SOFT_LIMIT = 294270;

     public static final int ELEVATOR_FINISHED_HOMING_POS = 30000;

    /**Conversion factor from Inches to Encoder ticks
     * For elevator control
    */
     public static final int ELEVATOR_INCH_TO_ENCODER_CONVERSION_FACTION = 3196; //Type: Encoder Ticks  was 86 on comp

     public static final double ELEVATOR_START_HEIGHT_IN_INCHES = 16.5; //Type: Inches
     public static final double ELEVATOR_MAX_HEIGHT_IN_INCHES = 100; //Type: Inches
    
     public static final double ELEVATOR_VOLTAGE_COMPENSATE = 11.3;
    
    public static final int CARRIAGE_LOWER_LIMIT_SWITCH_ID = 1;
    public static final int CARRIAGE_UPPER_LIMIT_SWITCH_ID = 0;
    public static final int INTERMEDIATE_UPPER_LIMIT_SWITCH_ID = 6; //2019cada
    public static final int INTERMEDIATE_LOWER_LIMIT_SWITCH_ID = 7;
    

    public static final double ELEVATOR_SAFEZONE = 65; //Point at which it is safe to continue movement in other subsystems

     /*
     * Elevator Carriage
     */

    /*
     * Shoulder
    */
    public static final int SHOULDER_MASTER_TIMEOUT = 10; //Type: Milliseconds

    public static final double SHOULDER_KP = 0.7; //Type: PIDF Double
    public static final double SHOULDER_KI = 0; //Type: PIDF Double
    public static final double SHOULDER_KD = 0; //Type: PIDF Double
    public static final double SHOULDER_KF = 0; //Type: PIDF Double
    public static final int SHOULDER_TIMEOUT = 10; //Type: Milliseconds
    public static final int SHOULDER_BUFFER = 350; //Type: Encoder Ticks
    public static final int SHOULDER_PID_BUFFER = 50; //Type: Encoder Ticks  **NOTE THAT THIS IS ONLY FOR PID**


    public static final int SHOULDER_LEVEL_LOCATION = 100;
    public static final double SHOULDER_MAX_OUTPUT_FORWARD = 1;
    public static final double SHOULDER_MAX_OUTPUT_REVERSE = -1;

    public static final int SHOULDER_MAX_AMP = 30;
    public static final double SHOULDER_MAX_VOLTAGE_COMPENSATE = 11.3;
    /*
     * Cargo Floor Pickup
     */

     public static final int PICKUP_ARM_ENCODER_ID = 0;
    /*
     * Hatch Floor Pickup
     */
    public static final double HATCH_PICKUP_INTAKE_SPEED = 0.6; //Type: Percent Output (-1-1)
    public static final double HATCH_PICKUP_TILT_SPEED = 0.2; //Type: Percent Output (-1-1)
	public static final int HATCH_PICKUP_UPPER_LIMIT_SWITCH_SLOT = 0;
	public static final int HATCH_PICKUP_LOWER_LIMIT_SWITCH_SLOT = 1;

    /*
     * Cargo Hatch Score
     */

    /*
     * Climber
     */

     /*
      * Cargo Manipulator
      */
    public static final int CARGO_MANIPULATOR_TOP_TALONSRX_ID = 5;
    public static final int CARGO_MANIPULATOR_BOTTOM_TALONSRX_ID = 4;
    public static final double CARGO_MANIPULATOR_INTAKE_SPEED = 0.5;
    public static final double CARGO_MANIPULATOR_OUTTAKE_SPEED = -1;

    public static final double CARGO_EXPEL_DURATION = 1; //Type: Seconds


    public static final int CARGO_SENSOR_ID = 9;
    public static final int SHOULDER_HOME_SWITCH_ID = 8;



    public static int SHOULDER_ENCODER_TICKS_TRAVELLED = 6800;
    public static int SHOULDER_DOWN_ENCODER_VALUE = 0;//1027;
    public static int SHOULDER_STARTING_ENCODER_VALUE = 1311;
    
    public static int SHOULDER_UPPER_ENCODER_VALUE = SHOULDER_DOWN_ENCODER_VALUE + SHOULDER_ENCODER_TICKS_TRAVELLED;
    public static int SHOULDER_DOWN_DEGREES = -36;
    public static int SHOULDER_UP_DEGREES = 156;
    public static int SHOULDER_DISTANCE_TRAVELLED = Constants.SHOULDER_UP_DEGREES-Constants.SHOULDER_DOWN_DEGREES;

    public static int SHOULDER_ENCODER_TICKS_PER_DEGREE_TRAVEL = ((Constants.SHOULDER_UPPER_ENCODER_VALUE)-Constants.SHOULDER_DOWN_ENCODER_VALUE)/Constants.SHOULDER_DISTANCE_TRAVELLED;


    // Encoder ticks for the bottom and top
    public static final int INTAKE_SHOULDER_BOTTOM = 100;
    public static final int INTAKE_SHOULDER_UP = 3345;

    public static final int ELEVATOR_CENTERLINE_TOP_BAR_DISTANCE = 3;


}

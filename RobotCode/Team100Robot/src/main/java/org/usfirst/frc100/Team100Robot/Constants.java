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
    public static SerialPort.Port NAVX_COMM_PORT = SerialPort.Port.kUSB;
    public static final int PCM_CANID = 0;
    public static final int DRIVE_TRAIN_LEFT_MASTER_CANID = 0;
    public static final int DRIVE_TRAIN_LEFT_FOLLOWER_CANID = 3;
    public static final int DRIVE_TRAIN_RIGHT_MASTER_CANID = 15;
    public static final int DRIVE_TRAIN_RIGHT_FOLLOWER_CANID = 13;
    public static final int ELEVATOR_MASTER_CANID = 11;
    public static final int ELEVATOR_FOLLOWER_CANID = 9;
    public static final int ELEVATOR_CARRIAGE_SHOULDER_CANID = 1;
    public static final int CARGO_PICKUP_TILT_CANID = 2;
    public static final int CARGO_PICKUP_ROLLER1_PWM = 6;
    public static final int CARGO_PICKUP_ROLLER2_PWM = 7;
    public static final int HATCH_PICKUP_ROLLER_PWM = 8;
    public static final int HATCH_PICKUP_TILT_CANID = 10;
	public static final int CARGO_HATCH_SCORER_TOP_CANID = 4;
    public static final int CARGO_HATCH_SCORER_BOTTOM_CANID = 5;
	public static final int CLIMBER_MASTER_CANID = 14;
	public static final int CLIMBER_FOLLOWER_CANID = 12;

    /* 
     * PNEUMATICS SOLENOID PCM IDs
     */
    public static final int DRIVETRAIN_SHIFTER_PCMID = 0;
    public static final int LOADING_STATION_INTAKE_PCMID = 1;
    public static final int CARGO_GROUND_PICKUP_PCMID = 5;
    public static final int HATCH_PICKUP_PCMID = 4;
    public static final int CARGO_SCORER_PCMID = 3;
	public static final int HATCH_SCORER_PCMID = 2;
    public static final int CLIMBER_DEPLOY_PCMID = 6;

    /*
     * Drivetrain
     */
    public static final double DRIVE_TRAIN_MAX_MOTOR_OUTPUT = 0.8;
    public static final boolean DRIVE_TRAIN_LEFT_MASTER_INVERT = false;
    public static final InvertType DRIVE_TRAIN_LEFT_FOLLOWER_INVERT = InvertType.FollowMaster;
    public static final boolean DRIVE_TRAIN_RIGHT_MASTER_INVERT = false;
    public static final InvertType DRIVE_TRAIN_RIGHT_FOLLOWER_INVERT = InvertType.FollowMaster;
    public static final boolean ARCADE_DRIVE_MODE = true;
    //PID Rotation with NavX
    public static final double DT_TURN_P = 0.007;
    public static final double DT_TURN_I = 0.00004;
    public static final double DT_TURN_D = 0.002;
    public static final double DT_TURN_F = 0;
    public static final double DT_TURN_MIN_ROTATION_ANGLE = -180;
    public static final double DT_TURN_MAX_ROTATION_ANGLE = 180;
    public static final double DT_TURN_MIN_OUTPUT = -1;
    public static final double DT_TURN_MAX_OUTPUT = 1;
    public static final double DT_TURN_ABSOLUTE_TOLERANCE = 1;
    public static final double DT_TURN_MOTOR_OUTPUT = 0.5;

    /*
     * Elevator
     */

     /**Power to apply when going down during homing sequence... <em>keep very small</em>*/
     public static final double HOMING_GOING_DOWN_POWER = 0.3;
     /**Power to apply when going up during homing sequence... <em>keep very small</em> */
     public static final double HOMING_GOING_UP_POWER = -0.3;
     /**Buffer in elevator target for acceptable destination */
     public static final int ELEVATOR_POSITION_BUFFER = 750;



     ////////////// PID

     /**Elevator PID P */
     public static final double ELEVATOR_KP = 1.0;

     /**Elevator PID I */
     public static final double ELEVATOR_KI = 0;

     /**Elevator PID D */
     public static final double ELEVATOR_KD = 0;

     /**Elevator PID F */
     public static final double ELEVATOR_KF = 1;


    /**Conversion factor from Inches to Encoder ticks
     * For elevator control
    */
     public static final int ELEVATOR_INCH_TO_ENCODER_CONVERSION_FACTION = 86;

     public static final double ELEVATOR_START_HEIGHT_IN_INCHES = 16.5;
     public static final double ELEVATOR_MAX_HEIGHT_IN_INCHES = 78.5;
    /*
     * Elevator Carriage
     */

    /*
     * Cargo Floor Pickup
     */

    /*
     * Hatch Floor Pickup
     */
    public static final double HATCH_PICKUP_INTAKE_SPEED = 0.6;
    public static final double HATCH_PICKUP_TILT_SPEED = 0.2;
	public static final int HATCH_PICKUP_UPPER_LIMIT_SWITCH_SLOT = 0;
	public static final int HATCH_PICKUP_LOWER_LIMIT_SWITCH_SLOT = 1;

    /*
     * Cargo Hatch Score
     */

    /*
     * Climber
     */

}

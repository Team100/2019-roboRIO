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
    public static int DRIVE_TRAIN_LEFT_MASTER_CANID = 0;
    public static int DRIVE_TRAIN_LEFT_FOLLOWER_CANID = 1;
    public static int DRIVE_TRAIN_RIGHT_FOLLOWER_CANID = 14;
    public static int DRIVE_TRAIN_RIGHT_MASTER_CANID = 15;
    public static int CARGO_PICKUP_TALON_CANID = 3;
    public static int CARGO_PICKUP_VICTOR1_PWM = 6;
    public static int CARGO_PICKUP_VICTOR2_PWM = 7;
    public static int HATCH_PICKUP_ROLLER_PWM = 8;
    public static int HATCH_PICKUP_TILT_CANID = 9;
    public static int ELEVATOR_MASTER_CANID = 10;
    public static int ELEVATOR_FOLLOWER_CANID = 11;
    public static int ELEVATOR_CARRIAGE_CANID = 2;

    /*
     * Drivetrain
     */
    public static double DRIVE_TRAIN_MAX_MOTOR_OUTPUT = 0.8;
    public static boolean DRIVE_TRAIN_LEFT_MASTER_INVERT = false;
    public static InvertType DRIVE_TRAIN_LEFT_FOLLOWER_INVERT = InvertType.FollowMaster;
    public static boolean DRIVE_TRAIN_RIGHT_MASTER_INVERT = false;
    public static InvertType DRIVE_TRAIN_RIGHT_FOLLOWER_INVERT = InvertType.FollowMaster;
    //PID Rotation with NavX
    public static double DT_MASTER_P = 0.007;
    public static double DT_MASTER_I = 0.00004;
    public static double DT_MASTER_D = 0.002;
    public static double DT_MASTER_F = 0;
    public static double DT_TURN_MIN_ROTATION_ANGLE = -180;
    public static double DT_TURN_MAX_ROTATION_ANGLE = 180;
    public static double DT_TURN_MIN_OUTPUT = -1;
    public static double DT_TURN_MAX_OUTPUT = 1;
    public static double DT_TURN_ABSOLUTE_TOLERANCE = 1;
    public static double DRIVE_TRAIN_PIVOT_MOTOR_OUTPUT = 0.5;
    public static boolean ARCADE_DRIVE_MODE = true;

}

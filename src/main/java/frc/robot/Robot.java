// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Here is all the imports, As you make more code, WPILIB will automatically fill these in to use the corresponding libraries

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.Timer;



public class Robot extends TimedRobot {
  //here is where you will want to initialize all of your Commands, Motors, Features, and furtherMore

  //below is the SmartDashboard AutoChooser Being Initialized
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /*below is the control system being initialized
  *on 2531, We really have only used Two types of Controllers, Xbox/PS4 and Joysticks
  *these controllers are bascially initialized the same but will use differnt inputs and values to control
  *Here we will not be looking into using the buttons(We will Later in the Command Based Structure), but rather the X & Y axis of the controllers
  */
  //here is what each one looks like
  private final XboxController kXbController = new XboxController(0);
  private final Joystick kPlaneJoystick = new Joystick(2);


  //Here is where you will initalize Motors and encoders
  private final Talon leftMotor = new Talon(0);
  private final Talon rightMotor = new Talon(1);
  private final Talon kFakeMotor1 = new Talon(2);
  private final Talon kFakeMotor2 = new Talon(3);

  //private final Talon shooterMotor = new Talon(4);
  

  private final MotorControllerGroup rightGroup = new MotorControllerGroup(rightMotor, kFakeMotor2);
  private final MotorControllerGroup leftGroup = new MotorControllerGroup(leftMotor, kFakeMotor1);

  //these are encoders, used to get how much a motor has moved
  private final Encoder leftEncoder = new Encoder(0, 2);
  private final Encoder rigthEncoder = new Encoder(1, 3, true);

  //Here is the timer for the TimedRobot Auto
  private final Timer timer = new Timer();

  //Here is where we want to establish what drive system we want to use

  //This Differential is only using two Motors, if we had made a drive train with two motors per side, we would need to use a motorContollerGroup
  private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
  private final MecanumDrive mecanumDrive = new MecanumDrive(leftMotor, kFakeMotor1, rightMotor, kFakeMotor2);
  private final DifferentialDrive kMultiMotorDrive = new DifferentialDrive(leftGroup, rightGroup);

  //part of kadens go to way
  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    

    //Something to remember is to reverse the direction of one sides motors and encoders so both sides have the same "foward"
    rightMotor.setInverted(true);
    leftMotor.setInverted(false);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
    m_autoSelected = m_chooser.getSelected();
   //m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }


    public void AutoCommand() {
      //differentialDrive.curvatureDrive(0.6, 0.4, false);
    }
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    //this is one way for you to make a auto chooser command work
    //this is not my go to way(ill show mine soon), but it does work and its simple
      switch (m_autoSelected) {
        case kCustomAuto:
        //Custom Auto Goes Here
        //here Should be An if-then Statement
        break;
        case kDefaultAuto:
        Default:
        //Default Auto Goes here
        //This Will Also be An if-Then Statment, but make this less than Three lines of code(For a challenge, not required
        break;
      }
    
      //this is my go to way
      autoChooser.addOption("Auto Path 1", null);
      //the issue with this is that i cannot show how it works within a timed robot because it uses commands, so for now stick to switch and case


  
                    /*example code*/
    /*  switch (m_autoSelected) {
      case kCustomAuto:
      if (timer.get() < 2) {
        shooterMotor.set(1);
      } else if (timer.get() < 4 && timer.get() > 2) {
        differentialDrive.arcadeDrive(0, 0.4);
      } else if (timer.get() < 6 && timer.get() > 4) {
        differentialDrive.arcadeDrive(0.6, 0);
      } else {
        differentialDrive.stopMotor();
      }
        break;
      case kDefaultAuto:
      default:
        if (timer.get() < 10) {
          differentialDrive.arcadeDrive(0.2, 0);
        } else {
          differentialDrive.arcadeDrive(0, 0.4);
        }
        break;
    }
    */
  }
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    timer.stop();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //There are three ways to controll a differential Drive Train, these being Arcade Drive, Tank Drive, And Curvature Drive
    
    //How this command Works
    //first by establishing the diff. drive motors(Differentiall Drive)
    //then you say which type of drive you want(This case its arcade drive)
    //arcade drive works like this arcadeDrive(Double xSpeed, double yspeed)
    //this means that whatever you put in the first slot will input speed, and the second will controll turning speed
      differentialDrive.arcadeDrive(kPlaneJoystick.getY() * 0.4, kPlaneJoystick.getX() * 0.4);
      kMultiMotorDrive.arcadeDrive(-kXbController.getLeftY() * 0.4, kXbController.getRightX() * 0.4);


    //this is how you do Tank Drive
      differentialDrive.tankDrive(-kXbController.getLeftY() * 0.6, -kXbController.getRightY() * 0.6);
    //notice the differences?

    //this is how you do curvature Drive
      kMultiMotorDrive.curvatureDrive(-kXbController.getLeftY() * 0.6, kXbController.getRightX() * 0.5, kXbController.getXButton());
    //curvature is differnt from arcade becuase it doesnt allow for the robot to turn in place(Unless pressing X), rather it turns like a car does



    //This is how you see the encoders date
    //Ignore this because its usless here :)
    SmartDashboard.putNumber("Left Encoder Distance", leftEncoder.getDistance());
    SmartDashboard.putNumber("Left Encoder Rate", leftEncoder.getRate());
    SmartDashboard.putNumber("Right Encoder Distance", rigthEncoder.getDistance());
    SmartDashboard.putNumber("Right Encoder Rate", rigthEncoder.getRate());


  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    differentialDrive.stopMotor();
    kMultiMotorDrive.stopMotor();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}
//pp
}

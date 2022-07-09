

package frc.robot;

import java.sql.Time;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**                                                  Welcome To Team 2531 "RoboHawks" Intro To Java Programming!
*                                        This Page and A student will help lead you through the process of making a operate!
*
*                  My advice for programming is that the hard part is memorization of the words, and it can be easy to forget what does what. 
*                  also theres no such thing as a perfect programmer, everyone makes mistakes, so never be disheartened if your code doesnt work.
*                  final thing, if you ever need help, dont be shy to ask; Robotics kids will be your #1 recource and will answer your question(s)
*
*                                     --Thank you for reading this ramble of mine
*                                     --Kaden Morton, RoboHawks Captain
*
*
*
*
* NOTE: The theme can be changed so that you can read it better, if this is hard to read, ->
* Use Ctrl+Shift+P to open the Command prompt, then type theme
* Do not do this unless a member of the robohawks has shown you what to do
*
*
*
*
*
*
*
*
*  How this page will work : There are some less cool things about robotics programming, so i have already filled those out to keep this fun
*  Here are the steps i use to make the Robot, Ill say if there already Done :)
*
*  #1 Define Motors, Encoders, Drivetrains, MotorControllerGroups, and Controllers(This is done cause its boring) ->
*  #2
*
*/



public class Robot extends TimedRobot {
  //Ignore this Stuff, Its Booooooring(But very very needed!) :(
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //this creates a controller to be used in TeleOp
  private XboxController gamepad = new XboxController(0);

  //this creates a timer to be used in Auto code
  private Timer timer = new Timer();

  //these sould be TalonFX but i cant install the pheonix library cause norton security
  private Talon frontLeft = new Talon(32);
  private Talon frontRight = new Talon(31);
  private Talon backRight = new Talon(30);
  private Talon backLeft = new Talon(33);

  //this connects the motors so that differential drive works
  private MotorControllerGroup leftGroup = new MotorControllerGroup(frontLeft, backLeft);
  private MotorControllerGroup rightGroup = new MotorControllerGroup(frontRight, backRight);

  //this enables mecanum drive using all four wheels independently
  private MecanumDrive mecanumDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
  //this enables differential drive all four wheels dependently, used for 'basic' drives
  private DifferentialDrive differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
  
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    leftGroup.setInverted(true);
    rightGroup.setInverted(false);
  }

  

  @Override
  public void robotPeriodic() {

  }

  


  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    timer.reset();
    timer.start();
  }

 //here is where you will actually have fun programming


 //Explanation : Here is the autonomous command, Every Year; the first 15 seconds of every match is a period called autonomous period
 //during this, the robots all run on pre-programmed code, no human input
 //this is what I(Kaden) find facinating
 //Here is one of our teams autos: "https://www.youtube.com/embed/kirFwqO6Rp4?start=5" 

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        //Custom code is what will run when "Custom Auto" is selected
        //HINT: use an if() {then} statement to run the code and use the timer.get() function as a parameter


        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        //Default code will run when "Default Auto" is selected
        //Ill make a default code so that you can use a template to make a custom Code!
      if (timer.get() < 2) {
        differentialDrive.curvatureDrive(0.4, 0.2, false);
      } else {
        differentialDrive.curvatureDrive(0, 0.1, true);
      }
        break;
      //What this does
      //the first thing the code does is it gets the timer(means that it tells the robot what time it is)
      //and it allows the parameter to be active for two seconds.
      //when the timer is less than two, it will drive forward and turn at the same time, making a curved path
      //in the else statement, it stops the motors and prevents the robot from driving
    }
  }



  @Override
  public void teleopInit() {
    timer.stop();
  }


  @Override
  public void teleopPeriodic() {
    //so here is where you will want to define HOW the robot drives. I will give you what to put, but i wont tell you how to assemble it


    //HINT: Java uses the ".(DOT)" operator, this is used to Connect a variable to a command, ex: variable.command;
    //mecanumDrive : this is the VARIABLE for all the motors and this controlls all the motors; but it must be told how to drive
    //ONLY USE IF DOING MECANUM DRIVE

    //differentialDrive : this VARIABLE will group the left and right side motors to allow it to drive using Arcade, Tank, Or Curvature
    //ONLY USE IF DOING ARCADE, TANK, OR CURVATURE

    //arcadeDrive : COMMAND where on input controlls speed and one controlls rotation

    //tankDrive : COMMAND where both sticks controll speed and turns by using a negative input on one stick

    //curvatureDrive : COMMAND similar to arcade drive, but it drives like a car, means it cannot rotate in place

    //driveCartesianDrive() : this is a COMMAND that allows for the robot to move Forwards, Sidways and Rotate

    //GamePad : this is the VARIABLE for the xbox controller you use

    //getLeftX/Y() or getRightX/Y() : This COMMAND tells the robot which of the joysticks axis' control what.

    //Example for joysticks ; If we wanted to have the left joystick move the robot forwards and backwards, we would do this

    //-> Variable.Command(-gamepad.getLeftY() * 0.2): that last little math part Kaden Will Do!


    //Note: Remember that up and down are y axis, and left and right are x axis
    //Note2: Either use MecanumDrive, Arcade, or Tank. DO NOT use multiple or else it will not Work!


    //Try it yourself :)










    

  }


  @Override
  public void disabledInit() {
    //nothing goes here!
  }

  @Override
  public void disabledPeriodic() {
    //Too Far go back up!
  }
}























//You Found Kadens Easter Egg 🥚:) its a special drive system!
//differentialDrive.arcadeDrive((-gamepad.getLeftTriggerAxis() + gamepad.getRightTriggerAxis()) * 0.2, gamepad.getRightX() * 0.2);
//but seriously why are you down this far, go back up


/**                𝑶𝒌 𝑰 𝑷𝒖𝒍𝒍 𝒖𝒑
*            ⢀⣞⣆⢀⣠⢶⡄
*   ⢀⣀⡤⠤⠖⠒⠋⠉⣉⠉⠹⢫⠾⣄⡀
* ⢠⡏⢰⡴   ⠉⠙⠟⠃    ⠈⠙⠦⣄⡀⢀⣀⣠⡤⠤⠶⠒⠒⢿⠋⠈ ⣒⡒⠲⠤⣄⡀
*⢸ ⢸⠁                  ⠈⠉ ⠴⠂⣀  ⣴⡄⠉⢷⡄⠚ ⢤⣒⠦⠉⠳⣄⡀
*⠸⡄⠼⠦                                  ⣄⡂⠠⣀⠐⠍⠂⠙⣆
* ⠙⠦⢄⣀⣀⣀⣀⡀ ⢷ ⢦                           ⠰⡇⠠⣀⠱⠘⣧
*        ⠈⠉⢷⣧⡄⢼ ⢀                           ⠈ ⡈ ⢄⢸⡄
*            ⠙⣿⡀⠃⠘⠂⠲⡀                        ⠙ ⡈⢘⡇
*             ⠈⢫⡑⠣⠰ ⢁⢀⡀                        ⠁⣸⠁
*               ⠙⣯⠂⡀⢨ ⠃           ⢀⡆⣾⡄     ⣀⠐⠁⡴⠁
*                ⠈⣧⡈⡀⢠⣧⣤⣀⣀⡀⢀⡀  ⢀⣼⣀⠉⡟ ⢀⡀⠘⢓⣤⡞⠁
*                   ⢺⡁⢁⣸⡏    ⠁ ⠉⠉⠁⠹⡟⢢⢱ ⢸⣷⠶⠻⡇
*                  ⢈⡏⠈⡟⡇           ⠑⢄⠁ ⠻⣧  ⣹⠁
*              ⣀⣀⡤⠚⠃⣰⣥⠇           ⢀⣾⠼⢙⡷⡻ ⡼⠁
*            ⠈⠟⠿⡿⠕⠊⠉         ⣠⣴⣶⣾⠉⣹⣷⣟⣚⣁⡼⠁
*                              ⠉⠙⠋⠁
*     
*⠀             ⠸⣶⣦⡄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⢀⣀⣀⣀⡀⢀⠀⢹⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⣷⣄⠨⣿⣿⣿⡌⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣷⣿⣿⣿⣿⣿⣶⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⣠⣴⣾⣿⣮⣝⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠈⠉⠙⠻⢿⣿⣿⣿⣿⣿⣿⠟⣹⣿⡿⢿⣿⣿⣬⣶⣶⡶⠦⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⠀⣀⣢⣙⣻⢿⣿⣿⣿⠎⢸⣿⠕⢹⣿⣿⡿⣛⣥⣀⣀⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⠀⠈⠉⠛⠿⡏⣿⡏⠿⢄⣜⣡⠞⠛⡽⣸⡿⣟⡋⠉⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠾⠿⣿⠁⠀⡄⠀⠀⠰⠾⠿⠛⠓⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠠⢐⢉⢷⣀⠛⠠⠐⠐⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*⠀⠀⠀⠀⣀⣠⣴⣶⣿⣧⣾⠡⠼⠎⢎⣋⡄⠆⠀⠱⡄⢉⠃⣦⡤⡀⠀⠀⠀⠀
*⠀⠀⠐⠙⠻⢿⣿⣿⣿⣿⣿⣿⣄⡀⠀⢩⠀⢀⠠⠂⢀⡌⠀⣿⡇⠟⠀⠀⢄⠀
*⠀⣴⣇⠀⡇⠀⠸⣿⣿⣿⣿⣽⣟⣲⡤⠀⣀⣠⣴⡾⠟⠀⠀⠟⠀⠀⠀⠀⡰⡀
*⣼⣿⠋⢀⣇⢸⡄⢻⣟⠻⣿⣿⣿⣿⣿⣿⠿⡿⠟⢁⠀⠀⠀⠀⠀⢰⠀⣠⠀⠰
*⢸⣿⡣⣜⣿⣼⣿⣄⠻⡄⡀⠉⠛⠿⠿⠛⣉⡤⠖⣡⣶⠁⠀⠀⠀⣾⣶⣿⠐⡀
*⣾⡇⠈⠛⠛⠿⣿⣿⣦⠁⠘⢷⣶⣶⡶⠟⢋⣠⣾⡿⠃⠀⠀⠀⠰⠛⠉⠉⠀⠀⠀
*/
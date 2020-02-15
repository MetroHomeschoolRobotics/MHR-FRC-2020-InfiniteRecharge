/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.*;
import frc.robot.subsystems.*;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Talon;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.InvertType;
//import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;

  private Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  String gameData;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //CameraServer.getInstance().startAutomaticCapture();
    DriveSystemBase tankDrive = new TankDrive(
      new CANSparkMax(RobotMap.LeftFrontMotor, MotorType.kBrushless), 
      new CANSparkMax(RobotMap.RightFrontMotor, MotorType.kBrushless),
      new CANSparkMax(RobotMap.LeftRearMotor, MotorType.kBrushless),
      new CANSparkMax(RobotMap.RightRearMotor, MotorType.kBrushless));
    Intake intake = new Intake(new Spark(RobotMap.IntakeMotor));
    Shooter shooter = new Shooter(new CANSparkMax(RobotMap.ShooterMotor1, MotorType.kBrushless), new CANSparkMax(RobotMap.ShooterMotor2, MotorType.kBrushless));
    Magazine magazine = new Magazine(new TalonSRX(RobotMap.MagazineMotor));
    ControlPanel controlPanel = new ControlPanel(new Spark(RobotMap.ControlPanelMotor));
   
    m_oi = new OI(tankDrive, intake, shooter, magazine, controlPanel);
    m_oi.init();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    CommandScheduler.getInstance().run();
    m_oi._driveTank.cancel();
   // m_oi._runIntake.cancel();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_chooser.getSelected();
    m_autonomousCommand = m_oi.getAutonomousCommand();
    m_oi._driveTank.cancel();
   // m_oi._runIntake.cancel();
  
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  /*@Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }*/

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    //instantiate drive command
    m_oi._driveTank.schedule();
    //m_oi._runIntake.schedule();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();


gameData = DriverStation.getInstance().getGameSpecificMessage();
if(gameData.length() > 0)
{
  switch (gameData.charAt(0))
  {
    case 'B' :
      //Blue case code
//      System.out.println("Game Data BLUE");
      break;
    case 'G' :
      //Green case code
//      System.out.println("Game Data GREEN");
      break;
    case 'R' :
      //Red case code
//      System.out.println("Game Data RED");
      break;
    case 'Y' :
      //Yellow case code
//      System.out.println("Game Data YELLOW");
      break;
    default :
      //This is corrupt data
//      System.out.println("Game Data BLANK");
      break;
  }
} else {
  //Code for no data received yet
//  System.out.println("Game data NULL");
}
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}

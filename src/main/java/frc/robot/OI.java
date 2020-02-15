/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.pixy2.Pixy2;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  Pixy2 _i2cPixy2 = null;
  Pixy2 _spiPixy2 = null;

  private DriveSystemBase _tankDrive;
  private Intake _intake;
  private Shooter _shooter;
  private Magazine _magazine;
//  private ControlPanel _controlPanel;
  CommandBase _autonomousCommand;
  CommandBase _driveTank;
  CommandBase _runIntake;
  CommandBase _runShooter;
  CommandBase _runMagazine;
  CommandBase _runControlPanel;
  CommandBase _reverseMagazine;
  CommandBase _reverseIntake;
  CommandBase _driveLimelight;
  SendableChooser<CommandBase> _autoChooser = new SendableChooser<>();

  SendableChooser<CommandBase> _i2cPixyChooser = new SendableChooser<>();
  SendableChooser<CommandBase> _spiPixyChooser = new SendableChooser<>();


  public OI(Pixy2 i2cPixy2, Pixy2 spiPixy2, DriveSystemBase tankDrive, Intake intake, Shooter shooter, Magazine magazine, ControlPanel controlPanel){
    _i2cPixy2 = i2cPixy2;
    _spiPixy2 = spiPixy2;
    _tankDrive = tankDrive;
    _intake = intake;
    _shooter = shooter;
    _magazine = magazine;
    //_controlPanel = controlPanel;
  }
  
  public void init() {
    Joystick driverControl = new Joystick(0);
    Joystick manipulatorControl = new Joystick(1);
    _driveTank = new DriveTank(_tankDrive, driverControl, manipulatorControl);
    //_runIntake = new RunIntake(_intake, driverControl);
   // _shooterAxis = new Joystick(driverControl, 3);
    JoystickButton intakeButton = new JoystickButton(manipulatorControl, 5);
    intakeButton.whileHeld(new RunIntake(_intake, manipulatorControl));
    JoystickButton shootButton = new JoystickButton(manipulatorControl, 1);
    shootButton.toggleWhenPressed(new RunShooter(_shooter)); //a
    JoystickButton magazineButton = new JoystickButton(manipulatorControl, 2);
    magazineButton.whileHeld(new RunMagazine(_magazine));
    JoystickButton reverseMagazineButton = new JoystickButton(manipulatorControl, 4);
    reverseMagazineButton.whileHeld(new ReverseMagazine(_magazine));
    JoystickButton reverseIntakeButton = new JoystickButton(manipulatorControl, 6);
    reverseIntakeButton.whileHeld(new ReverseIntake(_intake, manipulatorControl));
/*    JoystickButton controlPanelButton = new JoystickButton(driverControl, 8); // on the start button
    controlPanelButton.toggleWhenPressed(new RunControlPanel(_controlPanel));*/
    JoystickButton targetButton = new JoystickButton(driverControl, 1);
    targetButton.toggleWhenPressed(new DriveLimelight(_tankDrive));


    _tankDrive.setDefaultCommand(_driveTank);

   if (_i2cPixy2 != null){
      System.out.println("Adding Dashboard Options...");
      _i2cPixyChooser.setDefaultOption("Check Version", new SendCheckVersion(_i2cPixy2));
      _i2cPixyChooser.addOption("Get Biggest Block", new SendGetBiggestBlock(_i2cPixy2));
      _i2cPixyChooser.addOption("Find Color Blocks", new FindColorBlocks(_i2cPixy2));
      _i2cPixyChooser.addOption("Lamp Off", new SendLED(_i2cPixy2, false, 0, 0, 0));
      _i2cPixyChooser.addOption("Lamp On (Red)", new SendLED(_i2cPixy2, true, 255, 0, 0));
      _i2cPixyChooser.addOption("Lamp On (Green)", new SendLED(_i2cPixy2, true, 0, 255, 0));
      _i2cPixyChooser.addOption("Lamp On (Blue)", new SendLED(_i2cPixy2, true, 0, 0, 255));
      _i2cPixyChooser.addOption("Lamp On (Purple)", new SendLED(_i2cPixy2, true, 200, 30, 255));
      SmartDashboard.putData("I2C Pixy Command", _i2cPixyChooser);
      SmartDashboard.putData("Send I2C Command", new ExecuteChooser(_i2cPixyChooser));
    }

    if (_spiPixy2 != null){
      _spiPixyChooser.setDefaultOption("Check Version", new SendCheckVersion(_spiPixy2));
      _spiPixyChooser.addOption("Get Biggest Block", new SendGetBiggestBlock(_spiPixy2));
      _spiPixyChooser.addOption("Find Color Blocks", new FindColorBlocks(_spiPixy2));
      _spiPixyChooser.addOption("Lamp Off", new SendLED(_spiPixy2, false, 0, 0, 0));
      _spiPixyChooser.addOption("Lamp On (Red)", new SendLED(_spiPixy2, true, 255, 0, 0));
      _spiPixyChooser.addOption("Lamp On (Green)", new SendLED(_spiPixy2, true, 0, 255, 0));
      _spiPixyChooser.addOption("Lamp On (Blue)", new SendLED(_spiPixy2, true, 0, 0, 255));
      _spiPixyChooser.addOption("Lamp On (Purple)", new SendLED(_spiPixy2, true, 200, 30, 255));
      SmartDashboard.putData("SPI Pixy Command", _spiPixyChooser);
      SmartDashboard.putData("Send SPI Command", new ExecuteChooser(_spiPixyChooser));
    }

  SmartDashboard.putData("AutoMode", _autoChooser);
}
  
  public CommandBase getAutonomousCommand(){
    return _autoChooser.getSelected();
  }
  
  public CommandBase getDriveCommand(){
    return _driveTank;
  }
  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by its isFinished method.
  // button.whenReleased(new ExampleCommand());
}

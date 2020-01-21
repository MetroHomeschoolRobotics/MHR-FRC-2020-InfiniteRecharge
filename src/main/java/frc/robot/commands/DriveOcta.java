/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystemBase;
//import frc.robot.subsystems.OctaDrive;


public class DriveOcta extends Command {
  private DriveSystemBase _octaDrive;
  private Joystick _driverControl;
  private Joystick _manipulatorControl;  
  
  double threshold = 0.1;
  public DriveOcta(DriveSystemBase octaDrive, 
  Joystick driverControl, 
  Joystick manipulatorControl) {
      //requires(octaDrive);
      _octaDrive = octaDrive;
      _driverControl = driverControl;
      _manipulatorControl = manipulatorControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     double total = Math.abs(_driverControl.getRawAxis(0)) + Math.abs(_driverControl.getRawAxis(1)) + Math.abs(_driverControl.getRawAxis(2)) + Math.abs(_driverControl.getRawAxis(3));
     if (total > threshold*2) {
       _octaDrive.move(
           _driverControl.getRawAxis(0),
           _driverControl.getRawAxis(1),
           _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
     } else {
       _octaDrive.move(
           _manipulatorControl.getRawAxis(0),
           _manipulatorControl.getRawAxis(1),
           _manipulatorControl.getRawAxis(2) - _manipulatorControl.getRawAxis(3));

     }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

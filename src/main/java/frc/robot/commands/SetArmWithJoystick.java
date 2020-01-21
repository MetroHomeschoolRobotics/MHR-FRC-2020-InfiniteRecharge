/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetArmWithJoystick extends Command {
  private Arm _arm;
  private Joystick _manipulatorControl;
  private double _threshold = 0.1;
  public SetArmWithJoystick(Arm arm, Joystick manipulatorControl) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _arm = arm;
    _manipulatorControl = manipulatorControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rate = _manipulatorControl.getRawAxis(1);
    if(Math.abs(rate)<_threshold){
      rate = 0;
    }
    _arm.setArmMotor(rate);
    SmartDashboard.putNumber("Arm command rate", rate);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    _arm.setArmMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
private TalonSRX _armMotor;
private boolean joystickOverride = false;
private boolean encoderOverride = false;
//private Encoder _armMotorEncoder;
//positioning

public Arm(TalonSRX armMotor){
  _armMotor = armMotor;
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void overrideJoystick(boolean ignore){
    if (ignore) {
      joystickOverride = true;
    } else {
      joystickOverride = false;
    }
  }

  //creates encoder override variable
  public void setOverride(boolean active){
    if (active) {
      encoderOverride = true;
    } else {
      encoderOverride = false;
    }
    SmartDashboard.putBoolean("encoderoverride", active);
  }

  public void setArmMotor(double speed) {
    double val = speed;
    double angle = getArmAngle();
    //limits run only if encoderOverride is false; by activating encoderOverride, driver circumvents limits
    if(!encoderOverride){
      if (angle < 0 && speed > 0){
        val = 0;
      }  else if (angle > 800 && speed < 0){
        val = 0;
      }
      if (angle > 230 && speed == 0){
        val = .05;
      }
    }
    if (joystickOverride && angle < 70) {
        val = -0.4;
    }
    _armMotor.set(ControlMode.PercentOutput, val);
    SmartDashboard.putNumber("arm subsystem val", val);
  }
  public double getArmAngle() { 
  //arm encoder information at:
  //search "wpilib java declaring encoders"
     //float degree = _armMotorEncoder.get();
      double degree = 0;
     SmartDashboard.putNumber("arm distance", degree);
     return degree;
  }

  public void resetArmEncoder() {
    //_armMotorEncoder.reset();
    // SmartDashboard.putString("Resetting?", "Yes");
  }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  private Arm _arm;
  private TalonSRX _liftMotor;
  private DigitalInput _manipulatorBottomSwitch;
  private DigitalInput _manipulatorTopSwitch;
  private boolean encoderOverride;
  private boolean joystickOverride;
  //positioning

  public Lift (Arm arm, TalonSRX liftMotor, DigitalInput manipulatorBottomSwitch, DigitalInput manipulatorTopSwitch){
    _arm = arm;
    _liftMotor = liftMotor;
    _manipulatorBottomSwitch = manipulatorBottomSwitch;
    _manipulatorTopSwitch = manipulatorTopSwitch;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public int getLiftDistance(){
    // return _positioning.getLiftDistance();
    //double pos = _liftMotor.getPosition();//getSelectedSensorPosition() in C++
    //return pos;
    //encoder methods can be researched at https://www.ctr-electronics.com/downloads/api/java/html/functions_g.html
    return 0;
  }

  public void resetLiftEncoder(){
    //_liftMotor.setPosition(0);
  }

  public void setOverride(boolean active){
    if (active){
      encoderOverride = true;
    } else {
      encoderOverride = false;
    }
  }

  public void setLiftMotor(double speed){
    int pos = getLiftDistance();
    if (!encoderOverride) {
      if (pos > 28000 && speed > 0){
        speed = 0;
      } else if (speed > 0 && pos > 23000){
        speed /= 2;
      } else if (speed < 0 && pos < 12000){
        speed /= 3;
      }
      if (speed < -0.6){
        speed = -0.6;
      }
    }
    if (!_manipulatorBottomSwitch.get()) {
      resetLiftEncoder();
    }
    if (speed < 0 && !_manipulatorBottomSwitch.get()){
        // Bottom Limit Switch Hit -- STOP!!
      speed = 0;
      } else if (speed > 0 && !_manipulatorTopSwitch.get()){
      // Top Limit Switch Hit -- STOP!!
      speed = 0;
      }
  
    if (pos > 3500 && speed == 0){
        speed = .15;
    } else if (speed == 0){
        speed = .05;
      }
    if (_arm.getArmAngle() < 70 && (speed > 0.15 || speed < 0)) {
      _arm.overrideJoystick(true);
      if (pos < 3500) {
        _liftMotor.set(ControlMode.PercentOutput, 0.05);
      } else {
      _liftMotor.set(ControlMode.PercentOutput, 0.15);
      }
    } else {
      _arm.overrideJoystick(false);
      _liftMotor.set(ControlMode.PercentOutput, speed);
      SmartDashboard.putNumber("lift subsystem speed", speed);
    }
  }
  }


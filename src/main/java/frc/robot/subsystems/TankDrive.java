/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import edu.wpi.first.wpilibj.Spark;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends DriveSystemBase {
 /* private TalonSRX _frontLeft;
  private TalonSRX _frontRight;
  private TalonSRX _rearLeft;
  private TalonSRX _rearRight;*/
  private CANSparkMax _frontLeft;
  private CANSparkMax _frontRight;
  private CANSparkMax _rearLeft;
  private CANSparkMax _rearRight;

 // public TankDrive(TalonSRX frontLeft, TalonSRX frontRight, TalonSRX rearLeft, TalonSRX rearRight){
  public TankDrive(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax rearLeft, CANSparkMax rearRight){
    _frontLeft = frontLeft;
    _frontRight = frontRight;
    _rearLeft = rearLeft;
    _rearRight = rearRight;
  // private CANSparkMax frontLeft;
  // private CANSparkMax frontRight;
  // private CANSparkMax rearLeft;
  // private CANSparkMax rearRight;

  //public TankDrive(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax rearLeft, CANSparkMax rearRight){
/*    this._frontLeft = frontLeft;
    this._frontRight = frontRight;
    this._rearLeft = rearLeft;
    this._rearRight = rearRight;*/
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void move(double x, double y, double z){
    /*_frontLeft.set(ControlMode.PercentOutput, (y-x));
    _rearLeft.set(ControlMode.PercentOutput, (y-x));
    _frontRight.set(ControlMode.PercentOutput, (-y-x));
    _rearRight.set(ControlMode.PercentOutput, (-y-x));*/
    _frontLeft.set(-y+x);
    _rearLeft.set(-y+x);
    _frontRight.set(y+x);
    _rearRight.set(y+x);
    
    SmartDashboard.putNumber("TankDrive X", x);
    SmartDashboard.putNumber("TankDrive Y", y);
    SmartDashboard.putNumber("TankDrive Z", z);
  }
}

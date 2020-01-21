/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Spark;
//import com.revrobotics.CANSparkMax;
import frc.robot.subsystems.*;

public class OctaDrive extends DriveSystemBase {
  private DriveSystemBase _tankDrive;
  private DriveSystemBase _mechDrive;
  private boolean _isTank = true;
  private DoubleSolenoid _driveSwitch;
  
  //public OctaDrive (Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight, TankDrive tankDrive, MechDrive mechDrive){
    public OctaDrive (DriveSystemBase tankDrive, DriveSystemBase mechDrive, DoubleSolenoid driveSwitch){
    _tankDrive = tankDrive;
    _mechDrive = mechDrive;
    _driveSwitch = driveSwitch;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void move(double x, double y, double z){
    if (_isTank){
       _tankDrive.move(x,y,0);
    } else {
        _mechDrive.move(x,y,z);
    }

    SmartDashboard.putNumber("OctaDrive X", x);
    SmartDashboard.putNumber("OctaDrive Y", y);
    SmartDashboard.putNumber("OctaDrive Z", z);
  }

  public void switchMode(){

    _isTank = !_isTank;
  
     if (_isTank){
       _driveSwitch.set(DoubleSolenoid.Value.kReverse);
     } else {
      _driveSwitch.set(DoubleSolenoid.Value.kForward);
     }

     SmartDashboard.putBoolean("OctaDrive 'isTank'", _isTank);
  
  }

  public boolean isTankDrive() {
    return _isTank;
  }
 }

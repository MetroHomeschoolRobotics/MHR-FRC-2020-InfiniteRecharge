/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Spark;
//import com.ctre.phoenix.motorcontrol.ControlMode;

public class ControlPanel extends SubsystemBase {
  public Spark _controlPanelMotor;
    /**
   * Creates a new Control Panel Controller.
   */
  public ControlPanel(Spark controlPanelMotor) {
    _controlPanelMotor = controlPanelMotor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setControlPanel(double speed){
    _controlPanelMotor.set(-speed);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {
  private Spark _intakeMotor;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public Intake(Spark intakeMotor) {
  _intakeMotor = intakeMotor;
}
//@Override
  public void periodic(){
    
  }

  public void runTheIntake(double speed) {
    _intakeMotor.set(speed);
  }
}

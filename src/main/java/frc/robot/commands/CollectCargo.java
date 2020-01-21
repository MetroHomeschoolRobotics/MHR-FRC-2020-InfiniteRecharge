/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoSystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CollectCargo extends Command {
  private CargoSystem _cargoSystem;
  public CollectCargo(CargoSystem cargoSystem) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _cargoSystem = cargoSystem;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    _cargoSystem.setCargoMotor(-1);
    SmartDashboard.putString("Cargo Command", "Collecting");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    _cargoSystem.setCargoMotor(0);
    SmartDashboard.putString("Cargo Command", "Ended");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

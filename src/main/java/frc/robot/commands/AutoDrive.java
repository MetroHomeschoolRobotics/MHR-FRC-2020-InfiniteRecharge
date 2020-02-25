/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystemBase;

public class AutoDrive extends CommandBase {
  DriveSystemBase _tankDrive;
  double x = 0;
  double y = .25;
  double z = 0;
  double seconds = 1.5;
  /**
   * Creates a new AutoDrive.
   */
  public AutoDrive(DriveSystemBase tankDrive) {
    _tankDrive = tankDrive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("AutoDriving", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _tankDrive.move(x, y, z);
    Timer.delay(seconds);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("AutoDriving", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

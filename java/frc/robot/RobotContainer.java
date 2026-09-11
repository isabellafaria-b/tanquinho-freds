package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private final Joystick fred = new Joystick(0);
  private final DriveSubsystem DriveSubsystem= new DriveSubsystem();

  public RobotContainer() {
    DriveSubsystem();
  }

  private void DriveSubsystem() {
  }

  public Command getAutonomousCommand(){
    return null;
  }
}
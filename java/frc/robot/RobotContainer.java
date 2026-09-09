package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
  private final Joystick fred = new Joystick(0);
  private final DriveSubsystem DriveSubsystem= new DriveSubsystem();

  public RobotContainer() {
    DriveSubsystem();
  }

  private void DriveSubsystem() {
  }
}

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  public static final Joystick fred = new Joystick(0);
  public final DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();

  public RobotContainer() {
  }

  public Command getAutonomousCommand(){
    return null;
  }
}
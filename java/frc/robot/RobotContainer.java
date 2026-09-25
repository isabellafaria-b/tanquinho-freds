package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Locomocao;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  public static final Joystick fred = new Joystick(OperatorConstants.fred);
  public static final DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();
  public static final Locomocao locomocao = new Locomocao(fred, driveTrain);

  public RobotContainer() {
    locomocao.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(locomocao);
  }

  public Command getAutonomousCommand(){
    return new Autos(locomocao, driveTrain);
  }
}
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Braco;
import frc.robot.commands.Garra;
import frc.robot.commands.Locomocao;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.EncoderSubsystem;
import frc.robot.subsystems.GarraSubsystem;
import frc.robot.subsystems.InfraVermelho;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // Joystick e driveTrain
  public static final Joystick fred = new Joystick(OperatorConstants.fred);
  public static final DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();

  // Sensores e locomoção
  public static final InfraVermelho infraVermelho = new InfraVermelho(driveTrain);
  public static final EncoderSubsystem encoder = new EncoderSubsystem();
  public static final Locomocao locomocao = new Locomocao(fred, driveTrain, infraVermelho, encoder);

   // Garra / braco
  public static final GarraSubsystem garra = new GarraSubsystem();
  public static final Garra garrinha = new Garra(garra, fred);
  public static final ArmSubsystem braco = new ArmSubsystem();
  public static final Braco bracinho = new Braco(braco, fred);

  public RobotContainer() {
    locomocao.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(locomocao);

    bracinho.addRequirements(braco);
    braco.setDefaultCommand(bracinho);
    garrinha.addRequirements(garra);
    garra.setDefaultCommand(garrinha);
  }

  public Command getAutonomousCommand(){
    return new Autos(locomocao, driveTrain, encoder);
  }
}
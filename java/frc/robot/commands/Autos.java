package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.SensorConstants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.EncoderSubsystem;

public class Autos extends Command {
    private final Locomocao locomocao;
    private final DriveTrainSubsystem locomotionSubsystem;
    private final EncoderSubsystem encoder;
    private final double distanciaAlvo = SensorConstants.distanciaAlvo;

  public Autos(Locomocao loc, DriveTrainSubsystem drive, EncoderSubsystem encoder) {
    locomocao = loc;
    locomotionSubsystem = drive;
    this.encoder = encoder;

    addRequirements(drive, encoder);
  }

  @Override
  public void initialize() {
    encoder.resetEncoder();
  }

  @Override
  public void execute() {
    if(encoder.pegarDistancia() < distanciaAlvo){
        locomotionSubsystem.Drive(0.5, 0.5);
    } else {
      locomotionSubsystem.Drive(0, 0);
    }

    locomocao.dashboard();    
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    if (encoder.pegarDistancia() >= distanciaAlvo){
      return true;
    }
    return false;
  }
}
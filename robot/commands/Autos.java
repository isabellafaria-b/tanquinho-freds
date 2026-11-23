package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.SensorConstants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Autos extends Command {
    private final Locomocao locomocao;
    private final DriveTrainSubsystem locomotionSubsystem;
    private final double distanciaAlvo = SensorConstants.distanciaAlvo;

  public Autos(Locomocao loc, DriveTrainSubsystem drive) {
    locomocao = loc;
    locomotionSubsystem = drive;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    locomotionSubsystem.resetEncoder();
  }

  @Override
  public void execute() {
    if(locomotionSubsystem.pegarDistancia() < distanciaAlvo){
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
    if (locomotionSubsystem.pegarDistancia() >= distanciaAlvo){
      return true;
    }
    return false;
  }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Autos extends Command {
    public static Timer isabella = new Timer();
    Locomocao locomocao;
    DriveTrainSubsystem subsystem;

  public Autos(Locomocao loc, DriveTrainSubsystem drive) {
    locomocao = loc;
    subsystem = drive;

    addRequirements(drive);
  }

  @Override
  public void initialize() {
    isabella.reset();
    isabella.start();
  }

// 20ms ATUALIZA
  @Override
  public void execute() {
    if(isabella.get() <= 2){
        subsystem.Drive(1, 1);
    } else {
      isabella.stop();
      subsystem.Drive(0, 0);
    }

    locomocao.dashboard();    
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    if (isabella.get() >= 2){
      return true;
    }
    return false;
  }
}
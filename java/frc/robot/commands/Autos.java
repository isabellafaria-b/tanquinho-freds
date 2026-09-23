package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calculo;

public class Autos extends Command {
    Timer isabella = new Timer();
    Locomocao locomocao;

  public Autos() {
  }

  @Override
  public void initialize() {
    isabella.reset();
  }

  @Override
  public void execute() {
    isabella.start();

    if(isabella.get() <= 2){
        Calculo.velEsq = 1;
        Calculo.velDir = 1;
    }

    locomocao.dashboard();    
  }

  @Override
  public void end(boolean interrupted) {
    isabella.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

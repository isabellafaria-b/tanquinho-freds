package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class Locomocao extends Command {
  // Variáveis
  boolean botaoA, botaoB, botaoC, botaoD;
  double trigelaD, trigelaE;
  double x1, y1; double x2, y2;
  double velD,velE;

  //Joystick
    private Joystick fred;

  public Locomocao(Joystick fred) {
    this.fred = fred;
  }

  public void objetos(){
    botaoA = fred.getRawButton(1);
    botaoB = fred.getRawButton(2);
    botaoC = fred.getRawButton(3);
    botaoD = fred.getRawButton(4);
    
    // analogicos
      x1 = fred.getRawAxis(0);
      y1 = -fred.getRawAxis(1);
      x2 = fred.getRawAxis(4);
      y2 = -fred.getRawAxis(5);

    // triggers
      trigelaD = fred.getRawAxis(2);
      trigelaE = fred.getRawAxis(3);
      trigelaE *= -1;

      
  }

  public void stop(){
    
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}

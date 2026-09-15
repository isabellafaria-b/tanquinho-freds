package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calculo;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Locomocao extends Command {
  // Variáveis
  boolean botaoA, botaoB, botaoC, botaoD;
  double trigelaD, trigelaE;
  double x1, y1; double x2, y2;
  double velD, velE, velBotao; int angulo;

  // Deadzone
  private final double deadzone = OperatorConstants.deadzone;

  //Objetos
    private final Joystick fred;
    private final DriveTrainSubsystem drive;
    private final Calculo calculo = new Calculo();

  public Locomocao(Joystick fred, DriveTrainSubsystem subsystem) {
    this.fred = fred;
    drive = subsystem;

    addRequirements(subsystem);
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

    // POV
    angulo = fred.getPOV();
  }

  public void botoes(boolean botaoA, boolean botaoB, boolean botaoC, boolean botaoD){
     if (botaoA) {
      velBotao = 0.25;
    } else if(botaoB) {
      velBotao = 0.5;
    } else if (botaoC) {
      velBotao = 0.75;
    } else if (botaoD) {
      velBotao = 1;
    }
    }

  public void dashboard(){
   SmartDashboard.putBoolean("Botao A", botaoD);
   SmartDashboard.putBoolean("Botao B", botaoB);
   SmartDashboard.putBoolean("Botao C", botaoC);
   SmartDashboard.putBoolean("Botao D", botaoA);
   SmartDashboard.putNumber("Velocidade botao", velBotao);
   SmartDashboard.putNumber("Velocidade do motor direito", velD);
   SmartDashboard.putNumber("Velocidade do motor esquerdo", velE);
   SmartDashboard.putNumber("POV", angulo);
   SmartDashboard.putNumber("Trigger Direita", trigelaD);
   SmartDashboard.putNumber("Trigger Esquerda", trigelaE);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    calculo.Drive(velE, velD);

    calculo.calcEsq(x1, y1);
    calculo.calcDir(x2, y2);

    if(velD > deadzone && velE > deadzone){
      if(Calculo.hipotenusa > deadzone){
        calculo.analEsq(x1, y1, velBotao);
      } else if(Calculo.hipotenusa1 > deadzone){
      calculo.analDir(x2, y2, velBotao);
      } else if(trigelaD > deadzone && trigelaE > deadzone){
        calculo.triggers(trigelaD, trigelaE);
      } else if(fred.getPOV() != -1) {
        calculo.POV(velBotao, angulo);
      }
    } else {
      velE = 0; velD = 0; velBotao = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
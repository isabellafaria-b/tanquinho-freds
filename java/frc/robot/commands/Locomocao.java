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
  public static double trigelaE, trigelaD;
  public static double x1, y1; public static double x2, y2;

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
      trigelaD = fred.getRawAxis(3);
      trigelaE = fred.getRawAxis(2);

    // POV
    Calculo.angulo = fred.getPOV();
  }

  @Override
  public void execute() {
    objetos();
    drive.Drive(Calculo.velEsq, Calculo.velDir);

    if(Calculo.hipotenusa > deadzone){
      calculo.analEsq();
    } else if(Calculo.hipotenusa1 > deadzone){
      calculo.analDir(); 
    } else if(trigelaD > deadzone || trigelaE > deadzone){
      calculo.triggers();
    } else if(fred.getPOV() != -1) {
      calculo.POV();
    } else {
      Calculo.velEsq = 0;
      Calculo.velDir = 0;
    }

      botoes();
      calculo.calcEsq();
      calculo.calcDir();
      dashboard();
  }

  public void dashboard(){
   SmartDashboard.putBoolean("Botao A", botaoD);
   SmartDashboard.putBoolean("Botao B", botaoB);
   SmartDashboard.putBoolean("Botao C", botaoC);
   SmartDashboard.putBoolean("Botao D", botaoA);
   SmartDashboard.putNumber("Velocidade botao", Calculo.velBotao);
   SmartDashboard.putNumber("Vel do motor direito", Calculo.velDir);
   SmartDashboard.putNumber("Vel do motor esquerdo", Calculo.velEsq);
   SmartDashboard.putNumber("POV", Calculo.angulo);
   SmartDashboard.putNumber("Trigger Direita", trigelaD);
   SmartDashboard.putNumber("Trigger Esquerda", trigelaE);
  }
  
  public void botoes(){
     if (botaoA) {
      Calculo.velBotao = 0.25;
    } else if(botaoB) {
      Calculo.velBotao = 0.5;
    } else if (botaoC) {
      Calculo.velBotao = 0.75;
    } else if (botaoD) {
      Calculo.velBotao = 1;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
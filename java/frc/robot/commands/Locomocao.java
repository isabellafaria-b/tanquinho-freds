package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calculo;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.EncoderSubsystem;
import frc.robot.subsystems.InfraVermelho;

public class Locomocao extends Command {
  // Variáveis
  boolean botaoA, botaoB, botaoC, botaoD;
  public static double x1, y1; public static double x2, y2;
  public static double mediaVel;

  // Deadzone
  private final double deadzone = OperatorConstants.deadzone;

  //Objetos
    private final Joystick fred;
    private final DriveTrainSubsystem drive;
    private final Calculo calculo = new Calculo();
    private final EncoderSubsystem encoderS;
    private final InfraVermelho vermelhinho;

  public Locomocao(Joystick fred, DriveTrainSubsystem subsystem, InfraVermelho vermelinho, EncoderSubsystem encoderS) {
    this.fred = fred;
    this.drive = subsystem;
    this.vermelhinho = vermelinho;
    this.encoderS = encoderS;

    addRequirements(subsystem, vermelhinho, encoderS);
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

    // POV
    Calculo.angulo = fred.getPOV();
  }

  @Override
  public void execute() {
    objetos();
    botoes();

    calculo.calcEsq();
    calculo.calcDir();

    if(Calculo.hipotenusa > deadzone){
      calculo.analEsq();
    } else if(Calculo.hipotenusa1 > deadzone){
      calculo.analDir(); 
    } else if(fred.getPOV() != -1) {
      calculo.POV();
    } else {
      Calculo.velEsq = 0;
      Calculo.velDir = 0;
    }

    parar();
    dashboard();
  }

  public void parar(){
    //mediaVel = (Calculo.velEsq + Calculo.velDir) / 2;
    if(vermelhinho.temParede()){
      drive.Drive(Calculo.velEsq, Calculo.velDir);
    } else if(vermelhinho.temParede() == false){
      drive.Drive(Calculo.velEsq, Calculo.velDir);
    }
  }

  public void dashboard(){
   SmartDashboard.putBoolean("Botao A", botaoD);
   SmartDashboard.putBoolean("Botao B", botaoB);
   SmartDashboard.putBoolean("Botao C", botaoC);
   SmartDashboard.putBoolean("Botao D", botaoA);
   SmartDashboard.putBoolean("InfraVermelho", vermelhinho.temParede());
   SmartDashboard.putNumber("Movimento do braco", Calculo.velBraco);
   SmartDashboard.putNumber("Movimento da garra", Calculo.velGarra);
   SmartDashboard.putNumber("Velocidade botao", Calculo.velBotao);
   SmartDashboard.putNumber("Vel do motor direito", Calculo.velDir);
   SmartDashboard.putNumber("Vel do motor esquerdo", Calculo.velEsq);
   SmartDashboard.putNumber("POV", Calculo.angulo);
   SmartDashboard.putNumber("Encoder", encoderS.pegarDistancia());
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
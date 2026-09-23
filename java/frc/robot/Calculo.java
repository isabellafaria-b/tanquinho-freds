package frc.robot;

import frc.robot.commands.Locomocao;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Calculo extends DriveTrainSubsystem{
    // Variáveis
    public static double hipotenusa, hipotenusa1;
    double sen, sen1;
    private final double deadzone = Constants.OperatorConstants.deadzone;
    public static double velDir = 0, velEsq = 0;
    public static double velBotao; public static int angulo;

    // calculos analogicos
    public void calcEsq(){
        hipotenusa = Math.hypot(Locomocao.x1, Locomocao.y1);
        if(hipotenusa > 1){
          hipotenusa = 1;
        }
        sen = Locomocao.y1 / hipotenusa;
        Math.max(-1, Math.min(1, sen));
    }

    public void calcDir(){
        hipotenusa1 = Math.hypot(Locomocao.x2, Locomocao.y2);
        if(hipotenusa1 > 1){
            hipotenusa1 = 1;
        } 
        sen1 = Locomocao.y2 / hipotenusa1;
        Math.max(-1, Math.min(1, sen1));
    }

    //analogicos
    public void analEsq(){
        // movimentos diagonais
    if (Locomocao.x1 > deadzone && Locomocao.y1 > deadzone) { // eixo I
      velEsq = hipotenusa;
      velDir = sen;
    } else if (Locomocao.x1 < -deadzone && Locomocao.y1 > deadzone) { // eixo II
      velEsq = sen;
      velDir = hipotenusa;
    } else if (Locomocao.x1 < -deadzone && Locomocao.y1 < -deadzone) { // eixo III
      velEsq = sen;
      velDir = -hipotenusa;
    } else if (Locomocao.x1 > deadzone && Locomocao.y1 < -deadzone) { // eixo IV
      velEsq = -hipotenusa; 
      velDir = sen;
    }

    // movimentos verticais/horizontais
    else if(Locomocao.x1 < deadzone && Locomocao.y1 > deadzone){
      velEsq = hipotenusa;
      velDir = hipotenusa;
    } else if(Locomocao.x1 > deadzone && Locomocao.y1 < deadzone){
      velEsq = hipotenusa;
      velDir = 0;
    } else if(Locomocao.x1 < -deadzone && Locomocao.y1 < -deadzone){
      velEsq = -hipotenusa;
      velDir = -hipotenusa;
    } else if(Locomocao.x1 < -deadzone && Locomocao.y1 < deadzone){
      velEsq = 0;
      velDir = hipotenusa;
    }

      velEsq *= velBotao;
      velDir *= velBotao;
    }

    public void analDir(){
    // movimentos diagonais
    if (Locomocao.x2 > deadzone && Locomocao.y2 > deadzone){ // eixo I
      velEsq = hipotenusa1;
      velDir = sen1;
     } else if (Locomocao.x2 < -deadzone && Locomocao.y2 > deadzone) { // eixo II
      velEsq = sen1;
      velDir = hipotenusa1;
     } else if (Locomocao.x2 < -deadzone && Locomocao.y2 < -deadzone) { // eixo III
      velEsq =  sen1;
      velDir = -hipotenusa1;
     } else if (Locomocao.x2 > deadzone && Locomocao.y2 < -deadzone) { // eixo IV
      velEsq = -hipotenusa1;
      velDir = sen1;
     }

     // movimentos verticais/horizontais
     else if(Locomocao.x2 < deadzone && Locomocao.y2 > deadzone){
       velEsq = hipotenusa1;
       velDir = hipotenusa1;
     } else if(Locomocao.x2 > deadzone && Locomocao.y2 < deadzone){
       velEsq = hipotenusa1;
       velDir = 0;
     } else if(Locomocao.x2 < -deadzone && Locomocao.y2 < -deadzone){
       velEsq = -hipotenusa1;
       velDir = -hipotenusa1;
     } else if(Locomocao.x2 < -deadzone && Locomocao.y2 < deadzone){
       velEsq = 0;
       velDir = hipotenusa1;
     }

     velEsq *= velBotao;
     velDir *= velBotao;
  }

   public void triggers() {
    if (Locomocao.trigelaE > deadzone) {
      velDir = -Locomocao.trigelaE;
      velEsq = -Locomocao.trigelaE;
    } else if (Locomocao.trigelaD > deadzone) {
      velDir = Locomocao.trigelaD;
      velEsq = Locomocao.trigelaD;
    } else {
      velEsq = 0; velDir = 0;
    }

    velEsq *= velBotao;
    velDir *= velBotao;
  }

      public void POV() {
    switch (angulo) {
      case -1:
      velEsq = 0; velDir = 0;
      case 0: 
       velEsq = 1;
       velDir = 1;
       break;
      case 45:
       velEsq = 1;
       velDir = -0.5;
        break;
      case 90:
       velEsq = 1;
       velDir = 0;
        break;
      case 135:
       velEsq = -1;
       velDir = -0.5;
      case 180:
       velEsq = -1;
       velDir = -1;
        break;
      case 225:
       velEsq = -0.5;
       velDir = -1;
        break;
      case 270:
       velEsq = 0;
       velDir = 1;
        break;
      case 315:
       velEsq = -0.5;
       velDir = 1;
        break;
    }

    velEsq *= velBotao;
    velDir *= velBotao;
  }
}
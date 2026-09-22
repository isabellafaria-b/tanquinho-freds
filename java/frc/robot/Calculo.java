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
    }

    public void calcDir(){
        hipotenusa = Math.hypot(Locomocao.x2, Locomocao.y2);
        if(hipotenusa > 1){
            hipotenusa = 1;
        } 
        sen = Locomocao.y2 / hipotenusa;
    }

    //analogicos
    public double analEsq(){
        // movimentos diagonais
        if (Locomocao.x1 > deadzone && Locomocao.y1 > deadzone){
         velEsq = hipotenusa;
         velDir = hipotenusa - sen;
        } else if (Locomocao.x1 < -deadzone && Locomocao.y1 > deadzone) { // eixo II
         velEsq = hipotenusa + sen;
         velDir = hipotenusa;
        } else if (Locomocao.x1 < -deadzone && Locomocao.y1 < -deadzone) { // eixo III
         velEsq = -hipotenusa - sen;
         velDir = -hipotenusa;
        } else if (Locomocao.x1 > deadzone && Locomocao.y1 < -deadzone) { // eixo IV
         velEsq = -hipotenusa;
         velDir = -hipotenusa + sen;
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
        return Math.max(-1, Math.min(1, sen));
    }

    public double analDir(){
      // movimentos diagonais
      if (Locomocao.x2 > deadzone && Locomocao.y2 > deadzone){
       velEsq = hipotenusa1;
       velDir = hipotenusa1 - sen1;
      } else if (Locomocao.x2 < -deadzone && Locomocao.y2 > deadzone) { // eixo II
       velEsq = hipotenusa1 + sen1;
       velDir = hipotenusa1;
      } else if (Locomocao.x2 < -deadzone && Locomocao.y2 < -deadzone) { // eixo III
       velEsq = -hipotenusa1 - sen1;
       velDir = -hipotenusa1;
      } else if (Locomocao.x2 > deadzone && Locomocao.y2 < -deadzone) { // eixo IV
       velEsq = -hipotenusa1;
       velDir = -hipotenusa1 + sen1;
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
      return Math.max(-1, Math.min(1, sen1));
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
       velEsq = velBotao * 1;
       velDir = velBotao * 1;
       break;
      case 45:
       velEsq = velBotao * 1;
       velDir = velBotao * -0.5;
        break;
      case 90:
       velEsq = velBotao * 1;
       velDir = velBotao * 0;
        break;
      case 135:
       velEsq = velBotao * -1;
       velDir = velBotao * -0.5;
      case 180:
       velEsq = velBotao * -1;
       velDir = velBotao * -1;
        break;
      case 225:
       velEsq = velBotao * -0.5;
       velDir = velBotao * -1;
        break;
      case 270:
       velEsq = velBotao * 0;
       velDir = velBotao * 1;
        break;
      case 315:
       velEsq = velBotao * -0.5;
       velDir = velBotao * 1;
        break;
    }
  }
}
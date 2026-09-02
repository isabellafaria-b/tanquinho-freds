package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveSubsystem;

public class Calculo extends DriveSubsystem{
    // Variáveis
    double hipotenusa, hipotenusa1;  
    double sen, sen1;
    private final double deadzone = 0.4;
    double velDir = 0, velEsq = 0, velBotao = 0;
    int angulo;

    // Joystick
    Joystick fred = new Joystick(0);

    // drive
    @Override
    public void Drive(double velE, double velD){
        velEsq = velE;
        velDir = velD;
        
        df.set(ControlMode.PercentOutput, velD);
        ef.set(ControlMode.PercentOutput, velE);
    }

    // calculos analogicos
    public void calcEsq(double x1, double y1){
        hipotenusa = Math.hypot(x1, y1);
        if(hipotenusa > 1){
            hipotenusa = 1;
        } 
        sen = y1 / hipotenusa;
    }

    public void calcDir(double x2, double y2){
        hipotenusa = Math.hypot(x2, y2);
        if(hipotenusa > 1){
            hipotenusa = 1;
        } 
        sen = y2 / hipotenusa;
    }

    //analogicos
    public double analEsq(double x1, double y1){
        calcEsq(x1, y1);

        // movimentos diagonais
        if (x1 > deadzone && y1 > deadzone){
         velEsq = hipotenusa;
         velDir = sen;
        } else if (x1 < -deadzone && y1 > deadzone) { // eixo II
         velEsq = -sen;
         velDir = hipotenusa;
        } else if (x1 < -deadzone && y1 < -deadzone) { // eixo III
         velEsq = sen;
         velDir = -hipotenusa;
        } else if (x1 > deadzone && y1 < -deadzone) { // eixo IV
         velEsq = hipotenusa;
        velDir = sen;
        }

        // movimentos verticais/horizontais
        if(velEsq > 0.99 && velDir > 0.99) {
         velEsq = 1;
         velDir = 1;
        } else if(velEsq < -0.99 && velDir < -0.99) {
         velEsq = -1;
         velDir = -1;
        }

        return Math.max(-1, Math.min(1, sen));
    }

     public double analDir(double x2, double y2){
        calcEsq(x2, y2);

        // movimentos diagonais
        if (x2 > deadzone && y2 > deadzone){
         velEsq = hipotenusa;
         velDir = sen;
        } else if (x2 < -deadzone && y2 > deadzone) { // eixo II
         velEsq = -sen;
         velDir = hipotenusa;
        } else if (x2 < -deadzone && y2 < -deadzone) { // eixo III
         velEsq = sen;
         velDir = -hipotenusa;
        } else if (x2 > deadzone && y2 < -deadzone) { // eixo IV
         velEsq = hipotenusa;
        velDir = sen;
        }

        // limitando o analogico
        if(velEsq > 0.99 && velDir > 0.99) {
         velEsq = 1;
         velDir = 1;
        } else if(velEsq < -0.99 && velDir < -0.99) {
         velEsq = -1;
         velDir = -1;
        }

        return Math.max(-1, Math.min(1, sen));
    }

    public void POV() {
    switch (angulo) {
      case -1:
        velEsq = velBotao * 0;
        velDir = velBotao * 0;
      case 0: 
       velEsq = velBotao * 1;
       velDir = velBotao * 1;
       break;
      case 45:
       velEsq = velBotao * 0.5;
       velDir = velBotao * -0.5;
        break;
      case 90:
       velEsq = velBotao * 1;
       velDir = velBotao * 0;
        break;
      case 135:
       velEsq = velBotao * 1;
       velDir = velBotao * 0.3;
      case 180:
       velEsq = velBotao * -1;
       velDir = velBotao * -1;
        break;
      case 225:
       velEsq = velBotao * 0.3;
       velDir = velBotao * 1;
        break;
      case 270:
       velEsq = velBotao * 0;
       velDir = velBotao * 1;
        break;
      case 315:
       velEsq = velBotao * -0.5;
       velDir = velBotao * 0.5;
        break;
    }
  }
}
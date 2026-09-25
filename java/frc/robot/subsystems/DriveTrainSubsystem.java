package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants;


public class DriveTrainSubsystem extends SubsystemBase {
    // Controladores de motor
    protected final VictorSPX dt = new VictorSPX(Constants.OperatorConstants.dt);
    protected final VictorSPX df = new VictorSPX(Constants.OperatorConstants.df);
    protected final VictorSPX et = new VictorSPX(Constants.OperatorConstants.et);
    protected final VictorSPX ef = new VictorSPX(Constants.OperatorConstants.ef);

    public DriveTrainSubsystem(){
        dt.setInverted(true);
        df.setInverted(true);

        dt.follow(df);
        et.follow(ef);

        df.setNeutralMode(NeutralMode.Brake);
        ef.setNeutralMode(NeutralMode.Brake);

        df.configNeutralDeadband(Constants.OperatorConstants.deadzone);
        ef.configNeutralDeadband(Constants.OperatorConstants.deadzone);
    }

    public void Drive(double velE, double velD){
        Calculo.velEsq = velE;
        Calculo.velDir = velD;
        
        df.set(ControlMode.PercentOutput, velD);
        ef.set(ControlMode.PercentOutput, velE);
    }

    @Override
  public void periodic() {
    
  }
}
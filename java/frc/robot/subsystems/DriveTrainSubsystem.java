package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class DriveTrainSubsystem extends SubsystemBase {
    // Controladores de motor
    protected final VictorSPX dt = new VictorSPX(Constants.OperatorConstants.dt);
    protected final VictorSPX df = new VictorSPX(Constants.OperatorConstants.df);
    protected final VictorSPX et = new VictorSPX(Constants.OperatorConstants.et);
    protected final VictorSPX ef = new VictorSPX(Constants.OperatorConstants.ef);

    // Variaveis
    double velE, velD;

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

    public void Drive(double velEsq, double velDir){
        velE = velEsq;
        velD = velDir;
        
        df.set(ControlMode.PercentOutput, velDir);
        ef.set(ControlMode.PercentOutput, velEsq);
    }

    @Override
  public void periodic() {
    
  }

}
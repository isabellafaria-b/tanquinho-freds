package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants;


public class DriveTrainSubsystem extends SubsystemBase {
    // Controladores de motor
    protected final SparkMax dt = new SparkMax(Constants.OperatorConstants.dt, MotorType.kBrushed);
    protected final SparkMax df = new SparkMax(Constants.OperatorConstants.df, MotorType.kBrushed);
    protected final SparkMax et = new SparkMax(Constants.OperatorConstants.et, MotorType.kBrushed);
    protected final SparkMax ef = new SparkMax(Constants.OperatorConstants.ef, MotorType.kBrushed);

    public DriveTrainSubsystem(){
        SparkMaxConfig configDir = new SparkMaxConfig();
        configDir.inverted(true);
        configDir.follow(df, false);
        configDir.idleMode(IdleMode.kBrake);
        dt.configure(configDir, SparkBase.ResetMode.kResetSafeParameters,
             SparkBase.PersistMode.kPersistParameters);

        SparkMaxConfig configEsq = new SparkMaxConfig();
        configEsq.follow(ef, false);
        configEsq.idleMode(IdleMode.kBrake);
        ef.configure(configEsq, null, null);
    }

    public void Drive(double velE, double velD){
        Calculo.velEsq = velE;
        Calculo.velDir = velD;
        
        df.set(Calculo.velDir);
        ef.set(Calculo.velEsq);
    }

    @Override
  public void periodic() {
    
  }

}
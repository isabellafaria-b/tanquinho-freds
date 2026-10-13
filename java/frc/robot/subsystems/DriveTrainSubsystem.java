package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants;


public class DriveTrainSubsystem extends SubsystemBase {
    // Controladores de motor
    protected final SparkMax dt = new SparkMax(Constants.MotorConstants.dt, MotorType.kBrushed);
    protected final SparkMax df = new SparkMax(Constants.MotorConstants.df, MotorType.kBrushed);
    protected final SparkMax et = new SparkMax(Constants.MotorConstants.et, MotorType.kBrushed);
    protected final SparkMax ef = new SparkMax(Constants.MotorConstants.ef, MotorType.kBrushed);

    public void DriveTrain(){
        SparkMaxConfig configDir1 = new SparkMaxConfig();
        configDir1.idleMode(IdleMode.kBrake);
        df.configure(configDir1, ResetMode.kResetSafeParameters,
         PersistMode.kPersistParameters);

        SparkMaxConfig configDir2 = new SparkMaxConfig();
        configDir2.idleMode(IdleMode.kBrake);
        dt.configure(configDir2, ResetMode.kResetSafeParameters,
         PersistMode.kPersistParameters);
        
        SparkMaxConfig configEsq1 = new SparkMaxConfig();
        configEsq1.inverted(true);
        configEsq1.idleMode(IdleMode.kBrake);
        ef.configure(configEsq1, ResetMode.kResetSafeParameters,
         PersistMode.kPersistParameters);

        SparkMaxConfig configEsq2 = new SparkMaxConfig();
        configEsq2.inverted(true);
        configEsq2.idleMode(IdleMode.kBrake);
        et.configure(configEsq2, ResetMode.kResetSafeParameters,
         PersistMode.kPersistParameters);
    }

    public void Drive(double velE, double velD){
        Calculo.velEsq = velE;
        Calculo.velDir = velD;
        
        df.set(velD); dt.set(velD);
        ef.set(velE); et.set(velE);
    }

    @Override
  public void periodic() {
    
  }

}
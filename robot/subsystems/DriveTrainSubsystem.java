package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants.SensorConstants;
import frc.robot.Constants.MotorConstants;;


public class DriveTrainSubsystem extends SubsystemBase {
    // Controladores de motor
    private final SparkMax dt = new SparkMax(MotorConstants.dt, MotorType.kBrushed);
    private final SparkMax df = new SparkMax(MotorConstants.df, MotorType.kBrushed);
    private final SparkMax et = new SparkMax(MotorConstants.et, MotorType.kBrushed);
    private final SparkMax ef = new SparkMax(MotorConstants.ef, MotorType.kBrushed);

    // Encoder
    public static final Encoder encoder = new Encoder(SensorConstants.channelA,
     SensorConstants.channelB, SensorConstants.reversed, Encoder.EncodingType.k4X);

    public DriveTrainSubsystem(){
        SparkMaxConfig configDir1 = new SparkMaxConfig();
        configDir1.follow(df);
        configDir1.idleMode(IdleMode.kBrake);
        dt.configure(configDir1, ResetMode.kResetSafeParameters,
         PersistMode.kPersistParameters);
        
        SparkMaxConfig configEsq1 = new SparkMaxConfig();
        configEsq1.follow(ef);
        configEsq1.inverted(true);
        configEsq1.idleMode(IdleMode.kBrake);
        et.configure(configEsq1, ResetMode.kResetSafeParameters,
         PersistMode.kPersistParameters);

         // Configurações do encoder
         encoder.setDistancePerPulse(4.0 / 256.0);
    }
 
    public void resetEncoder(){
        encoder.reset();
    }

    public double pegarDistancia(){
        return encoder.getDistance();
    }

    public void Drive(double velE, double velD){
        Calculo.velEsq = velE;
        Calculo.velDir = velD;
        
        df.set(velD); dt.set(velD);
        ef.set(velE); et.set(velE);
    }
}
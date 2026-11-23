package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants.MotorConstants;;

public class GarraSubsystem extends SubsystemBase {
  // Motor da garra
    private final SparkMax mg = new SparkMax(MotorConstants.garra, MotorType.kBrushless);
 
  public GarraSubsystem() {
      SparkMaxConfig configGarra = new SparkMaxConfig();
         configGarra.idleMode(IdleMode.kBrake);
         configGarra.smartCurrentLimit(20);
         mg.configure(configGarra, ResetMode.kResetSafeParameters,
          PersistMode.kPersistParameters);
  }

  public void DriveGarra(double velG){
    Calculo.velGarra = velG;

    mg.set(velG);
  }
}
package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GarraSubsystem extends SubsystemBase {
  // Motor da garra
    protected final SparkMax mg = new SparkMax(Constants.MotorConstants.garra, 
    MotorType.kBrushless);
 
    public GarraSubsystem() {
      SparkMaxConfig configGarra = new SparkMaxConfig();
         configGarra.idleMode(IdleMode.kBrake);
         mg.configure(configGarra, ResetMode.kResetSafeParameters,
          PersistMode.kPersistParameters);
  }

  public void abrirGarra(){
    mg.set(-0.5);
  }

  public void fecharGarra(){
    mg.set(0.5);
  }

  public void pararGarra(){
    mg.set(0);
  }
}
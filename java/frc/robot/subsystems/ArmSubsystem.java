package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants.MotorConstants;

public class ArmSubsystem extends SubsystemBase {
  // Motor do braço
    protected final SparkMax mb = new SparkMax(MotorConstants.braco,
     MotorType.kBrushless);

  public ArmSubsystem() {
    SparkMaxConfig configBraco = new SparkMaxConfig();
         configBraco.idleMode(IdleMode.kBrake);
         mb.configure(configBraco, ResetMode.kResetSafeParameters,
          PersistMode.kPersistParameters);
  }

  public void DriveBraco(double velB){
    Calculo.velBraco = velB;

   mb.set(velB);
  }
}

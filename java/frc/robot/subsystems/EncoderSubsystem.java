package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SensorConstants;

public class EncoderSubsystem extends SubsystemBase {
  // Encoder
    public static final Encoder encoder = new Encoder(SensorConstants.channelA,
     SensorConstants.channelB, SensorConstants.reversed, Encoder.EncodingType.k4X);
 
  public EncoderSubsystem() {
      // Configurações do encoder
      encoder.setDistancePerPulse(360.0 / 8192.0);
  }

  public void resetEncoder(){
      encoder.reset();
    }

    public double pegarDistancia(){
      return encoder.getDistance();
    }

  @Override
  public void periodic() {
  }
}

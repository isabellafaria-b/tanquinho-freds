package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SensorConstants;

public class InfraVermelho extends SubsystemBase {
  DriveTrainSubsystem subsystem;
  DigitalInput input;
  
  public InfraVermelho(DriveTrainSubsystem subsystem) {
    this.subsystem = subsystem;
    input = new DigitalInput(SensorConstants.DigitalInput);
  }

  @Override
  public void periodic() {
  }

  public boolean temParede() {
    return !input.get();
  }
}
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Calculo;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.SensorConstants;

public class InfraVermelho extends SubsystemBase {
  DriveTrainSubsystem subsystem;
  DigitalInput input;

  // Deadzone
  private final double deadzone = OperatorConstants.deadzone;
  
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

  public void parar(){
    if(Calculo.velDir > deadzone || Calculo.velEsq > deadzone) {
      if(input.get() == false){
        subsystem.Drive(0, 0);
      } else{
        subsystem.Drive(Calculo.velEsq, Calculo.velDir);
      }
    } else {
      subsystem.Drive(Calculo.velEsq, Calculo.velDir);
    }
  }
}

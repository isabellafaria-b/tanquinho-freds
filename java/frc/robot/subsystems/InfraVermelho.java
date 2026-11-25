package frc.robot.subsystems;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SensorConstants;

public class InfraVermelho extends SubsystemBase {
  private final DigitalInput input = new DigitalInput(SensorConstants.DigitalInput);
  private final Debouncer debouncer = new Debouncer(SensorConstants.debounceTime, Debouncer.DebounceType.kBoth);
  private boolean parede = false;
  
  public InfraVermelho() {
  }

   public boolean temObstaculo() {
    return parede;
  }

  @Override
  public void periodic() {
    parede = debouncer.calculate(!input.get());
    
    SmartDashboard.putBoolean("Sensor Infravrmelho", input.get());
    SmartDashboard.putBoolean("Tem parede", parede);
  }
}
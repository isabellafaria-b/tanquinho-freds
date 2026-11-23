package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calculo;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ArmSubsystem;

public class Braco extends Command {
  // Variáveis
  public static double trigelaE, trigelaD;
  private final double deadzone = OperatorConstants.deadzone;
 
  // Objetos
  private final Joystick fred;
  private final ArmSubsystem braco;

  public Braco(ArmSubsystem subsystem, Joystick fred) {
    this.fred = fred;
    this.braco = subsystem;

    addRequirements(subsystem);
  }

  public void Objetos() {
    // triggers
      trigelaD = fred.getRawAxis(3);
      trigelaE = fred.getRawAxis(2);
  }

  public void triggers(ArmSubsystem braco) {
    if (Braco.trigelaE > deadzone) {
      Calculo.velBraco = -trigelaE;
    } else if (Braco.trigelaD > deadzone) {
      Calculo.velBraco = trigelaD;
    } else {
      Calculo.velBraco = 0;
    }

    Calculo.velBraco *= 0.3;
    braco.DriveBraco(Calculo.velBraco);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Objetos();
    triggers(braco);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}

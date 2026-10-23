package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calculo;
import frc.robot.subsystems.ArmSubsystem;

public class Arm extends Command {
  // Variáveis
  public static double trigelaE, trigelaD;

  // Objetos
  private final Joystick fred;
  private final ArmSubsystem braco;
  private final Calculo calculo = new Calculo();

  public Arm(ArmSubsystem subsystem, Joystick fred) {
    this.fred = fred;
    this.braco = subsystem;

    addRequirements(subsystem);
  }

  public void Objetos() {
    // triggers
      trigelaD = fred.getRawAxis(3);
      trigelaE = fred.getRawAxis(2);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Objetos();
    calculo.triggers();
    braco.DriveBraco(Calculo.velBraco);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Calculo;
import frc.robot.RobotContainer;
import frc.robot.subsystems.GarraSubsystem;

public class Garra extends Command {
  // Variáveis
  public static boolean lb, rb;  

  //Objetos
  private final Joystick fred;
  private final GarraSubsystem subsystem;
  private final Calculo calculo = new Calculo();

  public Garra(GarraSubsystem garra, Joystick fred) {
    this.fred = fred;
    this.subsystem = garra;

    addRequirements(garra);
  }

  public void objetos(){
    lb = fred.getRawButton(5);
    rb = fred.getRawButton(6);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    objetos();
    calculo.garraOn(RobotContainer.garra);
    subsystem.DriveGarra(Calculo.velGarra);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}

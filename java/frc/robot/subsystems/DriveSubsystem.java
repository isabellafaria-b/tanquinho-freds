package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class DriveSubsystem extends SubsystemBase {
    // Controladores de motor
    protected final VictorSPX dt = new VictorSPX(0);
    protected final VictorSPX df = new VictorSPX(2);
    protected final VictorSPX et = new VictorSPX(3);
    protected final VictorSPX ef = new VictorSPX(4);

    // Variaveis
    DifferentialDrive drive;
    double velE, velD;

    public DriveSubsystem(){
        dt.setInverted(true);
        df.setInverted(true);

        dt.follow(df);
        et.follow(ef);

        df.setNeutralMode(NeutralMode.Brake);
        ef.setNeutralMode(NeutralMode.Brake);

        df.configNeutralDeadband(0.043315);
        ef.configNeutralDeadband(0.043315);
    }

    public void Drive(double velEsq, double velDir){
        velE = velEsq;
        velD = velDir;
        
        df.set(ControlMode.PercentOutput, velDir);
        ef.set(ControlMode.PercentOutput, velEsq);
    }



    @Override
  public void periodic() {
  }

}

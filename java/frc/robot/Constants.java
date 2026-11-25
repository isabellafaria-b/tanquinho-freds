package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final double deadzone = 0.04;

    public static final int fred = 0;
    public static final int input = 1;
  }

  public static class MotorConstants {
    public static final int dt = 4;
    public static final int df = 3;
    public static final int et = 2;
    public static final int ef = 1;

    public static final int garra = 14;
    public static final int braco = 15;
  }

  public static class SensorConstants {
    public static final int channelA = 0;
    public static final int channelB = 1;
    public static final boolean reversed = false;

    public static final int DigitalInput = 2;
    public static final double debounceTime = 0.1;
    public static final double distanciaAlvo = 4.0;
    }
}

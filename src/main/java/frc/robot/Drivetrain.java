package frc.robot;

import javax.swing.text.AbstractDocument.LeafElement;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Drivetrain {
    private final WPI_TalonFX m_leftDrive;
    private final WPI_TalonFX m_rightDrive;
    //private final DifferentialDrive m_robotDrive;
    private final PIDController pid = new PIDController(.01, 0, 0); //kP is volts per rotation per second (12v/100rps=.12)
    private final PIDController pidPos = new PIDController(.01, 0, 0); //kP is volts per rotation

    private final double kS = .08; //Voltage needed to overcome static friction

    public Drivetrain() {
        m_leftDrive = new WPI_TalonFX(1);
        m_rightDrive = new WPI_TalonFX(2);
        //m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
        driveConfig(m_leftDrive, 6, .1);
        driveConfig(m_rightDrive, 6, .1);
    }

    private void driveConfig(WPI_TalonFX motor) {
        motor.configVoltageCompSaturation(6.0);
        motor.configOpenloopRamp(.2);
        motor.enableVoltageCompensation(true);
    }

    private void driveConfig(WPI_TalonFX motor, double voltage, double rampSeconds) {
        motor.configVoltageCompSaturation(voltage);
        motor.configOpenloopRamp(rampSeconds*(12/voltage));
        motor.enableVoltageCompensation(true);
    }

    private double ticksToRotations(double ticks) {
        return ticks / 2048.0;
    }

    private double ticksToRPS(double ticksPerSecond) {
        return (ticksPerSecond * 10.0 / 2048.0);
    }

    public void arcadeDrive(double Xspeed, double Zrotation) {
        //m_robotDrive.arcadeDrive(Xspeed, Zrotation);
    }

    //returns the velocity of the left drive motor in ROTATIONS PER SECOND
    public double getLeftRPS() {
        return ticksToRPS(m_leftDrive.getSelectedSensorVelocity());
    }

    public void velocityDrive(double rotationsPerSecond) {
        //1 volt drives 80.1 rotations per second

        m_leftDrive.set(pid.calculate(getLeftRPS(), rotationsPerSecond));
        SmartDashboard.putNumber("test", getLeftRPS()); //fix later
        //m_leftDrive.set(rotationsPerSecond / 107.04);

        //volts * 107.04 = rotations per second
        //50 * 0.0125 volts = 66.9 rotations per second
        //25 * 0.0125 volts = 33.6 rotations per second
        //m_leftDrive.set(.11*rotationsPerSecond);
    }

    public void positionDrive(double rotations) {
        m_leftDrive.set(Math.signum(rotations- ticksToRotations(m_leftDrive.getSelectedSensorPosition()))*kS + pidPos.calculate(ticksToRotations(m_leftDrive.getSelectedSensorPosition()), rotations));
    }
    
    public void mimicRotation() {
        m_leftDrive.set(pid.calculate(getLeftRPS(), ticksToRPS(m_rightDrive.getSelectedSensorVelocity())));
    }

    public void zeroMotors() {
        m_leftDrive.setSelectedSensorPosition(0);
        m_rightDrive.setSelectedSensorPosition(0);
    }
}

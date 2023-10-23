package frc.robot;

import javax.swing.text.AbstractDocument.LeafElement;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain {
    private final WPI_TalonFX m_leftDrive;
    private final WPI_TalonFX m_rightDrive;
    //private final DifferentialDrive m_robotDrive;
    private final PIDController pid = new PIDController(1/107.04, 0.03, 0); //kP is volts per rotation per second (12v/100rps=.11)

    public Drivetrain() {
        m_leftDrive = new WPI_TalonFX(1);
        m_rightDrive = new WPI_TalonFX(2);
        //m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
        driveConfig(m_leftDrive, 12, .1);
        driveConfig(m_rightDrive, 12, .1);
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

    private double ticksToRPS(double ticksPerSecond) {
        return (ticksPerSecond * 10.0 / 2048.0);
    }

    public void arcadeDrive(double Xspeed, double Zrotation) {
        //m_robotDrive.arcadeDrive(Xspeed, Zrotation);
    }

    //returns the velocity of the left drive motor in ROTATIONS PER SECOND
    public double getLeftVelocity() {
        return ticksToRPS(m_leftDrive.getSelectedSensorVelocity());
    }

    public void velocityDrive(double rotationsPerSecond) {
        //1 volt drives 80.1 rotations per second

        m_leftDrive.set(pid.calculate(ticksToRPS(m_leftDrive.getSelectedSensorVelocity()), rotationsPerSecond));
        SmartDashboard.putNumber("test", ticksToRPS(m_leftDrive.getSelectedSensorVelocity())); //fix later
        //m_leftDrive.set(rotationsPerSecond / 107.04);

        //volts * 107.04 = rotations per second
        //50 * 0.0125 volts = 66.9 rotations per second
        //25 * 0.0125 volts = 33.6 rotations per second
        //m_leftDrive.set(.11*rotationsPerSecond);
    }
}

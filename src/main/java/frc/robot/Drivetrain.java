package frc.robot;

import javax.swing.text.AbstractDocument.LeafElement;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {
    private final WPI_TalonFX m_leftDrive;
    private final WPI_TalonFX m_rightDrive;
    private final DifferentialDrive m_robotDrive;

    public Drivetrain() {
        m_leftDrive = new WPI_TalonFX(1);
        m_rightDrive = new WPI_TalonFX(2);
        m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
        driveConfig(m_leftDrive, 6, 2);
        driveConfig(m_rightDrive, 6, 2);
    }

    private void driveConfig(WPI_TalonFX motor) {
        motor.configVoltageCompSaturation(6.0);
        motor.configOpenloopRamp(20/2);
        motor.enableVoltageCompensation(true);
    }

    private void driveConfig(WPI_TalonFX motor, double voltage, double rampSeconds) {
        motor.configVoltageCompSaturation(voltage);
        motor.configOpenloopRamp(rampSeconds*(12/voltage));
        motor.enableVoltageCompensation(true);
    }

    public void arcadeDrive(double Xspeed, double Zrotation) {
        m_robotDrive.arcadeDrive(Xspeed, Zrotation);
    }
}

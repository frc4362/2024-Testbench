// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.sql.Time;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonFX m_leftDrive = new WPI_TalonFX(1);
  private final WPI_TalonFX m_rightDrive = new WPI_TalonFX(2);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
  private final XboxController m_Controller = new XboxController(0);
  private final Timer m_timer = new Timer();


  @Override
  public void robotInit() {

  }

  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 2) { //for 2 seconds
      m_robotDrive.arcadeDrive(0.5, 0.0, false); //make robot GO!!
    } else {
      m_robotDrive.stopMotor(); //stop de robot
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_Controller.getLeftY(), -m_Controller.getLeftX());
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}

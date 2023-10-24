// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pabeles.concurrency.IntOperatorTask.Max;

import java.sql.Time;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final XboxController m_Controller = new XboxController(0);
  private final Timer m_timer = new Timer();
  private final Tachometer m_Tachometer = new Tachometer();


  @Override
  public void robotInit() {

  }

  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  @Override
  public void autonomousPeriodic() {
    // if (m_timer.get() < 2) { //for 2 seconds
    //   m_robotDrive.arcadeDrive(0.5, 0.0, false); //make robot GO!!
    // } else {
    //   m_robotDrive.stopMotor(); //stop de robot
    // }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    //m_drivetrain.arcadeDrive(-m_Controller.getLeftY(), -m_Controller.getLeftX());

    // double goal = 50*m_Controller.getLeftY();
    // if (goal < 2.0 && goal > -2.0) {
    //   goal = 0.0;
    // }
    // SmartDashboard.putNumber("goal speed", goal);
    // SmartDashboard.putNumber("ratio real to goal", m_drivetrain.getLeftVelocity()/(goal));
    // m_drivetrain.velocityDrive(goal);

    m_drivetrain.mimicRotation();

    // m_Tachometer.update();
    // SmartDashboard.putBoolean("BROKEN", m_Tachometer.isBroken());
    // SmartDashboard.putNumber("break count", m_Tachometer.getBreakCount());
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}

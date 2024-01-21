// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Shooter;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;
/*package frc.robot.subsystems.flywheel;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.LoggedTunableNumber;
import org.littletonrobotics.junction.Logger;
*/
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.LoggedTunableNumber;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */

  private static final LoggedTunableNumber flywheelkP = new LoggedTunableNumber("kP");
  private final SimpleMotorFeedforward ffModel;
  TalonFX test;
  public Shooter() {
    ffModel = new SimpleMotorFeedforward(0.1, 0.05);
    flywheelkP.initDefault(0);
    test = new TalonFX(0);
       var config = new Slot0Configs();
    config.kP = flywheelkP.get();
    test.getConfigurator().apply(config);



  }

  public void setVelocity(double velocityRPM) {
    var velocityRadPerSec = Units.rotationsPerMinuteToRadiansPerSecond(velocityRPM);
    test.setControl(
        new VelocityVoltage(
            Units.radiansToRotations(velocityRadPerSec),
            0.0,
            false,
            ffModel.calculate(velocityRadPerSec),
            0,
            false,
            false,
            false));
  }

  public void stop() {
    test.stopMotor();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //LoggedTunableNumber 
    //test.set(shooterControl);
    
  }
}

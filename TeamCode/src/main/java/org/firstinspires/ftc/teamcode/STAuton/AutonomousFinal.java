package org.firstinspires.ftc.teamcode.STAuton;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;
import org.firstinspires.ftc.teamcode.RobotParts.DriveTrain;

import java.util.List;

@Autonomous(name = "AutonomousFinal", group = "AutonomousFinal")
//Naam van project
public class AutonomousFinal extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private final int TICKS_PER_REVOLUTION = 8192;
    private final double WHEEL_DIAMETER_MM = 34.0;
    private final double TICKS_PER_CM = TICKS_PER_REVOLUTION / (WHEEL_DIAMETER_MM * Math.PI);

    @Override
    public void runOpMode() throws InterruptedException {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }


        DriveTrain driveTrain = new DriveTrain();
        Motors motors = new Motors();
        ServoTest feeder = new ServoTest();
        motors.init(hardwareMap);
        driveTrain.init(hardwareMap);
        feeder.init(hardwareMap);

        int state = 0;

        double startuptime;
        double error = 0;
        int ticks = 0;

        waitForStart();
        if (isStopRequested()) return;

        startuptime = System.currentTimeMillis();

        while (opModeIsActive() && state != 24) {
            switch (state) {
                case 0:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 26.5 - ticks / TICKS_PER_CM;
                    motors.shootingMethod(1);
                    driveTrain.drive(0.7, Math.PI, 0, true);
                    if (ticks > 11000|| System.currentTimeMillis() > startuptime + 3000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 1:
                    if (System.currentTimeMillis() > startuptime + 400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 2:
                    if (System.currentTimeMillis() > startuptime +800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 3:
                    if (System.currentTimeMillis() > startuptime + 2000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 4:
                    if (System.currentTimeMillis() > startuptime + 2400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 5:
                    if (System.currentTimeMillis() > startuptime + 2800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 6:
                    if (System.currentTimeMillis() > startuptime + 3200) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 7:
                    if (System.currentTimeMillis() > startuptime + 3600) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 8:
                    if (System.currentTimeMillis() > startuptime + 4800) {
                        motors.shootingMethod(0);
                        state++;
                    }
                    break;
                case 9:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 65.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, Math.PI, 0, true);
                    if (ticks > 12222 || System.currentTimeMillis() > startuptime + 7000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 10:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 1024 || System.currentTimeMillis() > startuptime + 11000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 11:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 60.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, Math.PI, 0, true);
                    if (ticks > 26888 || System.currentTimeMillis() > startuptime + 14000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 12:
                    if (System.currentTimeMillis() > startuptime + 9500) {
                        motors.transferMethod(0.5);
                        motors.intakeMethod(0.5);
                        state++;
                    }
                    break;
                case 13:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 1024 || System.currentTimeMillis() > startuptime + 16000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 14:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 60.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, Math.PI, 0, true);
                    if (ticks > 20777 || System.currentTimeMillis() > startuptime + 19000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                case 15:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 21000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 16:
                    if (System.currentTimeMillis() > startuptime + 23000) {
                        state++;
                        motors.shootingMethod(1);
                        feeder.setSevenPos(0);
                    }
                    break;
                case 17:
                    if (System.currentTimeMillis() > startuptime + 25000) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 18:
                    if (System.currentTimeMillis() > startuptime + 27000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 19:
                    if (System.currentTimeMillis() > startuptime + 27250) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 20:
                    if (System.currentTimeMillis() > startuptime + 27500) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 21:
                    if (System.currentTimeMillis() > startuptime + 27750) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 22:
                    if (System.currentTimeMillis() > startuptime + 28000) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 23:
                    if (System.currentTimeMillis() > startuptime + 28250) {
                        motors.shootingMethod(0);
                        state++;
                    }
                    break;
            }
            telemetry.addData("ticks", ticks);
            telemetry.addData("error", error);
            telemetry.addData("state",state);
            telemetry.addData("time", System.currentTimeMillis() - startuptime);
            telemetry.update();
        }
    }
}

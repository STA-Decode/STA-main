package org.firstinspires.ftc.teamcode.STAuton;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;
import org.firstinspires.ftc.teamcode.RobotParts.DriveTrain;

import java.util.List;

@Autonomous(name = "AutonomousRedClose", group = "AutonomousRedClose")
//Naam van project
public class AutonomousRedClose extends LinearOpMode {
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

        while (opModeIsActive() && state != 64) {
            switch (state) {
                case 0:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 26.5 - ticks / TICKS_PER_CM;
                    motors.shootingMethod(1);
                    driveTrain.drive(0.7, Math.PI, 0, true);
                    if (ticks > 2094|| System.currentTimeMillis() > startuptime + 3000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 1:
                    if (System.currentTimeMillis() > startuptime + 3400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 2:
                    if (System.currentTimeMillis() > startuptime +3800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 3:
                    if (System.currentTimeMillis() > startuptime + 332000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 4:
                    if (System.currentTimeMillis() > startuptime + 332400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 5:
                    if (System.currentTimeMillis() > startuptime + 3332800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 6:
                    if (System.currentTimeMillis() > startuptime + 3333200) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 7:
                    if (System.currentTimeMillis() > startuptime + 3333600) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 8:
                    if (System.currentTimeMillis() > startuptime + 3334800) {
                        motors.shootingMethod(0);
                        state++;
                    }
                    break;
                case 9:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 65.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, Math.PI, 0, true);
                    if (ticks > 10216 || System.currentTimeMillis() > startuptime + 3337000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 10:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 975 || System.currentTimeMillis() > startuptime + 33311000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 11:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 60.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, Math.PI, 0, true);
                    if (ticks > 12040 || System.currentTimeMillis() > startuptime + 33314000) {
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
                    if (ticks > 1949 || System.currentTimeMillis() > startuptime + 33316000) {
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
                    if (ticks > 4816 || System.currentTimeMillis() > startuptime + 33319000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                case 15:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 16:
                    if (System.currentTimeMillis() > startuptime + 33323000) {
                        state++;
                        motors.shootingMethod(1);
                        feeder.setSevenPos(0);
                    }
                    break;
                case 17:
                    if (System.currentTimeMillis() > startuptime + 33325000) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 18:
                    if (System.currentTimeMillis() > startuptime + 33327000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 19:
                    if (System.currentTimeMillis() > startuptime + 33327250) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 20:
                    if (System.currentTimeMillis() > startuptime + 33327500) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 21:
                    if (System.currentTimeMillis() > startuptime + 33327750) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 22:
                    if (System.currentTimeMillis() > startuptime + 33328000) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 23:
                    if (System.currentTimeMillis() > startuptime + 33328250) {
                        motors.shootingMethod(0);
                        state++;
                    }
                    break;
                case 24:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 25:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 975 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 26:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, 0, 0, true);
                    if (ticks > 13621 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 27:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 975 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 28:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 13621 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 29:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 975 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 30:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 9632 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 31:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 1949 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 32:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 9632 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 33:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 34:
                    if (System.currentTimeMillis() > startuptime + 33323000) {
                        state++;
                        motors.shootingMethod(1);
                        feeder.setSevenPos(0);
                    }
                    break;
                case 35:
                    if (System.currentTimeMillis() > startuptime + 33325000) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 36:
                    if (System.currentTimeMillis() > startuptime + 33327000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 37:
                    if (System.currentTimeMillis() > startuptime + 33327250) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 38:
                    if (System.currentTimeMillis() > startuptime + 33327500) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 39:
                    if (System.currentTimeMillis() > startuptime + 33327750) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 40:
                    if (System.currentTimeMillis() > startuptime + 33328000) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 41:
                    if (System.currentTimeMillis() > startuptime + 33328250) {
                        motors.shootingMethod(0);
                        state++;
                    }
                    break;
                case 42:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 43:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 498 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 44:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, 0, 0, true);
                    if (ticks > 18488 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 45:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 1451 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 46:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 9150 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 47:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 974 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 48:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 10595 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 49:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 50:
                    if (System.currentTimeMillis() > startuptime + 33323000) {
                        state++;
                        motors.shootingMethod(1);
                        feeder.setSevenPos(0);
                    }
                    break;
                case 51:
                    if (System.currentTimeMillis() > startuptime + 33325000) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 52:
                    if (System.currentTimeMillis() > startuptime + 33327000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 53:
                    if (System.currentTimeMillis() > startuptime + 33327250) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 54:
                    if (System.currentTimeMillis() > startuptime + 33327500) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 55:
                    if (System.currentTimeMillis() > startuptime + 33327750) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 56:
                    if (System.currentTimeMillis() > startuptime + 33328000) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 57:
                    if (System.currentTimeMillis() > startuptime + 33328250) {
                        motors.shootingMethod(0);
                        state++;
                    }
                    break;
                case 58:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 500 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 59:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, 0, 0, true);
                    if (ticks > 17337 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 60:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, -0.5, true);
                    if (ticks > 974 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 61:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, 0, 0, true);
                    if (ticks > 2408 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

                        state++;
                    }
                    break;
                case 62:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 974 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 63:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.7, 0, 0, true);
                    if (ticks > 5779 || System.currentTimeMillis() > startuptime + 33316000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        driveTrain.drive(0, 0, 0, true);

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

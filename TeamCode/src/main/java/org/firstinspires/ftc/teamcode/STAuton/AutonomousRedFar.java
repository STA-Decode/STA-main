package org.firstinspires.ftc.teamcode.STAuton;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;
import org.firstinspires.ftc.teamcode.RobotParts.DriveTrain;

import java.util.List;

@Autonomous(name = "AutonomousRedFar", group = "AutonomousRedFar")
//Naam van project
public class AutonomousRedFar extends LinearOpMode {
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
        motors.shootingMethod(1);

        while (opModeIsActive() && state != 11) {
            switch (state) {
                case 0:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 5.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 15892 || System.currentTimeMillis() > startuptime + 3334000) {
                        startuptime = System.currentTimeMillis();
                        driveTrain.drive(0, 0, 0, true);
                        state++;


                    }
                    break;
                case 1:
                    ticks = driveTrain.getEncoderData()[1];
                    error = 25.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0, 0, 0.5, true);
                    if (ticks > 974 || System.currentTimeMillis() > startuptime + 33321000) {
                        startuptime = System.currentTimeMillis();
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                    }
                    break;
                case 2:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 5.0 - ticks / TICKS_PER_CM;
                    driveTrain.drive(-0.7, 0, 0, true);
                    if (ticks > 6810 || System.currentTimeMillis() > startuptime + 3334000) {
                        startuptime = System.currentTimeMillis();
                        driveTrain.drive(0, 0, 0, true);
                        state++;


                    }
                    break;
                case 3:
                    if (System.currentTimeMillis() > startuptime + 3333400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 4:
                    if (System.currentTimeMillis() > startuptime +3333800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 5:
                    if (System.currentTimeMillis() > startuptime + 332000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 6:
                    if (System.currentTimeMillis() > startuptime + 332400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 7:
                    if (System.currentTimeMillis() > startuptime + 3332800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 8:
                    if (System.currentTimeMillis() > startuptime + 3333200) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 9:
                    if (System.currentTimeMillis() > startuptime + 3333600) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 10:
                    if (System.currentTimeMillis() > startuptime + 3334800) {
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

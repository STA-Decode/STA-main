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
        motors.shootingMethod(1);

        while (opModeIsActive() && state != 9) {
            switch (state) {
                case 0:
                    ticks = driveTrain.getEncoderData()[0];
                    error = 26.5 - ticks / TICKS_PER_CM;
                    driveTrain.drive(0.3, Math.PI, 0, true);
                    if (error < 0.5 || System.currentTimeMillis() > startuptime + 2000) {
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
                    if (System.currentTimeMillis() > startuptime + 800) {
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
                    if (System.currentTimeMillis() > startuptime + 4000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 7:
                    if (System.currentTimeMillis() > startuptime + 4400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 8:
                    if (System.currentTimeMillis() > startuptime + 4800) {
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

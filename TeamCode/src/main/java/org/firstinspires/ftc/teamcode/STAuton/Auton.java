package org.firstinspires.ftc.teamcode.STAuton;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;
import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;

import java.util.List;

@Autonomous(name = "Autonomous", group = "Autonomous")
//Naam van project
public class Auton extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }


        driveTrain driveTrain = new driveTrain();
        Motors motors = new Motors();
        ServoTest feeder = new ServoTest();
        motors.init(hardwareMap);
        driveTrain.init(hardwareMap);
        feeder.init(hardwareMap);

        int state = 0;

        double startuptime;
        double error;

        waitForStart();
        if (isStopRequested()) return;

        startuptime = System.currentTimeMillis();
        motors.shootingMethod(1);

        while (opModeIsActive() && state != 9) {
            switch (state) {
                case 0:
                    //TODO: 26.5 cm rijden
                    error = 26.5;
                    driveTrain.drive(-0.3, 0, 0, true);
                    if (error < 0.3 || startuptime < System.currentTimeMillis() + 2000) {
                        state++;
                        driveTrain.drive(0, 0, 0, true);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 1:
                    if (System.currentTimeMillis() < startuptime + 1400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 2:
                    if (System.currentTimeMillis() < startuptime + 1800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 3:
                    if (System.currentTimeMillis() < startuptime + 3000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);
                    }
                    break;
                case 4:
                    if (System.currentTimeMillis() < startuptime + 3400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 5:
                    if (System.currentTimeMillis() < startuptime + 3800) {
                        state++;
                        motors.intakeMethod(1);
                        motors.transferMethod(1);
                    }
                    break;
                case 6:
                    if (System.currentTimeMillis() < startuptime + 5000) {
                        state++;
                        motors.transferMethod(0);
                        motors.intakeMethod(0);
                        feeder.setSevenPos(0.475);              }
                    break;
                case 7:
                    if (System.currentTimeMillis() < startuptime + 5400) {
                        state++;
                        feeder.setSevenPos(0);
                    }
                    break;
                case 8:
                    if (System.currentTimeMillis() < startuptime + 5800) {
                        state++;
                    }
                    break;
            }
        }







        while (System.currentTimeMillis() < startuptime + 3800);
        motors.intakeMethod(1);
        motors.transferMethod(1);

        while (System.currentTimeMillis() < startuptime + 5000);
        motors.transferMethod(0);
        motors.intakeMethod(0);
        feeder.setSevenPos(0.475);

        while (System.currentTimeMillis() < startuptime + 5400);
        feeder.setSevenPos(0);

        while (System.currentTimeMillis() < startuptime + 5800);
    }
}

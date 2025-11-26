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

        waitForStart();
        double startuptime = System.currentTimeMillis();
        motors.shootingMethod(1);
        //TODO: 26.5 cm rijden

        driveTrain.drive(-0.3, 0, 0, true);

        while (System.currentTimeMillis() < startuptime + 1000);
            driveTrain.drive(0, 0, 0, true);
            feeder.setSevenPos(0.475);

        while (System.currentTimeMillis() < startuptime + 1400);
            feeder.setSevenPos(0);

        while (System.currentTimeMillis() < startuptime + 1800);
            motors.intakeMethod(1);
            motors.transferMethod(1);

        while (System.currentTimeMillis() < startuptime +3000);
            motors.transferMethod(0);
            motors.intakeMethod(0);
            feeder.setSevenPos(0.475);

        while (System.currentTimeMillis() < startuptime + 3400);
            feeder.setSevenPos(0);

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

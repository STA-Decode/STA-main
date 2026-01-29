package org.firstinspires.ftc.teamcode.STAuton;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;
import org.firstinspires.ftc.teamcode.RobotParts.DriveTrain;

import java.util.List;

@Autonomous(name = "PassiveAutonomous", group = "PassiveAutonomous")
//Naam van project
public class PassiveAutonomous extends LinearOpMode {
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

        waitForStart();
        if (isStopRequested()) return;

        startuptime = System.currentTimeMillis();
        motors.shootingMethod(1);

        while (opModeIsActive() && state != 1) {
            if (state == 0) {
                    driveTrain.drive(-0.4, 0.75 * Math.PI, 0, true);
                    if (System.currentTimeMillis() > startuptime + 200) {
                        startuptime = System.currentTimeMillis();
                        driveTrain.drive(0, 0, 0, true);
                        state++;


                    }
            }

            telemetry.addData("time", System.currentTimeMillis() - startuptime);
            telemetry.update();
        }
    }
}

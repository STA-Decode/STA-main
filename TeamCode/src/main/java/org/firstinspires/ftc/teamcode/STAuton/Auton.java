package org.firstinspires.ftc.teamcode.STAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;

@Autonomous(name = "Autonomous", group = "Autonomous")
//Naam van project
public class Auton extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    //Slaat op hoe lang de robot is geinitialiseerd


    @Override
    public void runOpMode() throws InterruptedException {
        driveTrain driveTrain = new driveTrain();
        driveTrain.init(hardwareMap);

        waitForStart();
        double startuptime = runtime.milliseconds();
        while (opModeIsActive()) {

            if ((runtime.milliseconds() - startuptime) > 600)
            {
                driveTrain.drive(0, 0, 0, 0, 0);
            } else
            {
                driveTrain.drive(1, 0, 0, -1, 0);
            }



        }
    }
}

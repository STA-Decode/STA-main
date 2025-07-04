package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;


//the name is how this Opmode will show up on the driver-hub
@TeleOp(name = "Opmode", group = "TeleOp")
public class opmode extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    driveTrain drivetrain = new driveTrain();

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);


        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //We'll do something fun here, you will wanna read controller input, and use the methods made in the drivetrain
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rotate = -gamepad1.right_stick_x;
            double speed = 0.7 *gamepad1.right_trigger;

            drivetrain.drive(y,x,rotate,speed);


        }


    }
}
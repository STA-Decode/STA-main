package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;
import org.firstinspires.ftc.teamcode.RobotParts.Motors;

//the namee is how this Opmode will show up on the driver-hub
@TeleOp(name = "Opmode", group = "TeleOp")
public class opmode extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    driveTrain drivetrain = new driveTrain();
    Motors Motors = new Motors();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);
        Motors.init(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        double spin = 0;

        while (opModeIsActive()) {
            //We'll do something fun here, you will wanna read controller input, and use the methods made in the drivetrain
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rotate = -gamepad1.right_stick_x;
            double speed = 1 *gamepad1.right_trigger;

            if (gamepad1.y)
            {
                spin = 1;
            }

            if (gamepad1.b)
            {
                spin = 0;
            }
            drivetrain.drive(y,x,rotate,speed);
            Motors.intakeMethod(spin);
        }



    }
}
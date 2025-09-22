package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;
import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.Servos;


//the namee is how this Opmode will show up on the driver-hub
@TeleOp(name = "Opmode", group = "TeleOp")
public class opmode extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    driveTrain drivetrain = new driveTrain();
    Motors Motors = new Motors();
    Servos Servos = new Servos();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);
        Motors.init(hardwareMap);
        Servos.init(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        double spin = 0;
        double chainSpeed = 0;
        double level = 0;
        double power = 0;
        boolean intakeAllowed = true;
        boolean transportAllowed = true;
        boolean elevatorAllowed = true;

        while (opModeIsActive()) {
            //We'll do something fun here, you will wanna read controller input, and use the methods made in the drivetrain
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rotate = -gamepad1.right_stick_x;
            double speed = 1 *gamepad1.right_trigger;

            //This is the intake
            if (gamepad1.dpad_up && intakeAllowed)
            {
                if (spin == 0)
                {
                    spin = 1;
                }
                else
                {
                    spin = 0;
                }
                intakeAllowed = false;
            }
            if (!gamepad1.dpad_up)
            {
                intakeAllowed = true;
            }

            //this is the chain
            if (gamepad1.dpad_down && transportAllowed)
            {
                if (chainSpeed == 0)
                {
                    chainSpeed = 1;
                }
                else
                {
                    chainSpeed = 0;
                }
                transportAllowed = false;
            }

            if (!gamepad1.dpad_down)
            {
                transportAllowed = true;
            }


            if (gamepad1.left_bumper)
            {
                level = 1;
            }

            if (gamepad1.right_bumper)
            {
                level = 0;
            }

            if (gamepad1.a)
            {
                power = 1;
            } else {
                power = 0;
            }




            drivetrain.drive(y,x,rotate,speed);
            Motors.intakeMethod(spin);
            Motors.shootingMethod(power);
            Motors.transport(chainSpeed);
            Servos.Lift(level);
        }



    }
}
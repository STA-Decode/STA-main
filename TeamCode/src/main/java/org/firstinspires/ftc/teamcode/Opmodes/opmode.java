package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;
import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.Servos;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;

import java.util.List;

//the namee is how this Opmode will show up on the driver-hub
@TeleOp(name = "Opmode", group = "TeleOp")
public class opmode extends LinearOpMode {
    driveTrain drivetrain = new driveTrain();
    Motors Motors = new Motors();
    //Servos Servos = new Servos();
    ServoTest ServoTest = new ServoTest();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);
        Motors.init(hardwareMap);
        //Servos.init(hardwareMap);
        ServoTest.init(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;
        double driveMode = 0;
        double spin = 0;
        double chainSpeed = 0;
        double level = 0;
        double power = 0;
        double SevenPos = 0;
        boolean intakeAllowed = true;
        boolean transportAllowed = true;
        boolean driveSideways = false;
        boolean servoChangeAllowed = true;
        boolean shooterChangeAllowed = true;
        double servoTimer = 0;
        double transferPower = 0;

        Gamepad currentGamepad = new Gamepad(), previousGamepad = new Gamepad();

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        while (opModeIsActive()) {
            previousGamepad.copy(currentGamepad);
            currentGamepad.copy(gamepad1);

            double y = -currentGamepad.left_stick_x;
            double x = -currentGamepad.left_stick_y;
            double rotate = -currentGamepad.right_stick_x;
            double[] polarCoordinates = driveTrain.toPolar(x, y);
            polarCoordinates[0] = driveTrain.exagerateR(polarCoordinates[0]);
            double speed = 1;

            //This is the intake
            if (currentGamepad.x && intakeAllowed)
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
            if (!currentGamepad.x)
            {
                intakeAllowed = true;
            }

            //this is the chain
            if (currentGamepad.dpad_down && transportAllowed)
            {
                if (chainSpeed == 0)
                {
                    chainSpeed = 1;
                    transferPower = 1;
                }
                else
                {
                    chainSpeed = 0;
                    transferPower = 0;
                }
                transportAllowed = false;
            }

            if (!currentGamepad.dpad_down)
            {
                transportAllowed = true;
            }


            if (currentGamepad.left_bumper)
            {
                level = 1;
            }

            if (currentGamepad.right_bumper)
            {
                level = 0;
            }

            if (currentGamepad.a && !previousGamepad.a) {
                if (power == 0) power = 1;
                else power = 0;
            }

            if (currentGamepad.y && !previousGamepad.y) {
                if (driveSideways) driveSideways = false;
                else driveSideways = true;
            }

            if (currentGamepad.b && servoChangeAllowed) {
                SevenPos = 0.475;

                servoTimer = getRuntime();
            }

            if (getRuntime() >= servoTimer + 0.4) {
                servoChangeAllowed = false;
            }


            if (!servoChangeAllowed) {
                SevenPos = 0;
            }
            if (SevenPos == 0) {
                servoChangeAllowed = true;
            }


            drivetrain.drive(polarCoordinates[0], polarCoordinates[1], rotate, driveSideways);
            ServoTest.setSevenPos(SevenPos);
            telemetry.addData("servo pos",SevenPos);
            telemetry.addData("toggle",servoChangeAllowed);
            telemetry.update();
            Motors.intakeMethod(spin);
            Motors.shootingMethod(power);
            Motors.transferMethod(transferPower);
            //Servos.Lift(level);
        }



    }
}
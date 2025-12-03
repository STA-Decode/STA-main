package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.DriveTrain;
import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;

import java.util.List;

//the namee is how this Opmode will show up on the driver-hub
@TeleOp(name = "Opmode", group = "TeleOp")
public class opmode extends LinearOpMode {
    DriveTrain drivetrain = new DriveTrain();
    Motors Motors = new Motors();
    //Servos Servos = new Servos();
    ServoTest ServoTest = new ServoTest();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);
        Motors.init(hardwareMap);
        //Servos.init(hardwareMap);
        ServoTest.init(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;
        double spin = 0;
        double power = 0;
        double FeederPos;
        boolean intake1Allowed = true;
        boolean intake2Allowed = true;
        boolean transport1Allowed = true;
        boolean transport2Allowed = true;
        boolean driveSideways = false;
        boolean servoChangeAllowed = true;
        boolean twintakeAllowed = true;
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
            double[] polarCoordinates = DriveTrain.toPolar(x, y);
            polarCoordinates[0] = DriveTrain.exaggerateR(polarCoordinates[0]);

            //This is the intake
            if (currentGamepad.x && twintakeAllowed)
            {
                if (spin == 0)
                {
                    spin = 1;
                    transferPower = 1;
                }
                else
                {
                    spin = 0;
                    transferPower = 0;
                }
                twintakeAllowed = false;
            }
            if (!currentGamepad.x)
            {
                twintakeAllowed = true;

            }

            if (currentGamepad.dpad_down && transport1Allowed)
            {
                if (transferPower == 0)
                {

                    transferPower = 1;
                }
                else
                {
                    transferPower = 0;
                }
                transport1Allowed = false;
            }

            if (!currentGamepad.dpad_down)
            {
                transport1Allowed = true;
            }

            if (currentGamepad.dpad_up && transport2Allowed)
            {
                if (transferPower == 0)
                {

                    transferPower = -1;
                }
                else
                {
                    transferPower = 0;
                }
                transport2Allowed = false;
            }

            if (!currentGamepad.dpad_up)
            {
                transport2Allowed = true;
            }

            if (currentGamepad.dpad_right && intake1Allowed)
            {
                if (spin == 0)
                {

                    spin = 1;
                }
                else
                {
                    spin = 0;
                }
                intake1Allowed = false;
            }

            if (!currentGamepad.dpad_right)
            {
                intake1Allowed = true;
            }

            if (currentGamepad.dpad_left && intake2Allowed)
            {
                if (spin == 0)
                {

                    spin = -1;
                }
                else
                {
                    spin = 0;
                }
                intake2Allowed = false;
            }

            if (!currentGamepad.dpad_left)
            {
                intake2Allowed = true;
            }



            if (currentGamepad.a && !previousGamepad.a) {
                if (power == 0) power = 1;
                else power = 0;
            }

            if (currentGamepad.y && !previousGamepad.y) {
                if (driveSideways) driveSideways = false;
                else driveSideways = true;
            }

            if (currentGamepad.b && servoChangeAllowed)
            {
                servoTimer = runtime.milliseconds();

            }

            if (runtime.milliseconds() < servoTimer + 400)
            {
                FeederPos = 0.450;
                ServoTest.setSevenPos(0.450);
            }
            else
            {
                FeederPos = 0;
                ServoTest.setSevenPos(0);
                servoChangeAllowed = true;
            }




            //Let hiet niet op dit is tijdelijke fucked up code

            /*if (gamepad2.b && servoTimerAllowed)
            {
                servoTimer = runtime.milliseconds();
                servoTimerAllowed = false;
            }

            if (!gamepad2.b)
            {
                servoTimer = 1101;
                servoTimerAllowed = true;
            }

            if (runtime.milliseconds() < servoTimer + 400)
            {
                ServoTest.setSevenPos(0.475);
            }

            if (runtime.milliseconds() < servoTimer + 200)
            {
                ServoTest.setSevenPos(0);
            }

            if (runtime.milliseconds() > servoTimer + 525 && runtime.milliseconds() < servoTimer + 1100){
                spin = 1;
                transferPower = 1;
            }*/

            drivetrain.drive(polarCoordinates[0], polarCoordinates[1], rotate, driveSideways);
            telemetry.addData("servo pos",FeederPos);
            telemetry.addData("toggle",servoChangeAllowed);
            telemetry.addData("power",power);
            telemetry.update();
            Motors.intakeMethod(spin);
            Motors.shootingMethod(power);
            Motors.transferMethod(transferPower);
            //Servos.Lift(level);
        }



    }
}
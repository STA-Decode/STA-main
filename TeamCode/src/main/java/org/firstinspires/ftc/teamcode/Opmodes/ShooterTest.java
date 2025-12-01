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
@TeleOp(name = "ShooterTest", group = "TeleOp")
public class ShooterTest extends LinearOpMode {
    driveTrain drivetrain = new driveTrain();
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
        double FeederPos = 0;
        boolean intake1Allowed = true;
        boolean intake2Allowed = true;
        boolean transport1Allowed = true;
        boolean transport2Allowed = true;
        boolean servoChangeAllowed = true;
        boolean twintakeAllowed = true;
        boolean shooterChangeAllowed = true;
        boolean servoTimerAllowed = true;
        double servoTimer = 0;
        double transferPower = 0;

        Gamepad currentGamepad = new Gamepad(), previousGamepad = new Gamepad();

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
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






        if (gamepad1.b && servoTimerAllowed) {
            servoTimer = runtime.milliseconds();
            servoTimerAllowed = false;


            if (!gamepad2.b) {
                servoTimer = 0;
                servoTimerAllowed = true;
            }

            if (runtime.milliseconds() < servoTimer + 2000) {
                ServoTest.setSevenPos(0.475);
                spin = 1;
            }

            if (runtime.milliseconds() > servoTimer + 2000 && runtime.milliseconds() < servoTimer + 4000) {
                ServoTest.setSevenPos(0);
                spin = 0;
            }

            if (runtime.milliseconds() > servoTimer + 4000 && runtime.milliseconds() < servoTimer + 5000) {
                transferPower = 1;
            }
        }





        ServoTest.setSevenPos(FeederPos);
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

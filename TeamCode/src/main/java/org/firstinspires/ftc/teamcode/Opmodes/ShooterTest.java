package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;
import org.firstinspires.ftc.teamcode.RobotParts.Motors;
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
        boolean scoreSequence = false;
        double servoTimer = 0;
        double transferPower = 0;
        int runs = 0;

        Gamepad currentGamepad = new Gamepad(), previousGamepad = new Gamepad();

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        while (opModeIsActive()) {
            previousGamepad.copy(currentGamepad);
            currentGamepad.copy(gamepad1);

            if (currentGamepad.b && !previousGamepad.b && !scoreSequence) {
                servoTimer = runtime.milliseconds();
                scoreSequence = true;
            }

            if (scoreSequence) {
                if (runtime.milliseconds() < servoTimer + 2000) {
                    ServoTest.setSevenPos(0.475);
                    spin = 1;

                } else if (runtime.milliseconds() < servoTimer + 4000) {
                    ServoTest.setSevenPos(0);
                    spin = 0;

                } else if (runtime.milliseconds() < servoTimer + 5000) {
                    transferPower = 1;
                } else if (runtime.milliseconds() < servoTimer + 7000) {
                    transferPower = 0;
                } else {
                    scoreSequence = false;
                }
            }

            runs++;
            telemetry.addData("runs", runs);
            telemetry.addData("servo pos", FeederPos);
            telemetry.addData("toggle", servoChangeAllowed);
            telemetry.addData("power", power);
            telemetry.update();
            Motors.intakeMethod(spin);
            Motors.shootingMethod(power);
            Motors.transferMethod(transferPower);
            //Servos.Lift(level);
        }
    }
}

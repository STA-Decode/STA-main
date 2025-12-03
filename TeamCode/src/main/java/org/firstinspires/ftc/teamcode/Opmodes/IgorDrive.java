package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotParts.Motors;
import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;
import org.firstinspires.ftc.teamcode.RobotParts.driveTrain;

import java.util.List;

//the name is how this Opmode will show up on the driver-hub
@TeleOp(group = "TeleOp")
public class IgorDrive extends LinearOpMode {
    driveTrain drivetrain = new driveTrain();
    Motors Motors = new Motors();
    ServoTest ServoTest = new ServoTest();
    private final ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init(hardwareMap);
        Motors.init(hardwareMap);
        //Servos.init(hardwareMap);
        ServoTest.init(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;
        double power = 0;
        double FeederPos = 0;
        boolean driveSideways = false;
        boolean servoChangeAllowed = false;
        boolean outtaking = false;
        double servoTimer = 0;

        Gamepad currentGamepad1 = new Gamepad(), previousGamepad1 = new Gamepad(),
                currentGamepad2 = new Gamepad(), previousGamepad2 = new Gamepad();

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            double y = -currentGamepad1.left_stick_x;
            double x = -currentGamepad1.left_stick_y;
            double rotate = -currentGamepad1.right_stick_x;
            double[] polarCoordinates = driveTrain.toPolar(x, y);
            polarCoordinates[0] = driveTrain.exagerateR(polarCoordinates[0]);

            //This is the intake
            Motors.intakeMethod(currentGamepad1.right_trigger - currentGamepad1.left_trigger);

            Motors.transferMethod(currentGamepad2.right_trigger - currentGamepad2.left_trigger);

            if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
                outtaking ^= true;
                Motors.shootingMethod(outtaking ? 1 : 0);
            }

            if (currentGamepad1.y && !previousGamepad1.y) {
                driveSideways = !driveSideways;
            }

            if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper && !servoChangeAllowed) {
                servoTimer = runtime.milliseconds();
                servoChangeAllowed = true;
                ServoTest.setSevenPos(0.450);
            }

            if (servoChangeAllowed) {
                if (runtime.milliseconds() > servoTimer + 400) {
                    ServoTest.setSevenPos(0);
                    servoChangeAllowed = false;
                }
            }

            drivetrain.drive(polarCoordinates[0], polarCoordinates[1], rotate, driveSideways);
            telemetry.addData("servo pos",FeederPos);
            telemetry.addData("toggle",servoChangeAllowed);
            telemetry.addData("power",power);
            telemetry.update();
        }
    }
}
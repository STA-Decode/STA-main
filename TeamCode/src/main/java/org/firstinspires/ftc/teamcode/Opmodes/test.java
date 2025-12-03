package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotParts.ServoTest;

@TeleOp(name = "Lucas testing ur servo")
public class test extends LinearOpMode {
    ServoTest servoTest = new ServoTest();

    @Override
    public void runOpMode() throws InterruptedException {
        servoTest.init(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_bumper)
            {
                servoTest.setSevenPos(0);
            }

            if (gamepad1.right_bumper)
            {
                servoTest.setSevenPos(0.3);
            }
        }
    }
}

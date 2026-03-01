package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotParts.Encoder;


import java.util.List;

//the namee is how this Opmode will show up on the driver-hub
@TeleOp(name = "EncoderTest", group = "TeleOp")
public class EncoderTest extends LinearOpMode {

    Encoder encoder = new Encoder();
    double JoyStickPos = 0;



    @Override
    public void runOpMode() {

        encoder.init(hardwareMap, telemetry);

        waitForStart();

        Gamepad currentGamepad = new Gamepad(), previousGamepad = new Gamepad();

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        while (opModeIsActive()) {

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;

            double angle = Math.atan2(x, y);
            if (angle < 0) {
                angle += 2 * Math.PI;
            }



            encoder.updateTelemetry();
            encoder.encoder(x, y);

            telemetry.update();



        }
    }
}

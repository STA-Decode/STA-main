package org.firstinspires.ftc.teamcode.RobotParts;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
import  com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class Encoder{

    HardwareMap hardwareMap;
    Telemetry telemetry;

    DcMotor IgorLeft;
    DcMotor IgorRight;
    DcMotor DeanLeft;
    DcMotor DeanRight;

    double ticks = 384.5;

    public void init(HardwareMap hardwareMap, Telemetry telemetry){

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        IgorLeft = hardwareMap.get(DcMotor.class, "IgorLeft");
        IgorRight = hardwareMap.get(DcMotor.class, "IgorRight");
        DeanLeft = hardwareMap.get(DcMotor.class, "DeanLeft");
        DeanRight = hardwareMap.get(DcMotor.class, "DeanRight");

        IgorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IgorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DeanLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DeanRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Hardware: ", "Initialized");


    }



    private double lastTarget = 0;
    public void encoder(double JoyStickX, double JoyStickY) {

        if (Math.hypot(JoyStickX, JoyStickY) > 0.1) {
            double angle = Math.atan2(JoyStickY, JoyStickX);
            if (angle < 0) {
                angle += 2 * Math.PI;
            }

            double newTarget = (angle / (2 * Math.PI)) * ticks;

            double delta = newTarget - lastTarget;
            if (delta > ticks / 2) delta -= ticks;
            if (delta < -ticks / 2) delta += ticks;

            lastTarget += delta;
        }

        double rate = 0.004;

        double current = IgorLeft.getCurrentPosition();
        double error = lastTarget - current;

        if (error > ticks / 2) error -= ticks;
        if (error < -ticks / 2) error += ticks;

        double power = error * rate;
        if (Math.abs(error) < 5) power = 0;
        power = Math.max(-1, Math.min(1, power));



        IgorLeft.setPower(power);
        IgorRight.setPower(power);
        DeanLeft.setPower(power);
        DeanRight.setPower(power);






    }

    public void updateTelemetry() {
        telemetry.addData("IgorLeft Pos:", IgorLeft.getCurrentPosition());
        telemetry.addData("IgorRight Pos:", IgorRight.getCurrentPosition());
        telemetry.addData("DeanLeft Pos:", DeanLeft.getCurrentPosition());
        telemetry.addData("DeanRight Pos:", DeanRight.getCurrentPosition());
    }
}

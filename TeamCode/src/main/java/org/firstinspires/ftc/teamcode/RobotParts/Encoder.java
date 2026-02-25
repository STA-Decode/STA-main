package org.firstinspires.ftc.teamcode.RobotParts;

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

    double newTarget1;
    double newTarget2;
    double newTarget3;
    double newTarget4;

    public void init(HardwareMap hardwareMap, Telemetry telemetry){

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        IgorLeft = hardwareMap.get(DcMotor.class, "IgorLeft");
        IgorRight = hardwareMap.get(DcMotor.class, "IgorRight");
        DeanLeft = hardwareMap.get(DcMotor.class, "DeanLeft");
        DeanRight = hardwareMap.get(DcMotor.class, "DeanRight");

        telemetry.addData("Hardware: ", "Initialized");


    }




    public void Encoder(int turnage) {

        IgorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        IgorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DeanLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DeanRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        IgorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IgorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DeanLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DeanRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        newTarget1 = ticks / turnage;
        newTarget2 = ticks / turnage;
        newTarget3 = ticks / turnage;
        newTarget4 = ticks / turnage;

        IgorLeft.setTargetPosition((int ) newTarget1);
        IgorRight.setTargetPosition((int) newTarget2);
        DeanLeft.setTargetPosition((int) newTarget3);
        DeanRight.setTargetPosition((int) newTarget4);

        IgorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        IgorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DeanLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DeanRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        IgorLeft.setPower(1);
        IgorRight.setPower(1);
        DeanLeft.setPower(1);
        DeanRight.setPower(1);






    }

    public void updateTelemetry() {
        telemetry.addData("IgorLeft Pos:", IgorLeft.getCurrentPosition());
        telemetry.addData("IgorRight Pos:", IgorRight.getCurrentPosition());
        telemetry.addData("DeanLeft Pos:", DeanLeft.getCurrentPosition());
        telemetry.addData("DeanRight Pos:", DeanRight.getCurrentPosition());
    }
}

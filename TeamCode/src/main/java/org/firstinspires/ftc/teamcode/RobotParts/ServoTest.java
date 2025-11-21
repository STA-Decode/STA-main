package org.firstinspires.ftc.teamcode.RobotParts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class ServoTest
{
    private Servo Seven;

    public void init(HardwareMap map)
    {
        Seven = map.get(Servo.class, "Seven");


    }
    public void setSevenPos(double SevenPos){
        Seven.setPosition(SevenPos);
    }

}


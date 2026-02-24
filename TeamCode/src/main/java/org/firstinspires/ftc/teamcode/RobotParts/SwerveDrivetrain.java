/*
Hey! For the practice game, we've decided to give you guys a very basic example of what a drivetrain might look like.

You'll see two main methods here:
    - rotate()
    - drive()

I will explain how everything works along the way. Later (in the practice game or in the real season), you can build a better one.

Some things you'll want in a drivetrain that this one can't do include:
    - Driving and turning at the same time
    - Driving sideways (yes this is possible its very cool we have cool wheels for this :) )
    - More stuff but I can't think of anything rn

I won't explain the basics of java, but i will explain FTC-programming. I'd recommend having some prior programming knowledge (of stuff like variables, functions, etc.) but being a Java expert isn't necessary.
~Lucas
*/


package org.firstinspires.ftc.teamcode.RobotParts;
import  com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SwerveDrivetrain
{
    private DcMotorEx IgorLeft;
    private DcMotorEx IgorRight;
    private DcMotorEx DeanLeft;
    private DcMotorEx DeanRight;

    //When you start your main program you have to call init() on every class used.
    //In the init, we do stuff that has to happen exactly once in our program
    public void init(HardwareMap map){
        //Here, we are linking the motors we just declared with actual motors on our robot. In your HardwareMap, you will assign a name to different motors (making the HardwareMap doesn't happen in the code, just ask someone how to do it and be prepared for a lot of following wires)
        IgorLeft = map.get(DcMotorEx.class, "left_front");
        IgorRight = map.get(DcMotorEx.class, "right_front");
        DeanLeft = map.get(DcMotorEx.class, "left_back");
        DeanRight = map.get(DcMotorEx.class, "right_back");

        //These lines make sure that when the motor is unpowered, it won't move due to external forces like gravity, but it's actively trying to stay in it's current position
        IgorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IgorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DeanLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DeanRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




    }



    public void addTelemetry(Telemetry telemetry) {
        telemetry.addData("IgorL", IgorLeft.getPower());
        telemetry.addData("IgorR", IgorRight.getPower());
        telemetry.addData("DeanL", DeanLeft.getPower());
        telemetry.addData("DeanR", DeanRight.getPower());
    }


    public void spin(double speed){

        IgorLeft.setPower(speed);
        IgorRight.setPower(speed);
        DeanLeft.setPower(speed);
        DeanRight.setPower(speed);
        //IMPORTANT: if you notice the robots wheels are not turning in the direction you expected, it is probably because of the fact you used gears in your robot. This can make the turning-direction flip, so if thats the case, just put a "-" before "speed" for the motor having problems
    }

//igorleft en right postifief = wiel naar rechts

    public static double exaggerateR(double r) {return Math.sin(2*Math.PI*r) /9 + r;}


    //The method drive() is a lot like rotate()
    public void drive(double x, double y, double rotate){
        IgorLeft.setPower(x + rotate );
        IgorRight.setPower(-x - rotate );
        DeanLeft.setPower(x + y - rotate);
        DeanRight.setPower(x + y + rotate);
    }


}

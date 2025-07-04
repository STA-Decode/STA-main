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
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrain
{
    //Here, we declare our parts and other stuff, so that the computer knows they exist, and what type they are (type like servo, motor, etc.)
    private DcMotorEx leftFront;
    private DcMotorEx rightFront;
    private DcMotorEx leftBack;
    private DcMotorEx rightBack;

    //When you start your main program you have to call init() on every class used.
    //In the init, we do stuff that has to happen exactly once in our program
    public void init(HardwareMap map){
        //Here, we are linking the motors we just declared with actual motors on our robot. In your HardwareMap, you will assign a name to different motors (making the HardwareMap doesn't happen in the code, just ask someone how to do it and be prepared for a lot of following wires)
        leftFront = map.get(DcMotorEx.class, "left_front");
        rightFront = map.get(DcMotorEx.class, "right_front");
        leftBack = map.get(DcMotorEx.class, "left_back");
        rightBack = map.get(DcMotorEx.class, "right_back");

        //These lines make sure that when the motor is unpowered, it won't move due to external forces like gravity, but it's actively trying to stay in it's current position
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //The method rotate() is used whenever we wanna... you guessed it: rotate
    public void rotate(double speed){
        //quick note: a negative speed will make it turn counterclockwise

        //Here, we are setting the power (a value between -1 and 1) to our motors
        leftFront.setPower(speed);
        leftBack.setPower(speed);
        rightFront.setPower(speed);
        rightBack.setPower(speed);
        //IMPORTANT: if you notice the robots wheels are not turning in the direction you expected, it is probably because of the fact you used gears in your robot. This can make the turning-direction flip, so if thats the case, just put a "-" before "speed" for the motor having problems
    }

    //The method drive() is a lot like rotate()
    public void drive(double y, double x,double rotate, double speed){
        leftFront.setPower(speed*(y - x + rotate));
        leftBack.setPower(speed*(y + x + rotate));
        rightFront.setPower(-speed*(y + x - rotate));
        rightBack.setPower(-speed*(y - x - rotate));
    }

}

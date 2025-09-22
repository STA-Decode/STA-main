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

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Servos
{
    //Here, we declare our parts and other stuff, so that the computer knows they exist, and what type they are (type like servo, motor, etc.)
    private Servo elevator;


    //When you start your main program you have to call init() on every class used.
    //In the init, we do stuff that has to happen exactly once in our program
    public void init(HardwareMap map){
        //Here, we are linking the motors we just declared with actual motors on our robot. In your HardwareMap, you will assign a name to different motors (making the HardwareMap doesn't happen in the code, just ask someone how to do it and be prepared for a lot of following wires)
        //elevator = map.get(Servo.class, "elevator");

    }

    //The method rotate() is used whenever we wanna... you guessed it: rotate

    public void Lift(double level){

        //elevator.setPosition(level);
        }




}

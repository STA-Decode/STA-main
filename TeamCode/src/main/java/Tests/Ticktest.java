    package Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.RobotParts.DriveTrain;


    @TeleOp(name = "Ticktest", group = "TeleOp")
public class    Ticktest extends LinearOpMode {
    private final int TICKS_PER_REVOLUTION = 8192;
    private final double WHEEL_DIAMETER_MM = 34.0;
    private final double TICKS_PER_CM = TICKS_PER_REVOLUTION / (WHEEL_DIAMETER_MM * Math.PI);
    double ticks = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain driveTrain = new DriveTrain();
        driveTrain.init(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        ticks = driveTrain.getEncoderData()[0];

        Gamepad currentGamepad1 = new Gamepad(), previousGamepad1 = new Gamepad(),
                currentGamepad2 = new Gamepad(), previousGamepad2 = new Gamepad();

        while (opModeIsActive()) {

            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            double y = -currentGamepad1.left_stick_x;
            double x = -currentGamepad1.left_stick_y;
            double rotate = -currentGamepad1.right_stick_x;
            double[] polarCoordinates = DriveTrain.toPolar(x, y);
            polarCoordinates[0] = DriveTrain.exaggerateR(polarCoordinates[0]);
        }
        telemetry.addData("ticks", ticks);
        telemetry.update();
    }
}
package org.firstinspires.ftc.teamcode.auto_classes.drivers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.DriverConfiguration;
import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Analog_Action.drivex;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Analog_Action.drivey;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Analog_Action.turn;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.faceDown;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.faceLeft;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.faceRight;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.faceUp;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.resetGyro;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.swapDriveMode;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.turnLeft;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.turnRight;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog.Analog.left_stick_x;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog.Analog.left_stick_y;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog.Analog.right_stick_y;

@TeleOp(name="Default Normal", group="9108") //fix this
public class TeleOpMecanum extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot = new Robot(gamepad1,gamepad2);

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize(hardwareMap,telemetry);
        Robot.Driver1 = new DriverConfiguration(Robot, gamepad1);
        Robot.Driver2 = new DriverConfiguration(Robot, gamepad2);
        Robot.Driver1.assignDebounce(500);
        Robot.Driver1.assignAnalog(                         left_stick_x, drivex);
        Robot.Driver1.assignAnalog(                         left_stick_y, drivey);
        Robot.Driver1.assignSign(                           left_stick_y, ButtonAnalog.SIGN.NEGATIVE);
        Robot.Driver1.assignAnalog(                         right_stick_y, turn);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     y, swapDriveMode);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     dpad_up, faceUp);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     dpad_right, faceRight);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     dpad_down, faceDown);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     dpad_left, faceLeft);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     left_bumper, turnLeft);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     right_bumper, turnRight);
        Robot.Driver1.assignBinary(ButtonBinary.Binary.     back, resetGyro);
        Robot.DCGm.setRelativeDrive(true);
        Robot.RHG.Hubs[0].setLedColor(255,255,255);
        telemetry.addData("Status","Complete");
        telemetry.update();
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        runtime.reset();
        telemetry.setAutoClear(true);
    }

    boolean on;

    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
        Robot.run();

        /*
        //Lift
        double lift = gamepad2.left_stick_y;
        double speed = lift * 100;
        Robot.DCGl.raiseToInch(0.1,speed);

        if (gamepad2.a) {
            Robot.DCGl.raiseToBlock(-1,75);
        }
        if (gamepad2.y) {
            Robot.DCGl.raiseToBlock(1,75);
        }

        //Intake
        if (on == true) {
            Robot.DCGi.setPower(new double[]{1});
        }
        if (on == false) { 
            Robot.DCGi.setPower(new double[]{0});
        }

        if (gamepad2.left_trigger == 1) {
            on = true;
        }

        if (gamepad2.right_trigger == 1) {
            on = false;
        }
         */

        Robot.IMU.composeTelemetry(telemetry);
        Robot.DCGm.composeTelemetry(telemetry);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}

package org.firstinspires.ftc.teamcode.team_classes.robot;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends ServoGroup {

    public Intake() {
        super(new Servo[2]);
    }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.Servos[0] = Hmap.get(Servo.class, "Intake_servo");
        this.Servos[1] = Hmap.get(Servo.class,"Intake_Holder");
    }

    public void grab(){
        this.Servos[0].setPosition(1);
    }

    public void drop(){
        this.Servos[0].setPosition(0);
    }

    public void rotateToAngle(double angleTheta){
        double possition = angleTheta / 180;
        this.Servos[0].setPosition(possition);
    }

    public void activateIntake(){
        this.Servos[1].setPosition(1);
    }

    public void deactivateIntake(){
        this.Servos[1].setPosition(0);
    }
}
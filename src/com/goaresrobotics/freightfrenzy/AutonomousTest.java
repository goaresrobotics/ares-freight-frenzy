package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.sql.Time;

@Autonomous(name="autonomousTest")

public class AutonomousTest extends OpMode {

    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor leftBackMotor;
    DcMotor rightBackMotor;

        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init() {
            /* Initialize the hardware variables.
             * The init() method of the hardware class does all the work here
             */

            leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
            leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
            rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
            leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
            rightBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);

            // Send telemetry message to signify robot waiting;
            telemetry.addData("Say", "Hello Driver");
        }

    @Override
    public void start(){
            DriveForward(1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TurnRight(1);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DriveForward(1);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loop() {

    }

    public void DriveForward(double power){
            leftFrontMotor.setPower(power);
            leftBackMotor.setPower(power);
            rightBackMotor.setPower(power);
            rightFrontMotor.setPower(power);
        }
    public void TurnRight(double power){
            leftFrontMotor.setPower(power);
            leftBackMotor.setPower(power);
            rightFrontMotor.setPower(-power);
            rightBackMotor.setPower(-power);
    }


}
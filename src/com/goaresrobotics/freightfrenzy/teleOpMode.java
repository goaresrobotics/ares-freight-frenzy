package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="driveTest")

public class teleOpMode extends OpMode {

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
            rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
            leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
            rightBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);

            // Send telemetry message to signify robot waiting;
            telemetry.addData("Say", "Hello Driver");    //
        }
        /*
         * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
         */
        @Override
        public void loop() {
            double left;
            double right;
            double modifier;

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;
            modifier = 0.5;

            leftFrontMotor.setPower(left * modifier);
            rightFrontMotor.setPower(right * modifier);
            leftBackMotor.setPower(left * modifier);
            rightBackMotor.setPower(right * modifier);
        }
}
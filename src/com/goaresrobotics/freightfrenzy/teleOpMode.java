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
    DcMotor duckTurnMotor;
    DcMotor intakeMotor;


        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init() {
            /* Initialize the hardware variables.
             * The init() method of the hardware class does all the work here
             */

            leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
            leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
            rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
            leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
            rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            duckTurnMotor = hardwareMap.get(DcMotor.class, "duckTurnMotor");
            duckTurnMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");


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
            float rTrigger;
            float lTrigger;
            double modifier;
            double intake;

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;
            rTrigger = gamepad1.right_trigger;
            lTrigger = gamepad1.left_trigger;
            intake = gamepad2.left_stick_y;
            modifier = 0.6;

            leftFrontMotor.setPower(left * modifier);
            rightFrontMotor.setPower(right * modifier);
            leftBackMotor.setPower(left * modifier);
            rightBackMotor.setPower(right * modifier);
            intakeMotor.setPower(intake);

            if (lTrigger > 0) {
                duckTurnMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                duckTurnMotor.setPower(lTrigger);
            }
            if (rTrigger > 0) {
                duckTurnMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                duckTurnMotor.setPower(rTrigger);
            }
        }
}
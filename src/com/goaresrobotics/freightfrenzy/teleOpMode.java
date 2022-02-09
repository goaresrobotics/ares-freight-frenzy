package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="teleOpMode")

public class teleOpMode extends OpMode {

    DcMotor  leftFront;
    DcMotor  rightFront;
    DcMotor  leftBack;
    DcMotor  rightBack;
    DcMotor  duckTurn;
    DcMotor  intakeMotor;

    AresRobotHardware robot   = new AresRobotHardware();

        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init() {

            robot.init(hardwareMap);

            // Send telemetry message to signify robot waiting;
            telemetry.addData("Say", "Hello Driver");
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
            double intake;
            double modifier;
            double intakeModifier;
            
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;
            rTrigger = gamepad1.right_trigger;
            lTrigger = gamepad1.left_trigger;
            intake = gamepad2.left_stick_y;
            modifier = 0.6;
            intakeModifier = 0.7;

            leftFront.setPower(left * modifier);
            rightFront.setPower(right * modifier);
            leftBack.setPower(left * modifier);
            rightBack.setPower(right * modifier);
            intakeMotor.setPower(intake * intakeModifier);

            if (lTrigger > 0) {
                duckTurn.setDirection(DcMotorSimple.Direction.REVERSE);
                duckTurn.setPower(lTrigger);
            } else {
                duckTurn.setPower(0);
            }
            if (rTrigger > 0) {
                duckTurn.setDirection(DcMotorSimple.Direction.FORWARD);
                duckTurn.setPower(rTrigger);
            } else {
                duckTurn.setPower(0);
            }
        }
}
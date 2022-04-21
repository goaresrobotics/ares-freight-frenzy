package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="MecanumD")

public class MecanumD extends OpMode {

    public DcMotor  leftFront   = null;
    public DcMotor  rightFront  = null;
    public DcMotor  leftBack = null;
    public DcMotor  rightBack = null;
    public DcMotor  duckTurn = null;
    public DcMotor  intakeMotor = null;



        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init() {

            leftFront = hardwareMap.get(DcMotor.class, "leftFrontMotor");
            leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightFront = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
            leftBack = hardwareMap.get(DcMotor.class, "leftBackMotor");
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
            rightBack = hardwareMap.get(DcMotor.class, "rightBackMotor");
            rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
            duckTurn = hardwareMap.get(DcMotor.class, "duckTurnMotor");
            duckTurn.setDirection(DcMotorSimple.Direction.FORWARD);
            intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
            intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);


            // Send telemetry message to signify robot waiting;
            telemetry.addData("Say", "Hello Driver");
        }
        /*
         * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
         */
        @Override
        public void loop() {
            double forwardBack;
            double leftRight;
            float rTrigger;
            float lTrigger;
            double intake;
            double modifier;
            double turnModifier;
            double intakeModifier;

            forwardBack = gamepad1.left_stick_y;
            leftRight = gamepad1.right_stick_x;
            rTrigger = gamepad1.right_trigger;
            lTrigger = gamepad1.left_trigger;
            intake = gamepad2.left_stick_y;
            modifier = 0.6;
            turnModifier = 0.4;
            intakeModifier = 0.4;

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            leftFront.setPower(frontLeftPower * 0.4);
            leftBack.setPower(backLeftPower * 0.4);
            rightFront.setPower(frontRightPower * 0.4);
            rightBack.setPower(backRightPower * 0.4);

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
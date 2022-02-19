package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="teleOpMode")

public class teleOp extends OpMode {

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
            leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
            rightFront = hardwareMap.get(DcMotor.class, "rightFrontMotor");
            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack = hardwareMap.get(DcMotor.class, "leftBackMotor");
            leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBack = hardwareMap.get(DcMotor.class, "rightBackMotor");
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
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
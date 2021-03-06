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

            leftFront.setPower(forwardBack * modifier);
            rightFront.setPower(forwardBack * modifier);
            leftBack.setPower(forwardBack * modifier);
            rightBack.setPower(forwardBack * modifier);
            intakeMotor.setPower(intake * intakeModifier);

            if (gamepad1.right_stick_x > 0) {
                leftFront.setPower(-gamepad1.right_stick_x * turnModifier);
                leftBack.setPower(-gamepad1.right_stick_x * turnModifier);
                rightFront.setPower(gamepad1.right_stick_x * turnModifier);
                rightBack.setPower(gamepad1.right_stick_x * turnModifier);
            }

            if (gamepad1.right_stick_x < 0) {
                leftFront.setPower(-gamepad1.right_stick_x * turnModifier);
                leftBack.setPower(-gamepad1.right_stick_x * turnModifier);
                rightFront.setPower(gamepad1.right_stick_x * turnModifier);
                rightBack.setPower(gamepad1.right_stick_x * turnModifier);
            }

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
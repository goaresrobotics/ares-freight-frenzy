package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name= "mecanu mDrive")

public class MecanumDrive extends OpMode {

    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor leftBackMotor;
    DcMotor rightBackMotor;

    public void moveDriveTrain(){
        double vertical;
        double horizontal;
        double pivot;
        vertical = gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        rightFrontMotor.setPower(pivot + (-vertical + horizontal));
        rightBackMotor.setPower(pivot + (-vertical - horizontal));
        leftFrontMotor.setPower(pivot + (-vertical - horizontal));
        leftBackMotor.setPower(pivot + (-vertical + horizontal));
    }

    @Override
    public void init() {
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
    }

    @Override
    public void loop() {

    }
}

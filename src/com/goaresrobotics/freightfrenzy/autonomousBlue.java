package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.sql.Time;

@Autonomous(name="autonomousBlue")

public class autonomousBlue extends OpMode {

    // Motor definitions
    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;

        @Override
        public void init() {
            /* Initialize the hardware variables.
             * The init() method of the hardware class does all the work here
             */

            leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
            leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
            rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
            leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
            rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Send telemetry message to signify robot waiting;
            telemetry.addData("Say", "Hello Driver");

        }

        @Override
        public void start() {

        }
    @Override
    public void loop()
    {

                DriveForward(0.6);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                TurnLeft(0.6);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                DriveForward(0.6);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Stop();
    }

    @Override
    public void stop()
    {
        /*
         Called when the driver presses STOP
         Set a flag here so we know when we are stopped and don't accidentally run EncoderDrive
         */
        telemetry.addData("Say", "Done!");
    }

    public void DriveForward(double power)
    {
        leftFrontMotor.setPower(power);
        leftBackMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);
        telemetry.addData("Say", "Driving Forward");
        telemetry.update();
    }
    public void TurnLeft(double power)
    {
        leftFrontMotor.setPower(-power);
        leftBackMotor.setPower(-power);
        rightFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        telemetry.addData("Say", "Turning Left");
        telemetry.update();
    }
    public void Stop()
    {
        leftFrontMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightFrontMotor.setPower(0);
        rightBackMotor.setPower(0);
        telemetry.addData("Say", "Stopping");
        telemetry.update();
    }

    /*
     * Perform a relative move using the encoders
     * Stop if any of the following are true:
     * 1) Move gets to the commanded position
     * 2) Timeout value is reached
     * 3) Driver stops the opmode
     */

    /**
     * @brief Moves the robot a specified number of inches at a specified speed
     * @param Speed power for all motors
     * @param leftInches number of inches to drive the left motor
     * @param rightInches number of inches to drive the right motor
     * @param timeout seconds the method is allowed to run before stopping ( put in place in case
     *                something goes wrong and it doesn't reach the desired position )
     */
    private void EncoderDrive(double Speed, double leftInches, double rightInches, double timeout)
    {

    }

}
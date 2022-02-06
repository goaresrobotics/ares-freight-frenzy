package com.goaresrobotics.freightfrenzy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.sql.Time;

@Autonomous(name="autonomousTest")

public class AutonomousTest extends OpMode {

    // Motor definitions
    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;

    private boolean opModeStopped = false;

    private int state = 0;
    private int next_state;
    // States
    static final int INIT = 0;
    static final int START = 1;
    static final int DRIVING_FORWARD = 2;
    static final int STOP = 3;
    static final int TURN_LEFT = 4;
    static final int DONE = 5;

    // encoder details
    static final double COUNTS_PER_MOTOR_REV = 10;
    static final double GEARBOX_RATIO = 20;
    static final double WHEEL_DIAMETER_INCHES = 12.56;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * GEARBOX_RATIO) /
            (WHEEL_DIAMETER_INCHES * 3.1459);

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
        switch(state)
        {
            case INIT:
                // code to run when state == 0
                // ERROR condition
                break;

            case START:
                DriveForward(0.6);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state = TURN_LEFT;
                break;

            case TURN_LEFT:
                TurnLeft(0.6);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state = DRIVING_FORWARD;
                break;

            case DRIVING_FORWARD:
                DriveForward(0.6);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state = STOP;
                // wait to until we get to position
                // when get to position state = next_state
                break;
            case STOP:

                default:
                break;
        } // end switch
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop()
    {
        /*
         Called when the driver presses STOP
         Set a flag here so we know when we are stopped and don't accidentally run EncoderDrive
         */
        opModeStopped = true;
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
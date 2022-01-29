package com.goaresrobotics.examples;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="StateMachineEx")

public class StateMachineExample extends OpMode {

    // Motor definitions
    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;
    private DcMotor duckTurnMotor;

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
    static final int DRIVING_FORWARD_2 = 6;
    static final int TURN_RIGHT = 7;

    // encoder details
    static final double COUNTS_PER_MOTOR_REV = 10;
    static final double GEARBOX_RATIO = 20;
    static final double WHEEL_DIAMETER_INCHES = 4;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * GEARBOX_RATIO) /
            (WHEEL_DIAMETER_INCHES * 3.1459);

    @Override
    public void init()
    {
        state = 0;

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
        telemetry.update();
    }

    @Override
    public void start()
    {
        state = START;
        opModeStopped = false;
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
                DriveForward(1);
                next_state = TURN_LEFT;
                state = DRIVING_FORWARD;
                break;

            case DRIVING_FORWARD:
                // wait to until we get to position
                // when get to position state = next_state
                break;

            case TURN_LEFT:
                // execute the turn
                // after turn
                state = DRIVING_FORWARD;
                break;

            case DRIVING_FORWARD_2:
                break;

            case DONE:
                // do stop
                break;

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
    }
    public void TurnLeft(double power)
    {
        leftFrontMotor.setPower(-power);
        leftBackMotor.setPower(-power);
        rightFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        telemetry.addData("Say", "Turning Left");
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

        leftFrontMotor.setTargetPosition((int)(10 * COUNTS_PER_INCH));
    }

}

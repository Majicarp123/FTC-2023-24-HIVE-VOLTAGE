package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Drive Slide", group="Exercises")
public class DriveSlide extends LinearOpMode {
    DcMotor leftMotor, rightMotor, linearSlide;
    Servo servoArm;
    double servoPosition = 0;
    double servoDelta = 0.01;


    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftmotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightmotor");
        linearSlide = hardwareMap.get(DcMotor.class, "linearslide");
        servoArm = hardwareMap.get(Servo.class, "servoArm");


        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double slide = gamepad2.left_stick_y;
            double leftPower, rightPower;
            double deadzone = 0.1;

            if (gamepad2.right_trigger > 0.5) {
                servoPosition += servoDelta;
                servoPosition = Range.clip(servoPosition, 0, 1);
                servoArm.setPosition(servoPosition);
            } else if (gamepad2.left_trigger > 0.5) {
                servoPosition -= servoDelta;
                servoPosition = Range.clip(servoPosition, 0, 1);
                servoArm.setPosition(servoPosition);
            }

            drive = Range.clip(drive, -1, 1);
            turn = Range.clip(turn, -1, 1);
            slide = Range.clip(slide, -1, 1);

            drive = (Math.abs(drive) > deadzone) ? drive : 0;
            turn = (Math.abs(turn) > deadzone) ? turn : 0;
            slide = (Math.abs(slide) > deadzone) ? slide : 0;

            leftPower = drive + turn;
            rightPower = drive - turn;

            leftPower = Range.clip(leftPower, -1, 1);
            rightPower = Range.clip(rightPower, -1, 1);

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);
            linearSlide.setPower(slide);

            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.addData("Linear Slide Power", slide);
            telemetry.update();

        }
    }
}
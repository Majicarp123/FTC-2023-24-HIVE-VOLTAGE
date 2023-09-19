package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class AutonomousCode2 extends LinearOpMode {
    DcMotor leftMotor, rightMotor, linearSlide;
    Servo servoClaw;
    String color;
    OpenCvCamera camera;

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftmotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightmotor");
        linearSlide = hardwareMap.get(DcMotor.class, "linearslide");
        servoClaw = hardwareMap.get(Servo.class, "servoclaw");

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
        camera.openCameraDevice();
        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        waitForStart();


        telemetry.addData("Color: ", color);
        telemetry.update();
        // rotate 35 degrees to the left
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        int leftTarget = leftMotor.getCurrentPosition() + (1440);
        int rightTarget = rightMotor.getCurrentPosition() + (1440);
        leftMotor.setTargetPosition(leftTarget);
        rightMotor.setTargetPosition(rightTarget);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
            telemetry.addData("leftMotor: ", leftMotor.getCurrentPosition());
            telemetry.addData("rightMotor: ", rightMotor.getCurrentPosition());
            telemetry.update();
        }

        // drive forward 4 inches
        /*leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);
        leftTarget = leftMotor.getCurrentPosition() + (int)(4 * 1120 / (4 * Math.PI));
        rightTarget = rightMotor.getCurrentPosition() + (int)(4 * 1120 / (4 * Math.PI));
        leftMotor.setTargetPosition(leftTarget);
        rightMotor.setTargetPosition(rightTarget);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
            telemetry.addData("leftMotor: ", leftMotor.getCurrentPosition());
            telemetry.addData("rightMotor: ", rightMotor.getCurrentPosition());
            telemetry.update();
        }

        // raise linear slide 2 inches
        linearSlide.setPower(0.5);
        int linearTarget =
                linearTarget = linearSlide.getCurrentPosition() + (int)(2 * 1120 / (4 * Math.PI));
        linearSlide.setTargetPosition(linearTarget);
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (linearSlide.isBusy() && opModeIsActive()) {
            telemetry.addData("linearSlide: ", linearSlide.getCurrentPosition());
            telemetry.update();
        }
        linearSlide.setPower(0);

        // close servo claw
        servoClaw.setPosition(0);

        // lower linear slide 4 inches
        linearSlide.setPower(-0.5);
        linearTarget = linearSlide.getCurrentPosition() - (int)(4 * 1120 / (4 * Math.PI));
        linearSlide.setTargetPosition(linearTarget);
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (linearSlide.isBusy() && opModeIsActive()) {
            telemetry.addData("linearSlide: ", linearSlide.getCurrentPosition());
            telemetry.update();
        }
        linearSlide.setPower(0);

        // rotate 45 degrees to the left
        leftMotor.setPower(0.5);
        rightMotor.setPower(-0.5);
        leftTarget = leftMotor.getCurrentPosition() + (int)(45 * 1120 / (4 * Math.PI));
        rightTarget = rightMotor.getCurrentPosition() + (int)(45 * 1120 / (4 * Math.PI));
        leftMotor.setTargetPosition(leftTarget);
        rightMotor.setTargetPosition(rightTarget);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (leftMotor.isBusy() && rightMotor.isBusy() && opModeIsActive()) {
            telemetry.addData("leftMotor: ", leftMotor.getCurrentPosition());
            telemetry.addData("rightMotor: ", rightMotor.getCurrentPosition());
            telemetry.update();
        }

        // park based on color
        if (color.equals("green")) {
            leftMotor.setPower(0.5);
            rightMotor.setPower(0);
            leftTarget = leftMotor.getCurrentPosition() + (int)(2 * 1120 / (4 * Math.PI));
            leftMotor.setTargetPosition(leftTarget);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (leftMotor.isBusy() && opModeIsActive()) {
                telemetry.addData("leftMotor: ", leftMotor.getCurrentPosition());
                telemetry.update();
            }
        } else if (color.equals("black")) {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        } else if (color.equals("purple")) {
            rightMotor.setPower(0.5);
            leftMotor.setPower(0);
        }
        */}}
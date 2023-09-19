package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "Signal Sleeve Test")
public class AtonTestV2 extends LinearOpMode {

    private SleeveDetection sleeveDetection;
    private OpenCvCamera camera;
    DcMotor leftMotor, rightMotor, linearSlide;
    Servo servoArm;
    String color;

    // Name of the Webcam to be set in the config
    private String webcamName = "Webcam 1";

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftmotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightmotor");
        linearSlide = hardwareMap.get(DcMotor.class, "linearslide");
        servoArm = hardwareMap.get(Servo.class, "servoArm");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);
        sleeveDetection = new SleeveDetection();
        camera.setPipeline(sleeveDetection);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode) {
            }
        });



        SleeveDetection.ParkingPosition position = null;

        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("ROTATION: ", sleeveDetection.getPosition());
            telemetry.update();
        }
        waitForStart();


            telemetry.addData("ROTATION: ", sleeveDetection.getPosition());
            telemetry.update();


            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            if (sleeveDetection.getPosition() == SleeveDetection.ParkingPosition.LEFT) {
                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);
                int leftTarget = leftMotor.getCurrentPosition() + (3037);
                int rightTarget = rightMotor.getCurrentPosition() + (-3037);
                leftMotor.setTargetPosition(leftTarget);
                rightMotor.setTargetPosition(rightTarget);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while (leftMotor.isBusy() || rightMotor.isBusy()) {
                }


            } else if (sleeveDetection.getPosition() == SleeveDetection.ParkingPosition.CENTER) {
                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);
                int leftTarget = leftMotor.getCurrentPosition() + (-3351);
                int rightTarget = rightMotor.getCurrentPosition() + (3351);
                leftMotor.setTargetPosition(leftTarget);
                rightMotor.setTargetPosition(rightTarget);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while (leftMotor.isBusy() || rightMotor.isBusy()) {
                }



            }

            else {
                leftMotor.setPower(0.5);
                rightMotor.setPower(-0.5);
                int leftTarget = leftMotor.getCurrentPosition() + (3037);
                int rightTarget = rightMotor.getCurrentPosition() + (3037);
                leftMotor.setTargetPosition(leftTarget);
                rightMotor.setTargetPosition(rightTarget);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while (leftMotor.isBusy() || rightMotor.isBusy()) {
                }
            }
        }

        }


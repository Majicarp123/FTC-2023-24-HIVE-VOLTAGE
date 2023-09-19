
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
public class VisionTest extends LinearOpMode {



    DcMotor leftMotor, rightMotor, linearSlide;
    Servo servoArm;
    String color;
    SleeveDetection sleeveDetection;
    OpenCvCamera camera;


    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftmotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightmotor");
        linearSlide = hardwareMap.get(DcMotor.class, "linearslide");
        servoArm = hardwareMap.get(Servo.class, "servoArm");





        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam1"), cameraMonitorViewId);
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

        waitForStart();

        while (opModeIsActive()) {


            telemetry.addData("ROTATION: ", sleeveDetection.getPosition());
            telemetry.update();


            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



            if (color.equals("CORN"))
            {
                leftMotor.setPower(0.5);
                rightMotor.setPower(-0.5);
                int leftTarget = leftMotor.getCurrentPosition() + (3037);
                int rightTarget = rightMotor.getCurrentPosition() + (3037);
                leftMotor.setTargetPosition(leftTarget);
                rightMotor.setTargetPosition(rightTarget);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            }

            else if (color.equals("GREEN"))
            {
                leftMotor.setPower(0.5);
                rightMotor.setPower(-0.5);
                int leftTarget = leftMotor.getCurrentPosition() + (3351);
                int rightTarget = rightMotor.getCurrentPosition() + (3351);
                leftMotor.setTargetPosition(leftTarget);
                rightMotor.setTargetPosition(rightTarget);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            else
            {
                leftMotor.setPower(0.5);
                rightMotor.setPower(-0.5);
                int leftTarget = leftMotor.getCurrentPosition() + (3037);
                int rightTarget = rightMotor.getCurrentPosition() + (3037);
                leftMotor.setTargetPosition(leftTarget);
                rightMotor.setTargetPosition(rightTarget);
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }


        }
    }
}
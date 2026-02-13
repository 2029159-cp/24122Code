package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.util.IntakePos;

@TeleOp(name="DriveYay" )
@Configurable

public class DriveYay extends LinearOpMode {
    private Robot r;
    @Override
    public void runOpMode() {
        r = new Robot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            r.d.drive(gamepad1);

            /* Intake */
            if (gamepad1.left_bumper) {
                r.i.setPower(1);
            } else if (gamepad1.left_trigger > 0.2) {
                r.i.setPower(-1);
            }

            /* Intake Position */
            if (gamepad1.dpadLeftWasPressed()) {
                r.i.setPos(IntakePos.INTAKE);
            } else if (gamepad1.dpadRightWasPressed()) {
                r.i.setPos(IntakePos.SHOOT);
            } else if (gamepad1.dpadDownWasPressed()) {
                r.i.setPos(IntakePos.NEUTRAL);
            }

            /* Spinner */
            if (gamepad1.xWasPressed()) {
                r.sp.spinState--;
                r.sp.spinSM();
            } else if (gamepad1.bWasPressed()) {
                r.sp.spinState++;
                r.sp.spinSM();
            }

            /* Shooter */
            if (gamepad1.right_bumper) {
                r.sh.go(telemetry);
            } else {
                r.sh.stop();
            }

            /* LED Strip */
            r.l.updateColor(r.sh.getVisibility());
        }
    }
}
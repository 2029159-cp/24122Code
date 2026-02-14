package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.util.IntakePos;

@TeleOp(name="Drive" )
@Configurable

public class Drive extends LinearOpMode {
    private Robot r;
    private boolean sp;

    @Override
    public void runOpMode() {
        r = new Robot(hardwareMap);

        sp = r.d.selectDriveMode(this);

        waitForStart();

        while (opModeIsActive()) {
            r.d.drive(gamepad1);

            /* Intake */
            if ((gamepad1.left_bumper && sp) || (gamepad2.left_bumper && !sp)) {
                r.i.setPower(1);
            } else if ((gamepad1.left_trigger > 0.2 && sp) || (gamepad2.left_trigger > 0.2 && !sp)) {
                r.i.setPower(-1);
            }

            /* Intake Position */
            if ((gamepad1.dpadLeftWasPressed() && sp) || (gamepad2.dpadLeftWasPressed() && !sp)) {
                r.i.setPos(IntakePos.INTAKE);
            } else if ((gamepad1.dpadRightWasPressed() && sp) || (gamepad2.dpadRightWasPressed() && !sp)) {
                r.i.setPos(IntakePos.SHOOT);
            } else if ((gamepad1.dpadDownWasPressed() && sp) || (gamepad2.dpadDownWasPressed() && !sp)) {
                r.i.setPos(IntakePos.NEUTRAL);
            }

            /* Spinner */
            if ((gamepad1.xWasPressed() && sp) || (gamepad2.xWasPressed() && !sp)) {
                r.sp.spinState--;
                r.sp.spinSM();
            } else if ((gamepad1.bWasPressed() && sp) || (gamepad2.bWasPressed() && !sp)) {
                r.sp.spinState++;
                r.sp.spinSM();
            }

            /* Shooter */
            if ((gamepad1.right_bumper && sp) || (gamepad2.right_bumper && !sp)) {
                r.sh.go(telemetry);
            } else {
                r.sh.stop();
            }

            /* LED Strip */
            r.l.updateColor(r.sh.getVisibility());
        }
    }
}
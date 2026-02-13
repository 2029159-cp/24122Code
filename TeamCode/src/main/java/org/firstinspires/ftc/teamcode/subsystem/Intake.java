package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.IntakePos;

@Configurable
public class Intake {
    private final DcMotorEx intake;
    private final Servo servoL;
    private final Servo servoR;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "in");
        servoL = hardwareMap.get(Servo.class, "inl");
        servoR = hardwareMap.get(Servo.class, "inr");
        setPower(0);
        setPos(IntakePos.NEUTRAL);
    }

    public void setPower(int power) {
        intake.setPower(power);
    }

    public void setPos(IntakePos p) {
        servoL.setPosition(p.pos);
        servoR.setPosition(p.pos);
    }
}

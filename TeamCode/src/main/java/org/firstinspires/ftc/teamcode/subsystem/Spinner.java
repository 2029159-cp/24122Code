package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.SpinPos;

public class Spinner {
    private final Servo s;
    public int spinState = 0;

    Spinner(HardwareMap h) {
        s = h.get(Servo.class, "spin");
    }

    public void setPos(SpinPos p) {
        s.setPosition(p.pos);
    }

    public void spinSM() {
        if (spinState > 2) {
            spinState = 0;
        } else if (spinState < 0) {
            spinState = 2;
        }
        switch (spinState) {
            case 0:
                s.setPosition(SpinPos.P1.pos);
                break;
            case 1:
                s.setPosition(SpinPos.P2.pos);
                break;
            case 2:
                s.setPosition(SpinPos.P3.pos);
                break;
        }
    }
}

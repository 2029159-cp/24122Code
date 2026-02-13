package org.firstinspires.ftc.teamcode.util;

public enum IntakePos {
    INTAKE(0.35F),
    NEUTRAL(0.5F),
    SHOOT(0.576F);

    public final float pos;
    IntakePos(float pos) {
        this.pos = pos;
    }
}


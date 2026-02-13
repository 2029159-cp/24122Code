package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public final Drivetrain d;
    public final Intake i;
    public final Spinner sp;
    public final Shooter sh;
    public final LEDStrip l;

    public Robot(HardwareMap h) {
        d = new Drivetrain(h);
        i = new Intake(h);
        sp = new Spinner(h);
        sh = new Shooter(h);
        l = new LEDStrip(h);
    }
}

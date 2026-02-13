package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Prism.Color;
import org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver;
import org.firstinspires.ftc.teamcode.Prism.PrismAnimations;

public class LEDStrip {
    private final GoBildaPrismDriver l;
    private final PrismAnimations.Solid blue = new PrismAnimations.Solid(Color.BLUE);
    private final PrismAnimations.Solid magenta = new PrismAnimations.Solid(Color.MAGENTA);
    private PrismAnimations.Solid ledColor = blue;

    LEDStrip(HardwareMap h) {
        l = h.get(GoBildaPrismDriver.class, "led_strip");
    }

    public void updateColor(boolean isVisible) {
        if (isVisible) {
            if (ledColor == magenta) return;
            l.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, new PrismAnimations.Solid(Color.MAGENTA));
            ledColor = magenta;
        } else {
            if (ledColor == blue) return;
            l.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, new PrismAnimations.Solid(Color.BLUE));
            ledColor = blue;
        }

    }
}

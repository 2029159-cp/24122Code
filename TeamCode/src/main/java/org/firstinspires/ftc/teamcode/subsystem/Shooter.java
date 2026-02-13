package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Configurable
public class Shooter {
    public static int p = 200;
    public static int i = 0;
    public static int d = 15;
    public static int f = 0;
    public static boolean dynamicVel = true;
    public static int vel = 1350;
    private final Limelight3A limelight;
    private boolean isVisible = false;
    private final DcMotorEx f1;
    private final DcMotorEx f2;

    public Shooter(HardwareMap h) {
        limelight = h.get(Limelight3A.class, "limelight");
        f1 = h.get(DcMotorEx.class, "fOne");
        f2 = h.get(DcMotorEx.class, "fTwo");
        f1.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void go(Telemetry telemetry) {
        isVisible = updateVel(telemetry);
        f1.setVelocityPIDFCoefficients(p, i, d, f);
        f2.setVelocityPIDFCoefficients(p, i, d, f);
        f1.setVelocity(vel);
        f2.setVelocity(vel);
    }

    public void stop() {
        f1.setVelocity(0);
        f2.setVelocity(0);
    }

    public boolean getVisibility() {
        return isVisible;
    }

    private boolean updateVel(Telemetry telemetry) {
        LLStatus status = limelight.getStatus();
//        status.getPipelineIndex(), status.getPipelineType());

        LLResult result = limelight.getLatestResult();
        if (result.isValid()) {
            LLResultTypes.FiducialResult target = result.getFiducialResults().get(0);

            if (target != null) {
                double x = (target.getCameraPoseTargetSpace().getPosition().x / DistanceUnit.mPerInch);
                double z = (target.getCameraPoseTargetSpace().getPosition().z / DistanceUnit.mPerInch);
                double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));

                if (dynamicVel) {
                    vel = (int) (7.18563 * dist + 810.62874);
                }
                return true;
            }
        }

        telemetry.addData("Limelight", "No data available");
        return false;
    }
}

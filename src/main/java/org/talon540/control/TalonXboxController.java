package org.talon540.control;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class TalonXboxController extends XboxController {
    public double deadband;

    /**
     * @param port               port on the driverstation
     * @param deadbandPercentage minimum percent required to bypass deadband.
     */
    TalonXboxController(int port, double deadbandPercentage) {
        super(port);
        this.deadband = deadbandPercentage;
    }

    /**
     * Create a joystick with 10% deadband (default)
     * 
     * @param port
     */
    TalonXboxController(int port) {
        this(port, 0.1);
    }

    /**
     * Get the X value from the left joystick and check if it is within the deadband provided
     * @return normalized X
     */
    public double getLeftDeadbandX() {
        return checkDeadband(super.getLeftX());
    }

    /**
     * Get the Y value from the left joystick and check if it is within the deadband provided
     * @return normalized Y
     */
    public double getLeftDeadbandY() {
        return checkDeadband(super.getLeftY());
    }

    /**
     * Get the X value from the right joystick and check if it is within the deadband provided
     * @return normalized X
     */
    public double getRightDeadbandX() {
        return checkDeadband(super.getRightX());
    }

    /**
     * Get the Y value from the right joystick and check if it is within the deadband provided
     * @return normalized Y
     */
    public double getRightDeadbandY() {
        return checkDeadband(super.getRightY());
    }

    /**
     * Return 0 if the reported value is within the deadband
     * @param val current val within domain [-1, 1]
     * @return Checked deadband value
     */
    private double checkDeadband(double val) {
        if (-this.deadband < val && val < this.deadband) {
            return 0;
        }

        return MathUtil.clamp(val, -1, 1);
    }

}
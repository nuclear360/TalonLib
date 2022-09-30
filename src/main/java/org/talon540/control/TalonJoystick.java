package org.talon540.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.math.MathUtil;


public class TalonJoystick extends Joystick {
    private double deadband;

    /**
     * @param port port on the driverstation
     * @param deadbandPercentage minimum percent required to bypass deadband.
     */
    TalonJoystick(int port, double deadbandPercentage) {
        super(port);
        this.deadband = deadbandPercentage;
    }

    /**
     * Create a joystick with 20% deadband (default)
     * @param port
     */
    TalonJoystick(int port) {
        this(port, 0.2);
    }

    /**
     * Get the X value from the joysticks and check if it is within the deadband provided
     * @return normalized X
     */
    public double getDeadbandX() {
        return checkDeadband(super.getX());
    }

    /**
     * Get the X value from the joysticks and check if it is within the deadband provided
     * @return normalized Y
     */
    public double getDeadbandY() {
        return checkDeadband(super.getY());
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

        return MathUtil.clamp(-1, val, 1);
    }

}
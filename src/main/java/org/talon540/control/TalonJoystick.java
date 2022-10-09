package org.talon540.control;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Extends the normal WPI Joystick class with methods for calculating deadband
 * angles and normalizing the curve generated by changes in inputs
 */
public class TalonJoystick extends Joystick {
    public double deadband;

    /**
     * @param port               port on the driverstation
     * @param deadbandPercentage minimum percent required to bypass deadband.
     */
    public TalonJoystick(int port, double deadbandPercentage) {
        super(port);
        this.deadband = deadbandPercentage;
    }

    /**
     * Create a joystick with 20% deadband (default)
     * 
     * @param port
     */
    public TalonJoystick(int port) {
        this(port, 0.2);
    }

    /**
     * Get the X value from the joysticks and check if it is within the deadband
     * provided
     * 
     * @return normalized X
     */
    public double getDeadbandX() {
        return checkDeadband(super.getX());
    }

    /**
     * Get the Y value from the joysticks and check if it is within the deadband
     * provided
     * 
     * @return normalized Y
     */
    public double getDeadbandY() {
        return checkDeadband(super.getY());
    }

    /**
     * Return 0 if the reported value is within the deadband
     * 
     * @param val current val within domain [-1, 1]
     * @return Checked deadband value
     */
    private double checkDeadband(double val) {
        if (Math.abs(val) > deadband) {
            if (val > 0.0) {
                return (val - deadband) / (1.0 - deadband);
            } else {
                return (val + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

}

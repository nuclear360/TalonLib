package org.talon540.vision.balltracking;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BalltrackingVision extends SubsystemBase {
    private String tableName = "balltracking";

    public double tx = 0;
    public double ty = 0;
    public double ta = 0;

    public BalltrackingVision() {
        setName(this.tableName);

        NetworkTableInstance.getDefault().getTable(this.tableName).getEntry("robotready").setBoolean(true);

        NetworkTableInstance.getDefault().getTable(this.tableName).getEntry("tx").addListener(event -> {
            this.tx = event.value.getDouble();
        }, EntryListenerFlags.kUpdate);

        NetworkTableInstance.getDefault().getTable(this.tableName).getEntry("ty").addListener(event -> {
            this.ty = event.value.getDouble();
        }, EntryListenerFlags.kUpdate);

        NetworkTableInstance.getDefault().getTable(this.tableName).getEntry("ta").addListener(event -> {
            this.ta = event.value.getDouble();
        }, EntryListenerFlags.kUpdate);
    }

    /**
     * Set ball tracking data reporting state
     * 
     * @param state state to set
     */
    public void setState(BalltrackingStates state) {
        NetworkTableEntry stateEntry = NetworkTableInstance.getDefault().getTable(this.tableName).getEntry("state");

        switch (state) {
            case OFF:
                stateEntry.setNumber(0);
                break;
            case POLLING:
                stateEntry.setNumber(1);
                break;
            case STANDBY:
                stateEntry.setNumber(2);
                break;
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        
    }
}

package org.talon540.trajectory.swerve;

import java.util.List;

public abstract class TalonTrajectory {
    public List<TrajectoryNode> trajectoryList;

    /** Override this value if you don't want to use the default value */
    public double kMaxTranslationalVelocity;
    /** Override this value if you don't want to use the default value */
    public double kMaxAccelerationSquared;
    /** Override this value if you don't want to use the default value */
    public double kMaxRotationalVelocity;

    public void addPointsToTrajectory(double[][] points) {
        for (int i = 0; i < points.length; i++) {
            addPointToTrajectory(points[i]);
        }
    }

    public void addPointToTrajectory(double[] point) {
        this.trajectoryList.add(TrajectoryNode.fromPoint(point));
    }

}

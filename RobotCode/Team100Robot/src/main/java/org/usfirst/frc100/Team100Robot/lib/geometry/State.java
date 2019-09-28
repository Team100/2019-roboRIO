package org.usfirst.frc100.Team100Robot.lib.geometry;

import org.usfirst.frc100.Team100Robot.lib.util.CSVWritable;
import org.usfirst.frc100.Team100Robot.lib.util.Interpolable;

public interface State<S> extends Interpolable<S>, CSVWritable {
    double distance(final S other);

    boolean equals(final Object other);

    String toString();

    String toCSV();
}
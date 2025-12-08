package me.imspooks.adventofcode.util;

import java.util.Objects;

public class Point3d {

    public final double x;
    public final double y;
    public final double z;

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceSquared(Point3d other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point3d point3d)) return false;
        return Double.compare(x, point3d.x) == 0 && Double.compare(y, point3d.y) == 0 && Double.compare(z, point3d.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}

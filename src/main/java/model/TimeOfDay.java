package main.java.model;

import java.util.Objects;

public class TimeOfDay implements Comparable<TimeOfDay> {

    private int hours;
    private int minutes;

    public TimeOfDay(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(TimeOfDay other) {
        if (hours != other.hours) {
            return Integer.compare(hours, other.hours);
        }
        return Integer.compare(minutes, other.minutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeOfDay)) return false;

        TimeOfDay time = (TimeOfDay) o;

        return hours == time.hours &&
                minutes == time.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);
    }
}

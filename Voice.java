public class Voice implements Chargeable {
    private int duration; // in seconds
    private String format;

    // default constructor
    public Voice () {
        duration = 0;
        format = "";
    }

    // non-default constructor
    public Voice (int duration, String format) {
        this.duration = duration;
        this.format = format;
    }

    // returns a string with duration and format
    public String toString () {
        return new String ("\tVOICE: Duration :" + duration + " (sec), Format: " + format);
    }

    // accessors
    public int getDuration() {
        return duration;
    }

    public String getFormat() {
        return format;
    }

    // mutators
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    // two Voice objects are equal if they have the same duration and format
    public boolean equals (Object o) {
        return this.getClass().equals(o.getClass()) &&
                this.duration == ((Voice) o).getDuration() &&
                this.format.equals(((Voice) o).getFormat());
    }

    // charge method for chargeable
    public double charge () {
        return duration*.1;
    }
}

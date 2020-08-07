public class Media implements Chargeable {
    private double size;
    private String format;

    // default constructor
    public Media () {
        size = 0;
        format = "";
    }

    // non-default constructor
    public Media (double size, String format) {
        this.size = size;
        this.format = format;
    }

    // returns a string with the size and format
    public String toString () {
        return new String ("\tMEDIA: Size: " + size + " MB, Format: " + format);
    }

    // accessors
    public double getSize() {
        return size;
    }

    public String getFormat() {
        return format;
    }

    // mutators
    public void setSize(double size) {
        this.size = size;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    // two Media objects are equal if they have the same size and format
    public boolean equals (Object o) {
        return this.getClass().equals(o.getClass()) &&
                this.size == ((Media) o).getSize() &&
                this.format.equals(((Media) o).getFormat());
    }

    // charge method for Chargeable
    public double charge () {
        return size*.5;
    }
}

public abstract class Item {
    // private instance fields
    private int time;
    private String from;
    private String to;
    private double charge;

    // default constructor
    public Item() {
        this.time = 0;
        this.from = "";
        this.to = "";
        this.charge = 0;
    }

    // non-default constructor
    public Item(int time, String from, String to, double charge) {
        this.time = time;
        this.from = from;
        this.to = to;
        this.charge = charge;
    }

    // accessors
    public int getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getCharge() {
        return charge;
    }

    // mutators
    public void setTime(int time) {
        this.time = time;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    // returns a String of all instance fields
    @Override
    public String toString() {
        return String.format("Time: %d, From: %s, To: %s",
                this.time, this.from, this.to);
    }

    // two Item objects are equal if they have the same from, to and time
    @Override
    public boolean equals(Object o) {
            return this.getClass() == o.getClass() &&
                    this.time == ((Item) o).getTime() &&
                    this.to.equals(((Item) o).getTo()) &&
                    this.from.equals(((Item) o).getFrom());
        }
}

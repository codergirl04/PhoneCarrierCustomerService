public class Message<T> extends Item {
    // T can be Text or Voice or Media object
    private T message;

    // default constructor
    public Message() {
        super();
        this.message = null;
    }

    // non-default constructor
    public Message(int time, String from, String to, double charge, T content) {
        super(time, from, to, charge);
        this.message = content;
    }

    // accessor
    public T getMessage() {
        return message;
    }

    // mutator
    public void setMessage(T content) {
        this.message = content;
    }

    // builds the combined String from Item (super class) and content and return it
    @Override
    public String toString() {
        return String.format("%-50s %s", message, super.toString());
    }

    // two Message<?> objects are equal if super.equals returns true and messages are the same
    @Override
    public boolean equals(Object o) {
        return super.equals(o) &&
                o instanceof Message <?> &&
                this.message.equals(((Message<?>) o).getMessage());
    }
}

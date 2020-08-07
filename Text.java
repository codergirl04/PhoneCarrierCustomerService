public class Text implements Chargeable {
    private String content;

    // default constructor
    public Text () {
        content = "";
    }

    // non-default constructor
    public Text (String text) {
        content = text;
    }

    // creates a string with the content of the message
    public String toString () {
        return "\tTEXT: " + content;
    }

    // accessor
    public String getContent() {
        return content;
    }

    // mutator
    public void setContent(String content) {
        this.content = content;
    }

    // two text objects are equal if they have the same content
    @Override
    public boolean equals (Object o) {
        return this.getClass().equals(o.getClass()) &&
                this.content.equals(((Text) o).getContent());
    }

    // charge method for Chargeable
    public double charge () {
        return .2;
    }
}

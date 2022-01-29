package annotation;

public enum TesterName {
    SHYAM("Shyam Sundar");

    public String authorName;
    TesterName(String authorName)
    {
        this.authorName=authorName;
    }

    public String toString() {
        return this.authorName;
    }
}

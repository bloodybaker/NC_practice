package group;

public class Subject {
    private String title;
    private int mark;

    public Subject(String title, int mark) {
        this.title = title;
        this.mark = mark;
    }

    public Subject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", mark=" + mark +
                '}';
    }
}

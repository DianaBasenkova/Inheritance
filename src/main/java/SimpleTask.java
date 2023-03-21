public class SimpleTask extends Task {
    private String title;

    public SimpleTask(int id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
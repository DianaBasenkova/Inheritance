public class Epic extends Task {
    private String subtask[];

    public Epic(int id, String subtask[]) {
        super(id);
        this.subtask = subtask;


    }

    ;

    public String[] getSubtask() {
        return subtask;
    }
}

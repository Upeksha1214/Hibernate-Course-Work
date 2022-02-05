package view.tm;

import javafx.scene.control.Button;


public class CourseModelTwo {
    private String programme;
    private Button remove;

    public CourseModelTwo() {
    }

    public CourseModelTwo(String programme, Button remove) {
        this.programme = programme;
        this.remove = remove;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }
}

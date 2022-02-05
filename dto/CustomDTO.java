package dto;

public class CustomDTO {
    private String id;
    private String programme;

    public CustomDTO() {
    }

    public CustomDTO(String id, String programme) {
        this.id = id;
        this.programme = programme;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

}

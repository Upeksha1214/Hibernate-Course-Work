package dto;

public class CourseDTO {
    private String programmeId;
    private String programme;
    private String duration;
    private String free;

    public CourseDTO() {
    }

    public CourseDTO(String programmeId, String programme, String duration, String free) {
        this.programmeId = programmeId;
        this.programme = programme;
        this.duration = duration;
        this.free = free;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }
}

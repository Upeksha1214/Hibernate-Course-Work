package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @Column(name="programme_Id")
    private String programmeId;
    private String programme;
    private String duration;
    private String free;

    @OneToMany(mappedBy ="course",cascade = CascadeType.ALL)
    private Set<StudentCourses> studentCourses=new HashSet<>();

    public Course() {
    }

    public Course(String programmeId, String programme, String duration, String free) {
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

    @Override
    public String toString() {
        return "Course{" +
                "programmeId='" + programmeId + '\'' +
                ", programme='" + programme + '\'' +
                ", duration='" + duration + '\'' +
                ", free='" + free + '\'' +
                '}';
    }
}

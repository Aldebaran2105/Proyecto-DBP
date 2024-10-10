package com.api.rest.canvas2.Users.domain;

import com.api.rest.canvas2.Announcement.domain.Announcement;
import com.api.rest.canvas2.Assignment.domain.Assignment;
import com.api.rest.canvas2.Course.domain.Course;
import com.api.rest.canvas2.Group.domain.Group;
import com.api.rest.canvas2.Section.domain.Section;
import com.api.rest.canvas2.ZoomMeeting.domain.ZoomMeeting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private Role role;
    private String password;
    private String profilePicture;

    @ManyToMany(mappedBy = "users")
    private List<Section> sections;

    @ManyToMany(mappedBy = "users")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ZoomMeeting> zoomMeetings;

    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    @ManyToMany(mappedBy = "users") // mappedBy indica que la tabla de unión se maneja en Course
    private List<Course> courses;
}

package pinting.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//TODO: Entity Listener 설정
//@EntityListeners(AuditingEntityListener.class)
@Getter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private Long authorId;
    private String authorName;
    private String title;
    private String img;
    private String content;
    private Long viewCount;
    private LocalDateTime hiddenTime = null;

    @OneToMany(mappedBy = "post")
    private List<Tag> tags = new ArrayList<>();

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime createdDate;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime updatedDate;

}

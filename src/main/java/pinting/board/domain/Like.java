package pinting.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
        name = "LIKES",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueMemberAndPost",
                        columnNames = {"POST_ID", "MEMBER_ID"}
                )
        }
)
@NoArgsConstructor
public class Like {
    @Id @GeneratedValue
    @Column(name = "LIKE_ID")
    private Long id;

    private Long memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

}

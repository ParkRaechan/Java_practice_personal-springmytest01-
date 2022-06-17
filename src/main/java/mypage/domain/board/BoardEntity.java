package mypage.domain.board;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="board")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int bno;
    private String bcontent;
    private String whoqwe;
}

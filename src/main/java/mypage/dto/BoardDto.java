package mypage.dto;

import mypage.domain.board.BoardEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private int bno;

    private String bcontent;

    private String whoqwe;

    public BoardEntity toentitiy(){
        return BoardEntity.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .whoqwe(this.whoqwe)
                .build();
    }
}

package mypage.service;

import org.json.JSONArray;
import org.json.JSONObject;
import mypage.domain.board.BoardEntity;
import mypage.domain.board.BoardRepository;
import mypage.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    //번역결과 등록처리
    public boolean registration(String rrs11,String whoqwe){
        BoardDto boardDto = BoardDto.builder().bcontent(rrs11).whoqwe(whoqwe).build();
        BoardEntity boardEntity = boardDto.toentitiy();
        boardRepository.save(boardEntity);
        if(boardEntity.getBno()<1){
            return false;
        }else{
            return true;
        }
    }

    public JSONArray boardlist(){
        JSONArray jsonArray = new JSONArray();
        List<BoardEntity> boardlist = boardRepository.findAll();
        for(BoardEntity boardEntity : boardlist){
            JSONObject object = new JSONObject();
            object.put("bno",boardEntity.getBno()+"");
            object.put("bcontent",boardEntity.getBcontent());
            object.put("whoqwe",boardEntity.getWhoqwe());
            jsonArray.put(object);
        }
        return jsonArray;
    }



}

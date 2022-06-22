package mypage.service;

import org.json.JSONArray;
import org.json.JSONObject;
import mypage.domain.board.BoardEntity;
import mypage.domain.board.BoardRepository;
import mypage.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public JSONObject getboardlist(String key , String keyword , int page ){

        JSONObject object = new JSONObject();

        Page<BoardEntity> boardEntities = null ;

        Pageable pageable = PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC,"bno"));

        //검색[필드를 첨가한]
        if(key.equals("bcontent")){
            boardEntities =boardRepository.findBybcontent(keyword,pageable);
        }else if(key.equals("whoqwe")){
            boardEntities =boardRepository.findBywhoqwe(keyword,pageable);
        }else{
            boardEntities =boardRepository.findBybcontent(keyword,pageable);
        }

////////////////////////////////////////////////////////////

        //페이징
        int btncount= 5;
        int startbtn = (page/btncount) *btncount+1;
        int endbtn = startbtn + btncount -1;

        if(endbtn > boardEntities.getTotalPages()) endbtn = boardEntities.getTotalPages();

/////////////////////////////////////////////////////////////

        JSONArray jsonArray = new JSONArray();
        for(BoardEntity entity : boardEntities ){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bno",entity.getBno());
            jsonObject.put("bcontent",entity.getBcontent());
            jsonObject.put("whoqwe",entity.getWhoqwe());
            jsonArray.put(jsonObject);
        }

/////////////////////////////////////////////////////////////

        object.put( "startbtn" , startbtn );       //  시작 버튼
        object.put( "endhtn" , endbtn );         // 끝 버튼
        object.put( "totalpages" , boardEntities.getTotalPages() );  // 전체 페이지 수
        object.put( "data" , jsonArray );  // 리스트를 추가

/////////////////////////////////////////////////////////////

        return object;

    }



}

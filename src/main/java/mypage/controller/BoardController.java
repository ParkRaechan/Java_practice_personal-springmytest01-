package mypage.controller;

import mypage.dto.BoardDto;
import mypage.service.BoardService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService = new BoardService();

    //번역결괴 등록 매핑
    @PostMapping("/registration")
    @ResponseBody
    public  boolean registration(String rrs11,String whoqwe){
        boolean result = boardService.registration(rrs11,whoqwe);
        return result;
    }
    //board리스트출력
    @GetMapping("/getboardlist")
    public void getboardlist(
            HttpServletResponse response,
            @RequestParam("key") String key ,
            @RequestParam("keyword") String keyword ,
            @RequestParam("page") int page  ){

        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(boardService.getboardlist( key , keyword , page ));
        }catch(Exception e){e.printStackTrace();}
    }

}

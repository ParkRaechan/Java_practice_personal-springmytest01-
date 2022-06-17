package mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detail")
public class TranslateController {
    // 1. 번역 페이지 이동 매핑
    @GetMapping("/translate")
    public String signup(){
        return "detail/translate";
    }

}

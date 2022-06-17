package mypage.controller;

import mypage.dto.MemberDto;
import mypage.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService = new MemberService();

    // 1. 회원가입 페이지 이동 매핑
    @GetMapping("/signup")
    public String signup(){
        return "/member/signup";
    }


   // 2. 회원가입 처리 매핑
    @PostMapping("/signup")
    @ResponseBody
    public boolean  save( MemberDto memberDto ){
        // 서비스 호출
        boolean result =  memberService.signup(memberDto);
        return result;
    }

    // 3. 로그인 페이지 이동 매핑
    @GetMapping("/login")
    public String login( ){
        return "/member/login";
    }

    // 4. 로그인 처리 매핑
    @PostMapping("/login")
    @ResponseBody
    public boolean save(@RequestParam("mid") String mid ,
                        @RequestParam("mpassword") String mpassword ) {
        return memberService.login( mid , mpassword );
    }
    // 5. 로그아웃 처리 매핑
    @GetMapping("/logout")
    public String logout( ) {
        memberService.logout();
        return "redirect:/";
    }
}

package mypage.service;

import mypage.domain.member.MemberEntity;
import mypage.domain.member.MemberRepository;
import mypage.dto.LoginDto;
import mypage.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    HttpServletRequest request;

    // 1. 회원가입처리 메소드
    public boolean signup(  MemberDto memberDto){

        MemberEntity memberEntity = memberDto.toentitiy();

        memberRepository.save( memberEntity );

        if( memberEntity.getMno() < 1 ){
            return false;
        }else{
            return true;
        }
    }


    // 2. 로그인처리 메소드
    public boolean login( String mid , String mpassword ){

        // 1. 모든 엔티티 호출  [ java 조건처리 ]
        List<MemberEntity> memberEntityList =   memberRepository.findAll();
        // 2. 모든 엔티티 리스트에서 입력받은 데이터와 비교한다.
        for( MemberEntity entity : memberEntityList  ){
            // 3. 아이디와 비밀번호가 동일하며
            if( entity.getMid().equals(mid) && entity.getMpasswrd().equals(mpassword) ){

                // 로그인세션에 사용될 dto 생성
                LoginDto logindto = LoginDto.builder()
                        .mno(entity.getMno() )
                        .mid( entity.getMid() )
                        .mname( entity.getMname() )
                        .build();

                // 세션 객체 호출
                request.getSession().setAttribute("login" , logindto.getMname() ); // 세션이름 ,데이터

                return true; // 4. 로그인 성공
            }
        }
        return false; // 5. 로그인 실패
    }

    // 3. 로그아웃 메소드
    public void logout(){
        request.getSession().setAttribute("login",null); // 해당 세션을 null 대입
    }

}

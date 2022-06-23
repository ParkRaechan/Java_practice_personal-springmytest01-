package mypage.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//스프링테스트를 위한 MockMvcRequest 메소드 호출
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void listtest() throws Exception {
        mockMvc.perform(get("/board/getboardlist")
                        .param("key","bcontent")
                        .param("keyword","a")
                        .param("page","0")
        ).andDo(print());
    }

}
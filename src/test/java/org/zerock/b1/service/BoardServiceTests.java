package org.zerock.b1.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b1.dto.BoardDTO;
import org.zerock.b1.dto.PageRequestDTO;
import org.zerock.b1.dto.PageResponseDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;


    @Test
    public void testRegister() {

        log.info("보드 서비스 클래스명 :" + boardService.getClass().getName());

        BoardDTO boardDTO=BoardDTO.builder().title("Sample Title").build();


        Long bno=boardService.register(boardDTO);
        log.info("글 등록 결과 해당 글 번호 " + bno);
    }



    @Test
    public void testModify() {


//변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(102L)
                .title("Updated....102")
                .content("Updated content 102...").writer("user01")
                .build();

        boardService.modify(boardDTO);

    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();



        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }




}

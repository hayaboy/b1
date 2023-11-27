package org.zerock.b1.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b1.domain.BaseEntity;
import org.zerock.b1.domain.Board;
import org.zerock.b1.service.BoardService;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
   private BoardRepository boardRepository;



    @Test
    public void testInsert(){
        Board board=Board.builder().title("title1 Test.....").content("content1 Test.....")
                .writer("user01").build();
        //데이터를 삽입하는 메서드(save)
        Board board2=boardRepository.save(board);  //특정 엔터티를 저장

        log.info(board2);
        log.info(board2.getBno());
        log.info(board2.getTitle());
        log.info(board2.getContent());
        log.info(board2.getWriter());

    }


    //여러개의 글 추가
    @Test
    public void testInsert2(){

       IntStream.rangeClosed(2,100).forEach((i)->{

           Board board= Board.builder().title("title"+i+ "Test.....").content("content"+i+ "Test.....")
                    .writer("user0"+ (i%10)).build();

           boardRepository.save(board);
        });



    }


    //글 번호 가지고 하나의 글 조회

    @Test
    public void testSelectOne(){
//       log.info("글 하나 조회 후 테스트 결과 :" +  boardRepository.findById(101L));

        Optional<Board> optional= boardRepository.findById(101L);
        Board board =optional.orElseThrow();
        log.info("글 하나 조회 결과" + board);



    }


    // 글 수정
    @Test
    public void testUpdate(){

        Long bno = 101L;
        //해당 조회 후 수정
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("Updated 101", "Updated 101 Test");
        Board board2=boardRepository.save(board);
            log.info("수정 결과 " + board2);


    }

    //글 삭제

    @Test
    public void testDelete() {
        Long bno = 101L;

        boardRepository.deleteById(bno);
    }


    @Test
    void testPaging(){

        // Page  포함된 전체 목록에서 해당 항목의 위치에 대한 정보를 얻을 수 있습니다.

        // Pageable  Abstract interface for pagination information.

        // PageRequest  implementation of Pageable.

        Pageable pageable=  PageRequest.of(0,10, Sort.by("bno").ascending());

        log.info("페이징 정보 : " + pageable);


        Page page=boardRepository.findAll(pageable);
        log.info("결과 " + page);
        log.info("내용"  + page.getContent());

    }



    @Test
    public void testSearch1() {

        //2 page order by bno desc
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());

        boardRepository.search1(pageable);

    }
//
    @Test
    public void testSearchAll() {

        String[] types = {"t","c","w"};

        String keyword = "1";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable );

    }




}

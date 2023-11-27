package org.zerock.b1.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b1.domain.Board;
import org.zerock.b1.domain.QBoard;

import java.util.List;


//QuerydslRepositorySupport : Querydsl 라이브러리를 사용하여 리포지토리를 구현하기 위한 기본 클래스
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch  {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {

        // QueryDSL의 목적은 타입(클래스, QBoard) 기반으로 코드를 이용해서 JPQL 쿼리를 생성하고 실행하기 위한 코드

        QBoard board = QBoard.board;


        //JPQLQuery  Query interface for JPQL queries

        JPQLQuery<Board> query  =from(board); // from board

        query.where(board.title.contains("11")); //where title like '%1%';


        this.getQuerydsl().applyPagination(pageable,query);
        List<Board> list =query.fetch();
        long count=query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }
}

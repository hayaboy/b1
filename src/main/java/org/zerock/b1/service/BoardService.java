package org.zerock.b1.service;

import org.zerock.b1.dto.BoardDTO;
import org.zerock.b1.dto.PageRequestDTO;
import org.zerock.b1.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);


    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}

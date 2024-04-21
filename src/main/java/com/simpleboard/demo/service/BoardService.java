package com.simpleboard.demo.service;

import com.simpleboard.demo.dto.BoardDTO;
import com.simpleboard.demo.entity.BoardEntity;
import com.simpleboard.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 서비스 클래스에서는 DTO -> Entity 로 변환(Entity클래스) 또는 Entity -> DTO (DTO클래스) 로 변환 하는 과정이 이루어짐.

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}

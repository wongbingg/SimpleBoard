package com.simpleboard.demo.service;

import com.simpleboard.demo.dto.BoardDTO;
import com.simpleboard.demo.entity.BoardEntity;
import com.simpleboard.demo.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 서비스 클래스에서는 DTO -> Entity 로 변환(Entity클래스) 또는 Entity -> DTO (DTO클래스) 로 변환 하는 과정이 이루어짐.

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    //별도로 추가된 커스텀쿼리를 수행할 경우 아래 @Transactional 어노테이션을 명시
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        // jpa 에서는 save 메서드로 update 가 가능하다. 기존의 id값을 사용
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity); // update 수행
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;
        // 한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준 내림차순 정렬
        // page 는 0부터 시작된다.
        Page<BoardEntity> boardEntities = boardRepository.findAll(
                PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id"))
        );
        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        Page<BoardDTO> boardDTOS = boardEntities.map(
                board -> new BoardDTO(
                        board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits(),
                        board.getCreatedTime()
                )
        );

        return boardDTOS;
    }
}

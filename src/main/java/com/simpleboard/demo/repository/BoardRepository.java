package com.simpleboard.demo.repository;

import com.simpleboard.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    // native 쿼리가 아닌 Entity를 기준으로 쿼리를 작성할 수 있도록 해주는 어노테이션 @Query
    // :땡땡 은 아래 @Param 안의 string값과 매칭된다.
    //
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}

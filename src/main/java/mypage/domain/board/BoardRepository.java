package mypage.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity , Integer>{



    //content검색
    //////////////////////////////
    @Query(value="select * from board where bcontent like %:keyword%", nativeQuery = true)
    Page<BoardEntity> findBybcontent(@Param("keyword") String keyword , Pageable pageable);
    //////////////////////////////




    //name검색
    //////////////////////////////
    @Query(value="select * from board where whoqwe like %:keyword%", nativeQuery = true)
    Page<BoardEntity> findBywhoqwe(@Param("keyword") String keyword , Pageable pageable);
    //////////////////////////////
}

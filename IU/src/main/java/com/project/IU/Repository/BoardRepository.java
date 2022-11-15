package com.project.IU.Repository;

import com.project.IU.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long>
{
    @Transactional
    @Modifying
    @Query(value = "update BOARD2 set BOREADCNT = BOREADCNT +1 where BOARD2.BONO=:BONO", nativeQuery = true )
    int updateReadcnt(@Param("BONO") Long BONO);

    List<BoardEntity> findByBOTITLEContaining(String svalue);

}

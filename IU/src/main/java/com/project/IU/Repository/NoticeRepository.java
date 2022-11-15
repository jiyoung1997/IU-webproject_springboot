package com.project.IU.Repository;


import com.project.IU.Entity.BoardEntity;
import com.project.IU.Entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
    @Transactional
    @Modifying
    @Query(value = "update NOTICE2 set NTREADCNT = NTREADCNT +1 where NOTICE2.NTNO=:NTNO", nativeQuery = true )
    int updateReadcnt(@Param("NTNO") Long NTNO);

    List<NoticeEntity> findByNTTITLEContaining(String svalue);

}

package com.project.IU.Repository;

import com.project.IU.Entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "update photo2 set ptreadcnt = ptreadcnt +1 where photo2.ptno = :ptno", nativeQuery = true )
    int readcntup(@Param("ptno") Long ptno);

    List<PhotoEntity> findByPTTITLEContaining(String svalue);



}

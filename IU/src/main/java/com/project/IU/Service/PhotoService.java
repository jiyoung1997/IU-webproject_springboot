package com.project.IU.Service;

import com.project.IU.Entity.PhotoEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


public interface PhotoService {
    Page<PhotoEntity> list(int page);

    ArrayList<PhotoEntity> ptout();




    PhotoEntity ptinputdata(PhotoEntity boardEntity);

    int readcntup(long ptno);

    PhotoEntity ptdetail(long ptno);




    List<PhotoEntity> search(String svalue);

    void updatesave(PhotoEntity photoEntity);

    PhotoEntity modify(long ptno);

    void delete(long ptno);



}

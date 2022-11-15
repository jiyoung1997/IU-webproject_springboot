package com.project.IU.Service;

import com.project.IU.Entity.PhotoEntity;
import com.project.IU.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoIMPl implements PhotoService{
    @Autowired
    PhotoRepository photoRepository;

    @Override
    public Page<PhotoEntity> list(int page) {
        return photoRepository.findAll(PageRequest.of(page,8, Sort.by(Sort.Direction.ASC,"PTNO")));
    }

    @Override
    public ArrayList<PhotoEntity> ptout() {
        return (ArrayList<PhotoEntity>) photoRepository.findAll();

    }



    @Override
    public PhotoEntity ptinputdata(PhotoEntity photoEntity) {
        return photoRepository.save(photoEntity);
    }

    @Override
    public int readcntup(long ptno) {
        return photoRepository.readcntup(ptno);
    }

    @Override
    public PhotoEntity ptdetail(long ptno) {
        return photoRepository.findById(ptno).orElse(null);
    }



    @Override
    public List<PhotoEntity> search(String svalue) {
        List<PhotoEntity> list = photoRepository.findByPTTITLEContaining(svalue);
        return list;
    }



    @Override
    public PhotoEntity modify(long PTNO) {
        return photoRepository.findById(PTNO).orElse(null);
    }

    @Override
    public void delete(long ptno) {
        photoRepository.deleteById(ptno);

    }


    @Override
    public void updatesave(PhotoEntity photoEntity) {
        photoRepository.save(photoEntity);
    }



}

package com.project.IU.Service;

import com.project.IU.Entity.NoticeEntity;
import com.project.IU.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeIMPl implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public NoticeEntity inputsave(NoticeEntity noticeEntity) {
        return noticeRepository.save(noticeEntity);
    }

    @Override
    public ArrayList<NoticeEntity> out() {
        return (ArrayList<NoticeEntity>) noticeRepository.findAll();
    }

    @Override
    public Page<NoticeEntity> list(int page) {
        return noticeRepository.findAll(PageRequest.of(page,8,Sort.by(Sort.Direction.ASC,"NTNO")));
    }

    @Override
    public int updateReadcnt(long NTNO)
    {
        return noticeRepository.updateReadcnt(NTNO);
    }

    @Override
    public NoticeEntity detail(long NTNO)
    {return noticeRepository.findById(NTNO).orElse(null);}

    @Override
    public NoticeEntity modify(long NTNO)
    {return noticeRepository.findById(NTNO).orElse(null);}

    @Override
    public void update(NoticeEntity ne)
    {
        NoticeEntity nn = noticeRepository.findById(ne.getNTNO()).orElse(null);
        if(nn!=null)
        {noticeRepository.save(ne);}
    }


    @Override
    public void delete(long NTNO)
    {noticeRepository.deleteById(NTNO);}

    @Override
    public List<NoticeEntity> search(String svalue)
    {
        List<NoticeEntity> list = noticeRepository.findByNTTITLEContaining(svalue);
        return list;
    }


}

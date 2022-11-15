package com.project.IU.Service;

import com.project.IU.Entity.NoticeEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface NoticeService {
    NoticeEntity inputsave(NoticeEntity noticeEntity);

    ArrayList<NoticeEntity> out();

    Page<NoticeEntity> list(int page);


    public int updateReadcnt(long NTNO);

    public NoticeEntity detail(long NTNO);

    public NoticeEntity modify(long NTNO);

    public void update(NoticeEntity ne);

    public void delete(long NTNO);

    public List<NoticeEntity> search(String svalue);
}

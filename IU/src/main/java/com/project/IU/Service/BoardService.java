package com.project.IU.Service;

import com.project.IU.Entity.BoardEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface BoardService
{
    public BoardEntity inputsave(BoardEntity boardEntity);

    public Page<BoardEntity> list(int page);

    public ArrayList<BoardEntity> out();

    public BoardEntity detail(long BONO);

    public int updateReadcnt(long BONO);

    public BoardEntity modify(long BONO);

    public void update(BoardEntity boardEntity);

    public void delete(long BONO);

    public List<BoardEntity> search(String svalue);
}

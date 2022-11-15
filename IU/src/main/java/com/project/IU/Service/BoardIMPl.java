package com.project.IU.Service;

import com.project.IU.Entity.BoardEntity;
import com.project.IU.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardIMPl implements BoardService
{
    @Autowired
    BoardRepository boardRepository;

    @Override
    public BoardEntity inputsave(BoardEntity boardEntity)
    {return boardRepository.save(boardEntity);}

    @Override
    public ArrayList<BoardEntity> out()
    {return (ArrayList<BoardEntity>) boardRepository.findAll();}

    @Override
    public Page<BoardEntity> list(int page) {
        return boardRepository.findAll(PageRequest.of(page,8,Sort.by(Sort.Direction.ASC,"BONO")));
    }

    @Override
    public BoardEntity detail(long BONO)
    {
        return boardRepository.findById(BONO).orElse(null);
    }

    @Override
    public int updateReadcnt(long BONO)
    {
        return boardRepository.updateReadcnt(BONO);
    }

    @Override
    public BoardEntity modify(long BONO)
    {return boardRepository.findById(BONO).orElse(null);}

    @Override
    public void update(BoardEntity boardEntity)
    {boardRepository.save(boardEntity);}

    @Override
    public void delete(long BONO)
    {boardRepository.deleteById(BONO);}

    @Override
    public List<BoardEntity> search(String svalue)
    {
        List<BoardEntity> list = boardRepository.findByBOTITLEContaining(svalue);
        return list;
    }

}

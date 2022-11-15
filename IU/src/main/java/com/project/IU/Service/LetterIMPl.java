package com.project.IU.Service;

import com.project.IU.Entity.LetterEntity;
import com.project.IU.Repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LetterIMPl implements LetterService
{
    @Autowired
    LetterRepository letterRepository;

    @Override
    public void input(LetterEntity letterEntity)
    {letterRepository.save(letterEntity);}

    @Override
    public ArrayList<LetterEntity> out()
    {return letterRepository.findAll();}

    @Override
    public LetterEntity detail(long LTNO)
    {return letterRepository.findById(LTNO).orElse(null);}

    @Override
    public void delete(long LTNO)
    {letterRepository.deleteById(LTNO);}


    @Override
    public Page<LetterEntity> list(int page)
    {
        return letterRepository.findAll(PageRequest.of(page,8, Sort.by(Sort.Direction.ASC,"LTNO")));
    }


}

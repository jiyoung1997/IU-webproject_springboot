package com.project.IU.Service;

import com.project.IU.Entity.LetterEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface LetterService
{
    public void input(LetterEntity letterEntity);


    public ArrayList<LetterEntity> out();

    public LetterEntity detail(long LTNO);

    public void delete(long LTNO);

    public Page<LetterEntity> list(int page);
}

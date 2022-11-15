package com.project.IU.Repository;

import com.project.IU.Entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LetterRepository extends JpaRepository<LetterEntity,Long>
{
    @Override
    ArrayList<LetterEntity> findAll();

}

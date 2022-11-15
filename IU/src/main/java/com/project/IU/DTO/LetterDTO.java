package com.project.IU.DTO;

import com.project.IU.Entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LetterDTO
{
    long LTNO;
    String LTTITLE;
    LocalDate LTWRITEDAY;
    String LTCONTENT;
    String LTWRITER;


    public LetterEntity toEntity()
    {
        return new LetterEntity(LTNO,LTTITLE,LTWRITEDAY,LTCONTENT,LTWRITER);
    }
}

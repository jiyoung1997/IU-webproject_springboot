package com.project.IU.DTO;

import com.project.IU.Entity.BoardEntity;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BoardDTO
{
    long BONO;
    String BOTITLE;
    LocalDate BOWRITEDAY;
    String BOCONTENT;
    String BOPICTURE;
    String BOWRITER;
    int BOREADCNT;
    int BOLIKE;
    int RECNT;

    public BoardEntity toEntity()
    {
        return new BoardEntity(BONO,BOTITLE,BOWRITEDAY,BOCONTENT,BOPICTURE,BOWRITER,BOREADCNT,BOLIKE,RECNT);
    }
}

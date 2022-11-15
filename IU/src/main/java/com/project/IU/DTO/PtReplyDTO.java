package com.project.IU.DTO;

import com.project.IU.Entity.PtreplyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PtReplyDTO {


    int PTNO;
    Long PTRNO;
    String PTWRITEDAY;
    String PTCONTENT;
    String PTWRITER;
    int PTGROUPS;
    int PTSTEP;
    int PTINDENT;



    public PtreplyEntity toEntity(){
        return new PtreplyEntity(PTNO,PTRNO,PTWRITEDAY,PTCONTENT,PTWRITER,PTGROUPS,PTSTEP,PTINDENT);
    }

}

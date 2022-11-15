package com.project.IU.DTO;

import com.project.IU.Entity.BoreplyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoreplyDTO {
    long BONO;
    long BORNO;
    String BOWRITEDAY;
    String BOCONTENT;
    String BOWRITER;
    int BOGROUPS;
    int BOSTEP;
    int BOINDENT;

    public BoreplyEntity toEntity(){
        return new BoreplyEntity(BONO,BORNO,BOWRITEDAY,BOCONTENT,BOWRITER,BOGROUPS,BOSTEP,BOINDENT);
    }
}

package com.project.IU.DTO;

import com.project.IU.Entity.NoticeEntity;
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

public class NoticeDTO {
    long NTNO;
    LocalDate NTWRITEDAY = LocalDate.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    String NTTITLE;
    String NTCONTENT;
    String NTWRITER;
    int NTREADCNT;

    public NoticeEntity toEntity(){
        return new NoticeEntity(NTNO,NTWRITEDAY,NTTITLE,NTCONTENT,NTWRITER,NTREADCNT);
    }
}

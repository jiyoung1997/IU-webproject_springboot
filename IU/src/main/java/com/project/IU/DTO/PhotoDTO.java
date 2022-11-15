package com.project.IU.DTO;

import com.project.IU.Entity.PhotoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhotoDTO {
    long PTNO;
    String PTTITLE;
    /*@NotBlank(message = "이름은 필수항목")
    @Size(min = 2,max = 5,message = "이름은 2~5자 이내입력요함!!")*/
    LocalDate PTWRITEDAY;
    String PTCONTENT;
    String PTPICTURE ;
    String PTWRITER;


    int PTREADCNT;
    int PTLIKE;
    int PTRECNT;

    public PhotoEntity toEntity(){
        return new PhotoEntity(PTNO,PTTITLE,PTWRITEDAY,PTCONTENT,PTPICTURE,PTWRITER,PTREADCNT,PTLIKE,PTRECNT);
    }

}

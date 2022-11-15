package com.project.IU.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="photo2")
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "PTNO_SEQ_GENERATOR",sequenceName = "PTNO_SEQ", allocationSize = 1, initialValue = 1)
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PTNO_SEQ_GENERATOR")
    @Column
    long PTNO;
    @Column
    String PTTITLE;
    /*@NotBlank(message = "이름은 필수항목")
    @Size(min = 2,max = 5,message = "이름은 2~5자 이내입력요함!!")*/
    @Column
    LocalDate PTWRITEDAY;
    @Column
    String PTCONTENT;
    @Column
    String PTPICTURE ;
    @Column
    String PTWRITER;


    @Column
    int PTREADCNT;
    @Column
    int PTLIKE;
    @Column
    int PTRECNT;


}

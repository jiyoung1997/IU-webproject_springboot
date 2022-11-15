package com.project.IU.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="NOTICE2")
@SequenceGenerator(
        name="NTNO_seq_Test",
        sequenceName = "NTNO_seq",
        initialValue = 1,
        allocationSize = 1)
public class NoticeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="NTNO_seq_Test")
    long NTNO;
    @Column
    LocalDate NTWRITEDAY;
    @Column
    String NTTITLE;
    @Column
    String NTCONTENT;
    @Column
    String NTWRITER;
    @Column
    int NTREADCNT;
}

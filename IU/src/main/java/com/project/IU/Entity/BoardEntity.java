package com.project.IU.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Table(name="BOARD2")
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name="BONO_SEQ_GENERATOR",sequenceName = "BONO_SEQ",allocationSize = 1, initialValue = 1)

public class BoardEntity
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BONO_SEQ_GENERATOR")
    long BONO;

    @Column
    String BOTITLE;

    @Column
    LocalDate BOWRITEDAY;

    @Column
    String BOCONTENT;

    @Column
    String BOPICTURE;

    @Column
    String BOWRITER;

    @Column
    int BOREADCNT;

    @Column
    int BOLIKE;

    @Column
    int RECNT;
}

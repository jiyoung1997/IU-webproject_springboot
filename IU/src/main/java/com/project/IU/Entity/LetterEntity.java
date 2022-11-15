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

@Table(name="LETTER2")
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name="LTNO_SEQ_GENERATOR",sequenceName = "LTNO_SEQ",allocationSize = 1, initialValue = 1)

public class LetterEntity
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "LTNO_SEQ_GENERATOR")
    long LTNO;

    @Column
    String LTTITLE;

    @Column
    LocalDate LTWRITEDAY;

    @Column
    String LTCONTENT;

    @Column
    String LTWRITER;
}


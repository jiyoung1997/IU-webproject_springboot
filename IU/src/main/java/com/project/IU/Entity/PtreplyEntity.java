package com.project.IU.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;




@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="PTREPLY2")
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name="PTRNO_SEQ_GENERATOR",sequenceName = "PTRNO_SEQ",allocationSize = 1, initialValue = 1)

public class PtreplyEntity extends ArrayList<PtreplyEntity> {
    @Column
    int PTNO;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PTRNO_SEQ_GENERATOR")
    long PTRNO;
    String PTWRITEDAY;
    @Column
    String PTCONTENT;
    @Column
    String PTWRITER;
    @Column
    int PTGROUPS;
    @Column
    int PTSTEP;
    @Column
    int PTINDENT;
}

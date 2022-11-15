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
@Table(name="BOREPLY2")
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name="BORNO_SEQ_GENERATOR",sequenceName = "BORNO_SEQ",allocationSize = 1, initialValue = 1)

public class BoreplyEntity extends ArrayList<BoreplyEntity> {
    @Column
    long BONO;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BORNO_SEQ_GENERATOR")
    long BORNO;
    String BOWRITEDAY;
    @Column
    String BOCONTENT;
    @Column
    String BOWRITER;
    @Column
    int BOGROUPS;
    @Column
    int BOSTEP;
    @Column
    int BOINDENT;
}

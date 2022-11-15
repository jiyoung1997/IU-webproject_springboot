package com.project.IU.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="member2")
@SequenceGenerator(name="MEMNO_SEQ_IU",sequenceName ="MEMNO_SEQ",initialValue = 1,allocationSize = 1)
public class MemberEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MEMNO_SEQ_IU")
    long MEMNO;

    @Column
    String MEMID;
    @Column
    String MEMPW;
    @Column
    String MEMNAME;
    @Column
    String MEMNICKNAME;
    @Column
    String MEMSB;
    @Column
    String MEMBIRTH;
    @Column
    String MEMEMAIL;
    @Column
    String MEMADDRESS;
    @Column
    String MEMTEL;
}

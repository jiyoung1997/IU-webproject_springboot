package com.project.IU.DTO;

import com.project.IU.Entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {

    long MEMNO;

    String MEMID;

    String MEMPW;

    String MEMNAME;

    String MEMNICKNAME;

    String MEMSB;

    String MEMBIRTH;

    String MEMEMAIL;

    String MEMADDRESS;

    String MEMTEL;

    public MemberEntity toEntity() {
        return new MemberEntity(MEMNO, MEMID, MEMPW, MEMNAME, MEMNICKNAME, MEMSB, MEMBIRTH, MEMEMAIL, MEMADDRESS, MEMTEL);
    }
}

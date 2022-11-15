package com.project.IU.Service;

import com.project.IU.DTO.MemberDTO;
import com.project.IU.Entity.MemberEntity;

public interface MemberService {
    void inputsave(MemberEntity memberEntity);

    String detail(String memid);


}

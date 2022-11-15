package com.project.IU.Service;

import com.project.IU.DTO.MemberDTO;
import com.project.IU.Entity.MemberEntity;
import com.project.IU.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberIMPl implements MemberService {

    @Autowired
    MemberRepository serviceRepository;
    @Override
    public void inputsave(MemberEntity memberEntity) {
        serviceRepository.save(memberEntity);
    }

    @Override
    public String detail(String memid) {
        return null;
    }


}

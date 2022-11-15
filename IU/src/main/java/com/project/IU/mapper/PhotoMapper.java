package com.project.IU.mapper;

import com.project.IU.DTO.PtReplyDTO;

import java.util.ArrayList;

public interface PhotoMapper {
    ArrayList<PtReplyDTO> ptreplylist(long ptno);

    void ptreplysave(int ptno, String ptwriter, String ptcontent);

    void recnt(int ptno);

    void ptrereply(int ptno, String ptwriter, String ptcontent, int ptgroups, int ptstep, int ptindent);

    void ptmakereply(int ptgroups, int ptstep);

    void ptredelete(int borno);

    int ptno(int ptrno);

    void PTReUpdateForm(int ptno, int ptrno, String ptwriteday, String ptcontent, String ptwriter, int ptgroups, int ptstep, int ptindent);

    int LikeCheck(int ptno, String memnickname);

    void insertLike(int ptno, String memnickname);

    void updateLike(int ptno);

    void UpdateLikeCancle(int ptno);

    void deleteLike(int ptno, String memnickname);
}

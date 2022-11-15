package com.project.IU.mapper;

import com.project.IU.DTO.BoreplyDTO;

import java.util.ArrayList;

public interface BoardMapper
{
    void boreplysave(long bono, String bowriter, String bocontent);

    ArrayList<BoreplyDTO> boreplylist(long bono);

    void bomakereply(int bogroups, int bostep);

    void borereply(int bono, String rwriter, String rcontent, int bogroups, int bostep, int boindent);

    void recnt(int bono);

    void ReUpdateForm(int bono, int borno, String bowriteday, String bocontent, String bowriter, int bogroups, int bostep, int boindent);

    int bono(int borno);

    void boredelete(int borno);


    //추천 중복방지
    public int LikeCheck(int BONO, String MEMNICKNAME);

    //추천 저장
    public void insertLike(int BONO,String MEMNICKNAME);
    //추천 업데이트 (숫자 증가)
    public void updateLike(int BONO);


    //추천 취소
    public void UpdateLikeCancle(int BONO);
    //추천 삭제
    public void deleteLike(int BONO,String MEMNICKNAME);


}

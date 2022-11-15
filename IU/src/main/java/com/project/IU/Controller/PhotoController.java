package com.project.IU.Controller;


import com.project.IU.DTO.PhotoDTO;
import com.project.IU.DTO.PtReplyDTO;
import com.project.IU.Entity.PhotoEntity;
import com.project.IU.Service.PhotoService;
import com.project.IU.mapper.PhotoMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@SessionAttributes({"loginUser",""})
@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    SqlSession sqlSession;

    /* @GetMapping("main")
     public String main(){
         return "main";
     }*/
    @GetMapping(value="ptinput")
    public String ptinput()
    {
        return "/photo/ptinput";
    }
    @PostMapping("ptsave")
    public String ptsave(@ModelAttribute("PhotoDTO")  @Valid PhotoDTO photoDTO, BindingResult rs  , MultipartHttpServletRequest msr ) throws IOException {

        MultipartFile mf = msr.getFile("PTPICTURE");
        String path =  "C:/SpringBoot/IU-1110/IU/src/main/resources/static/uploadimg/";

        String pic = mf.getOriginalFilename();
        String  uploadpath = path + pic;
        mf.transferTo(new File(uploadpath));

        photoDTO.setPTPICTURE(pic);
        photoDTO.setPTWRITEDAY(LocalDate.now());

        PhotoEntity photoEntity= photoDTO.toEntity();

        photoService.ptinputdata(photoEntity);
        return "redirect:/ptout";
    }
    @GetMapping("ptout")
    public String out(Model mo, @RequestParam(required = false,defaultValue ="0", value = "page") int page){
        Page<PhotoEntity> listPage = photoService.list(page);
        ArrayList<PhotoEntity> list = photoService.ptout();
        int totalPage = listPage.getTotalPages();
        int nowpage = listPage.getPageable().getPageNumber()+1;//현재페이지
        mo.addAttribute("maxPage", 5); //한페이지에 보여질 페이지넘버 수
        mo.addAttribute("pages", listPage);
        mo.addAttribute("nowpage",nowpage);
        mo.addAttribute("list",listPage.getContent());  //출력 데이터들 담은 곳
        return "/Photo/ptout";
    }

    @GetMapping("ptdetail")
    public String detail(@RequestParam("PTNO") long PTNO,Model mo){
        photoService.readcntup(PTNO);
        PhotoEntity list = photoService.ptdetail(PTNO);
        mo.addAttribute("list",list);
        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);
        ArrayList<PtReplyDTO> reply = bm.ptreplylist(PTNO);
        mo.addAttribute("reply",reply);
        return "/Photo/ptdetail";
    }
    @GetMapping("ptdetail1")
    public String detail1(@RequestParam("PTNO") long PTNO,Model mo){

        PhotoEntity list = photoService.ptdetail(PTNO);
        mo.addAttribute("list",list);
        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);
        ArrayList<PtReplyDTO> reply = bm.ptreplylist(PTNO);
        mo.addAttribute("reply",reply);
        return "/Photo/ptdetail1";
    }


    @PostMapping(value = "/ptsearch")
    public String search1(HttpServletRequest request, Model mo) {


        String svalue=request.getParameter("svalue");
        ///ServiceMapper sm = sqlSession.getMapper(ServiceMapper.class);
        List<PhotoEntity> list = photoService.search(svalue);

        mo.addAttribute("list", list);
        return "/Photo/search";


    }


    @GetMapping("/ptmodify")
    public String mofidy(@RequestParam("PTNO") long PTNO,Model model){
        photoService.readcntup(PTNO);
        PhotoEntity photoEntity = photoService.modify(PTNO);
        model.addAttribute("photoEntity",photoEntity);

        return "/Photo/ptmodify";
    }

    @PostMapping("/ptupdate")
    public String updatesave(@ModelAttribute("PhotoDTO")  PhotoDTO photoDTO, BindingResult rs  , MultipartHttpServletRequest msr ) throws IOException {

        MultipartFile mf = msr.getFile("PTPICTURE");
        String path = "C:/SpringBoot/IU/src/main/resources/static/uploadimg/";
        String pic = mf.getOriginalFilename();
        String  uploadpath = path + pic;
        mf.transferTo(new File(uploadpath));
        photoDTO.setPTPICTURE(pic);
        photoDTO.setPTWRITEDAY(LocalDate.now());
        PhotoEntity photoEntity = photoDTO.toEntity();
        photoService.updatesave(photoEntity);
        return "redirect:/ptout";
    }

    @GetMapping("/ptdelete")
    public String ko8(@RequestParam("PTNO") long PTNO,Model model){

        photoService.delete(PTNO);


        return "redirect:/ptout";
    }


    @PostMapping("/ptreply")
    public String ptreply(@RequestParam("PTNO")int PTNO,HttpServletRequest request){
        String PTWRITER=request.getParameter("PTWRITER");
        String PTCONTENT=request.getParameter("PTCONTENT");
        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);
        bm.ptreplysave(PTNO,PTWRITER,PTCONTENT);
        recnt(PTNO);
        return "redirect:/ptdetail1?PTNO="+PTNO;
    }

    public void recnt(int PTNO)
    {
        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);
        bm.recnt(PTNO);
    }

    @PostMapping("/ptrereply")
    public String ptrereply(HttpServletRequest request){
        int PTNO=Integer.parseInt(request.getParameter("PTNO"));
        int PTGROUPS = Integer.parseInt(request.getParameter("PTGROUPS"));
        int PTSTEP = Integer.parseInt(request.getParameter("PTSTEP"));
        int PTINDENT = Integer.parseInt(request.getParameter("PTINDENT"));
        String RWRITER = request.getParameter("RWRITER");
        String RCONTENT = request.getParameter("RCONTENT").replace("\n\r", "<br>");

        MakeReply(PTGROUPS,PTSTEP);
        PTINDENT ++;
        PTSTEP ++;

        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);
        bm.ptrereply(PTNO, RWRITER, RCONTENT, PTGROUPS, PTSTEP, PTINDENT);
        recnt(PTNO);

        return "redirect:/ptdetail1?PTNO="+PTNO;
    }

    public void MakeReply(int PTGROUPS, int PTSTEP)
    {
        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);
        bm.ptmakereply(PTGROUPS, PTSTEP);
    }



    @GetMapping(value="/ptredelete")
    public String replydelete(HttpServletRequest request, PtReplyDTO pr)
    {


        int PTRNO = Integer.parseInt(request.getParameter("PTRNO"));
        PhotoMapper sb = sqlSession.getMapper(PhotoMapper.class);
        pr.setPTNO(sb.ptno(PTRNO));
        pr.getPTNO();
        sb.ptredelete(PTRNO);
        recnt(pr.getPTNO());

        return "redirect:/ptdetail1?PTNO="+pr.getPTNO();
    }



    public void ptno(int PTRNO)
    {
        PhotoMapper sb = sqlSession.getMapper(PhotoMapper.class);
        sb.ptno(PTRNO);
    }



    @PostMapping(value="/PTReUpdateForm")
    public String PTReUpdateForm(HttpServletRequest request,Model mo)
    {


        int PTNO=Integer.parseInt(request.getParameter("PTNO"));
        int PTRNO = Integer.parseInt(request.getParameter("PTRNO"));
        String PTWRITEDAY=request.getParameter("PTWRITEDAY");
        String PTCONTENT=request.getParameter("PTCONTENT");
        String PTWRITER=request.getParameter("PTWRITER");
        int PTGROUPS = Integer.parseInt(request.getParameter("PTGROUPS"));
        int PTSTEP = Integer.parseInt(request.getParameter("PTSTEP"));
        int PTINDENT = Integer.parseInt(request.getParameter("PTINDENT"));
        PhotoMapper sb = sqlSession.getMapper(PhotoMapper.class);

        sb.PTReUpdateForm(PTNO,PTRNO,PTWRITEDAY,PTCONTENT,PTWRITER,PTGROUPS,PTSTEP,PTINDENT);



        return "redirect:ptdetail1?PTNO="+PTNO;
    }




    @ResponseBody
    @GetMapping("/ptupdateLike")
    public int likesave(HttpServletRequest request)
    {
        PhotoMapper bm = sqlSession.getMapper(PhotoMapper.class);

        int PTNO = Integer.parseInt(request.getParameter("PTNO"));
        String MEMNICKNAME = request.getParameter("MEMNICKNAME");

        int LikeCheck = bm.LikeCheck(PTNO,MEMNICKNAME);

        System.out.println("번호 :"+PTNO);
        System.out.println("닉네임 : " +MEMNICKNAME);
        System.out.println("중복방지: "+LikeCheck);

        if(LikeCheck==0)
        {
            bm.insertLike(PTNO,MEMNICKNAME);
            bm.updateLike(PTNO);
        }
        else if(LikeCheck==1)
        {
            bm.UpdateLikeCancle(PTNO);
            bm.deleteLike(PTNO,MEMNICKNAME);
        }
        return LikeCheck;
    }



}

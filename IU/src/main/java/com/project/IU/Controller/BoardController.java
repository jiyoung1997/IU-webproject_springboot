package com.project.IU.Controller;

import com.project.IU.DTO.BoardDTO;
import com.project.IU.DTO.BoreplyDTO;
import com.project.IU.Entity.BoardEntity;
import com.project.IU.Service.BoardService;
import com.project.IU.mapper.BoardMapper;
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
public class BoardController
{
    @Autowired
    BoardService boardService;
    @Autowired
    SqlSession sqlSession;


    @GetMapping("/boinput")
    public String boardinput()
    {
        return "/Board/boinput";
    }

    @PostMapping("/bosave")
    public String boardsave(@ModelAttribute("boardDTO")@Valid BoardDTO boardDTO, BindingResult rs, MultipartHttpServletRequest msr) throws IOException
    {
        MultipartFile mf = msr.getFile("BOPICTURE");
        String path =  "C:/SpringBoot/IU-1110/IU/src/main/resources/static/uploadimg/";
        String pic = mf.getOriginalFilename();
        String uploadpath = path + pic;

        mf.transferTo(new File(uploadpath));
        boardDTO.setBOPICTURE(pic);
        boardDTO.setBOWRITEDAY(LocalDate.now());

        BoardEntity boardEntity = boardDTO.toEntity();
        boardService.inputsave(boardEntity);

        return "redirect:/boout";
    }

    @GetMapping("/boout")
    public String boardout(Model mo, @RequestParam(required = false,defaultValue ="0", value = "page") int page)
    {
        Page<BoardEntity> listPage = boardService.list(page);
        ArrayList<BoardEntity> list = boardService.out();
        int totalPage = listPage.getTotalPages();
        int nowpage = listPage.getPageable().getPageNumber()+1;//현재페이지
        mo.addAttribute("maxPage", 5); //한페이지에 보여질 페이지넘버 수
        mo.addAttribute("pages", listPage);
        mo.addAttribute("list",listPage.getContent());
        return "/Board/boout";
    }

    @GetMapping("/bodetail")
    public String detail1(@RequestParam("BONO") long BONO,Model mo){
        boardService.updateReadcnt(BONO);
        BoardEntity list = boardService.detail(BONO);
        mo.addAttribute("list",list);
        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
        ArrayList<BoreplyDTO> reply = bm.boreplylist(BONO);
        mo.addAttribute("reply",reply);
        return "/Board/bodetail";
    }
    @GetMapping("/bodetail1")
    public String detail2(@RequestParam("BONO") long BONO,Model mo){
        BoardEntity list = boardService.detail(BONO);
        mo.addAttribute("list",list);
        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
        ArrayList<BoreplyDTO> reply = bm.boreplylist(BONO);
        mo.addAttribute("reply",reply);
        return "/Board/bodetail1";
    }

    @GetMapping("/modify")
    public String updateui(@RequestParam("BONO")long BONO,Model mo)
    {
        BoardEntity list = boardService.modify(BONO);
        mo.addAttribute("list",list);

        return "/Board/bomodify";
    }
    @PostMapping("/boupdate")
    public String update(@ModelAttribute("boardDTO")@Valid BoardDTO boardDTO, BindingResult rs, MultipartHttpServletRequest msr) throws IOException
    {
        MultipartFile mf = msr.getFile("BOPICTURE");
        String path = "C:/SpringBoot/IU-1110/IU/src/main/resources/static/uploadimg/";
        String pic = mf.getOriginalFilename();
        String uploadpath = path + pic;

        mf.transferTo(new File(uploadpath));
        boardDTO.setBOPICTURE(pic);
        boardDTO.setBOWRITEDAY(LocalDate.now());

        BoardEntity boardEntity = boardDTO.toEntity();
        boardService.update(boardEntity);

        return "redirect:/boout";
    }

    @GetMapping("/delete")
    public String delete1(@RequestParam("BONO")long BONO)
    {
        boardService.delete(BONO);

        return "redirect:/boout";
    }

    @PostMapping("/search")
    public String search1(@RequestParam("svalue")String svalue,Model mo)
    {
        List<BoardEntity> list = boardService.search(svalue);

        mo.addAttribute("list",list);

        return "/Board/bosearch";
    }
    @PostMapping("/boreply")
    public String boreply(@RequestParam("BONO")int BONO,HttpServletRequest request){
        String BOWRITER=request.getParameter("BOWRITER");
        String BOCONTENT=request.getParameter("BOCONTENT");
        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
        bm.boreplysave(BONO,BOWRITER,BOCONTENT);
        recnt(BONO);
        return "redirect:/bodetail1?BONO="+BONO;
    }

    public void recnt(int BONO)
    {
        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
        bm.recnt(BONO);
    }

    @PostMapping("/borereply")
    public String borereply(HttpServletRequest request){
        int BONO=Integer.parseInt(request.getParameter("BONO"));
        int BOGROUPS = Integer.parseInt(request.getParameter("BOGROUPS"));
        int BOSTEP = Integer.parseInt(request.getParameter("BOSTEP"));
        int BOINDENT = Integer.parseInt(request.getParameter("BOINDENT"));
        String RWRITER = request.getParameter("RWRITER");
        String RCONTENT = request.getParameter("RCONTENT");

        MakeReply(BOGROUPS,BOSTEP);
        BOINDENT ++;
        BOSTEP ++;

        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
        bm.borereply(BONO, RWRITER, RCONTENT, BOGROUPS, BOSTEP, BOINDENT);
        recnt(BONO);

        return "redirect:/bodetail1?BONO="+BONO;
    }

    public void MakeReply(int BOGROUPS, int BOSTEP)
    {
        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);
        bm.bomakereply(BOGROUPS, BOSTEP);
    }
    @GetMapping(value="/boredelete")
    public String replydelete(HttpServletRequest request, BoreplyDTO boreplyDTO)
    {

        int BORNO = Integer.parseInt(request.getParameter("BORNO"));
        BoardMapper sb = sqlSession.getMapper(BoardMapper.class);
        boreplyDTO.setBONO(sb.bono(BORNO));
        sb.boredelete(BORNO);
        recnt((int) boreplyDTO.getBONO());
        return "redirect:/bodetail1?BONO="+boreplyDTO.getBONO();
    }

    @PostMapping(value="/ReUpdateForm")
    public String reupdateform2(HttpServletRequest request,Model mo)
    {


        int BONO=Integer.parseInt(request.getParameter("BONO"));
        int BORNO = Integer.parseInt(request.getParameter("BORNO"));
        String BOWRITEDAY=request.getParameter("BOWRITEDAY");
        String BOCONTENT=request.getParameter("BOCONTENT");
        String BOWRITER=request.getParameter("BOWRITER");
        int BOGROUPS = Integer.parseInt(request.getParameter("BOGROUPS"));
        int BOSTEP = Integer.parseInt(request.getParameter("BOSTEP"));
        int BOINDENT = Integer.parseInt(request.getParameter("BOINDENT"));
        BoardMapper sb = sqlSession.getMapper(BoardMapper.class);

        sb.ReUpdateForm(BONO,BORNO,BOWRITEDAY,BOCONTENT,BOWRITER,BOGROUPS,BOSTEP,BOINDENT);



        return "redirect:bodetail1?BONO="+BONO;
    }

    @ResponseBody
    @GetMapping("/updateLike")
    public int likesave(HttpServletRequest request) {
        BoardMapper bm = sqlSession.getMapper(BoardMapper.class);

        int BONO = Integer.parseInt(request.getParameter("BONO"));
        String MEMNICKNAME = request.getParameter("MEMNICKNAME");

        int LikeCheck = bm.LikeCheck(BONO, MEMNICKNAME);

        System.out.println("번호 :" + BONO);
        System.out.println("닉네임 : " + MEMNICKNAME);
        System.out.println("중복방지: " + LikeCheck);

        if (LikeCheck == 0) {
            bm.insertLike(BONO, MEMNICKNAME);
            bm.updateLike(BONO);
        } else if (LikeCheck == 1) {
            bm.UpdateLikeCancle(BONO);
            bm.deleteLike(BONO, MEMNICKNAME);
        }
        return LikeCheck;
    }

}

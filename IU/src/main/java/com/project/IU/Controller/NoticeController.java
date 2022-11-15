package com.project.IU.Controller;

import com.project.IU.DTO.NoticeDTO;
import com.project.IU.Entity.BoardEntity;
import com.project.IU.Entity.NoticeEntity;
import com.project.IU.Service.NoticeService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"loginUser",""})
@Controller
public class NoticeController {
    @Autowired
    SqlSession sqlSession;
    @Autowired
    NoticeService noticeService;

    @GetMapping("/ntinput")
    public String ntinput(){
        return "/Notice/ntinput";
    }
    @PostMapping("/ntsave")
    public String ntinputsave(@ModelAttribute("noticeDTO")@Valid NoticeDTO noticeDTO){
        NoticeEntity noticeEntity = noticeDTO.toEntity();
        noticeService.inputsave(noticeEntity);
        return "redirect:/ntout";
    }
    @GetMapping("/ntout")
    public String ntout(Model mo, @RequestParam(required = false,defaultValue ="0", value = "page") int page)
    {
        Page<NoticeEntity> listPage = noticeService.list(page);
        ArrayList<NoticeEntity> list = noticeService.out();
        int totalPage = listPage.getTotalPages();
        int nowpage = listPage.getPageable().getPageNumber()+1;//현재페이지
        mo.addAttribute("maxPage", 5); //한페이지에 보여질 페이지넘버 수
        mo.addAttribute("pages", listPage);
        mo.addAttribute("nowpage",nowpage);
        mo.addAttribute("list",listPage.getContent());  //출력 데이터들 담은 곳
        return "/Notice/ntout";
    }

    @GetMapping("/ntdetail")
    public String ntdetail(@RequestParam("NTNO")long NTNO,Model mo)
    {
        noticeService.updateReadcnt(NTNO);

        NoticeEntity list = noticeService.detail(NTNO);
        mo.addAttribute("list",list);

        return "/Notice/ntdetail";
    }

    @GetMapping("/ntmodify")
    public String updateui(@RequestParam("NTNO")long NTNO,Model mo)
    {
        NoticeEntity list = noticeService.modify(NTNO);
        mo.addAttribute("list",list);

        return "/Notice/ntmodify";
    }

    @PostMapping("/ntmodifydata")
    public String update(NoticeDTO dt)
    {
        NoticeEntity ne = dt.toEntity();
        noticeService.update(ne);

        return "redirect:/ntout";
    }

    @GetMapping("/ntdelete")
    public String delete(@RequestParam("NTNO")long NTNO)
    {
        noticeService.delete(NTNO);

        return "redirect:/ntout";
    }

    @PostMapping("/ntsearch")
    public String search1(@RequestParam("svalue")String svalue,Model mo)
    {
        List<NoticeEntity> list = noticeService.search(svalue);
        mo.addAttribute("list",list);

        return "/Notice/ntsearch";
    }

}

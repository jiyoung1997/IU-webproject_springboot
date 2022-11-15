package com.project.IU.Controller;

import com.project.IU.DTO.LetterDTO;
import com.project.IU.Entity.BoardEntity;
import com.project.IU.Entity.LetterEntity;
import com.project.IU.Service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;

@SessionAttributes({"loginUser",""})
@Controller
public class LetterController
{
    @Autowired
    LetterService letterService;

    @GetMapping("/linput")
    public String letterinput()
    {return "/Letter/linput";}

    @PostMapping("/linputsave")
    public String lettersave(LetterDTO dt)
    {
        LetterEntity le = dt.toEntity();
        letterService.input(le);

        return "redirect:lout";
    }

    @GetMapping("/lout")
    public String letterout(Model mo,@RequestParam(required = false,defaultValue ="0", value = "page") int page)
    {
        Page<LetterEntity> listPage = letterService.list(page);
        ArrayList<LetterEntity> list = letterService.out();
        int totalPage = listPage.getTotalPages();
        int nowpage = listPage.getPageable().getPageNumber()+1;//현재페이지
        mo.addAttribute("maxPage", 5); //한페이지에 보여질 페이지넘버 수
        mo.addAttribute("pages", listPage);
        mo.addAttribute("nowpage",nowpage);
        mo.addAttribute("list",listPage.getContent());  //출력 데이터들 담은 곳
        return "/Letter/lout";
    }

    @GetMapping("/ldetail")
    public String ldetail(@RequestParam("LTNO")long LTNO,Model mo)
    {
        LetterEntity list = letterService.detail(LTNO);
        mo.addAttribute("list",list);

        return "/Letter/ldetail";
    }

    @GetMapping("/ldelete")
    public String ldelete(@RequestParam("LTNO")long LTNO)
    {
        letterService.delete(LTNO);

        return "redirect:/lout";
    }

}

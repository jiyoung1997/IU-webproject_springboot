package com.project.IU.Controller;

import com.project.IU.DTO.MemberDTO;
import com.project.IU.Entity.MemberEntity;
import com.project.IU.Service.MemberService;
import com.project.IU.mapper.Membermapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@SessionAttributes({"loginUser",""})
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    SqlSession sqlSession;


    @GetMapping("/main")
    public String signup() {
        return "/Member/main";
    }

    @GetMapping("/input")
    public String signup2(){
        return "/Member/input";
    }

    @PostMapping("/inputsave")
    public String inputsave(MemberDTO memberDTO){
        MemberEntity memberEntity = memberDTO.toEntity();
        memberService.inputsave(memberEntity);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginform(){
        return "/Member/loginform";
    }


    @GetMapping(value = "/home")
    public String indexGET(@SessionAttribute(name = "loginUser", required = false)MemberDTO loginUser, Model model) {

        model.addAttribute("loginUser", loginUser);

        return "redirect:/main";
    }


    @PostMapping("/loginsave")
    public String loginsave(HttpServletRequest httpServletRequest, MemberDTO memberDTO, Model mo, RedirectAttributes ra){

        String memid = httpServletRequest.getParameter("memid");
        String mempw = httpServletRequest.getParameter("mempw");
        String failMessage="아이디 혹은 비밀번호가 틀립니다";
        Membermapper ms =  sqlSession.getMapper(Membermapper.class);
        MemberDTO dto = ms.login(memid,mempw);

        HttpSession hs = httpServletRequest.getSession();
        if(dto ==null)
        {
            ra.addFlashAttribute("loginFail",failMessage);
            return "redirect:/login";

            //hs.setAttribute("dto", dto);
            // System.out.println(dto.getMEMNICKNAME());

            //mo.addAttribute("dto",dto);
            //hs.setAttribute("loginOn", true);


            //hs.setAttribute("dto", dto);
            // return "redirect:/home";

        }
        else
        {
            hs.setAttribute("loginUser",dto);
            //System.out.println(dto.getMEMNICKNAME());
            return "redirect:/home";
            //ra.addAttribute("result","fail");
            //mv.setViewName("redirect:/login");
            //return mv;
        }

    }



    @GetMapping("/logout")
    public String logoutGET(HttpServletRequest request, SessionStatus sessionStatus) {

    /*  HttpSession session = request.getSession();
        session.invalidate();*/

        sessionStatus.setComplete();
        return "redirect:/home";
    }



    @GetMapping(value = "/top")
    public String index(@SessionAttribute(name = "loginUser", required = false)MemberDTO loginUser, Model model) {


        model.addAttribute("loginUser", loginUser);

        return "top";
    }


}

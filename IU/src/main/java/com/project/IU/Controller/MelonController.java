package com.project.IU.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"loginUser",""})
@Controller
public class MelonController {

    @GetMapping(value="/melon")
    public String melon1()
    {
        return "/Melon/Melonmain";
    }

    //조각집
    @GetMapping(value="/jogak")
    public String jogak1()
    {
        return "/Melon/jogakjip";
    }

    //스트로베리문
    @GetMapping(value="/straw")
    public String straw()
    {
        return "/Melon/strawberrymoon";
    }

    //라일락
    @GetMapping(value="/lilac")
    public String lilac()
    {
        return "/Melon/lilac";
    }

    //celebrity
    @GetMapping(value="/cele")
    public String celebr()
    {
        return "/Melon/celebrity";
    }

    //에잇
    @GetMapping(value="/eight")
    public String eight()
    {
        return "/Melon/eight";
    }

    //lovepoem
    @GetMapping(value="/lovepoem")
    public String lovepoem()
    {
        return "/Melon/lovepoem";
    }

    //삐삐
    @GetMapping(value="/bi")
    public String bi()
    {
        return "/Melon/bibi";
    }

    //꽃갈피2
    @GetMapping(value="/flower2")
    public String flower2()
    {
        return "/Melon/flower2";
    }

    //팔레트
    @GetMapping(value="/palette")
    public String palette()
    {
        return "/Melon/palette";
    }

    //밤편지
    @GetMapping(value="/nletter")
    public String nletter()
    {
        return "/Melon/nightletter";
    }

    //체셔
    @GetMapping(value="/chatshire")
    public String chatshire()
    {
        return "/Melon/chat-shire";
    }

    //마음
    @GetMapping(value="/mind")
    public String mind()
    {
        return "/Melon/mind";
    }

    //꽃갈피1
    @GetMapping(value="/flower1")
    public String flower()
    {
        return "/Melon/flower1";
    }

    //모던타임즈2
    @GetMapping(value="/modern2")
    public String modern2()
    {
        return "/Melon/moderntimes2";
    }

    //모던타임즈1
    @GetMapping(value="/modern1")
    public String modern1()
    {
        return "/Melon/moderntimes";
    }

    //스무살의봄
    @GetMapping(value="/20spring")
    public String spring()
    {
        return "/Melon/20spring";
    }

    //라스트 판타지
    @GetMapping(value="/lastfantasy")
    public String fantasy()
    {
        return "/Melon/lastfantasy";
    }

    //real+
    @GetMapping(value="/realplus")
    public String real2()
    {
        return "/Melon/realplus";
    }

    //real
    @GetMapping(value="/real")
    public String real()
    {
        return "/Melon/real";
    }

    //잔소리
    @GetMapping(value="/jan")
    public String janso()
    {
        return "/Melon/jansori";
    }

    //iuim
    @GetMapping(value="/iuim")
    public String iuim()
    {
        return "/Melon/iuim";
    }

    //growing up
    @GetMapping(value="/grup")
    public String grup()
    {
        return "/Melon/growingup";
    }

    //lost and found
    @GetMapping(value="/lost")
    public String lost()
    {
        return "/Melon/lostandfound";
    }


}

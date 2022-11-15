package com.project.IU.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping(value="/aboutiu")
    public String about()
    {
        return "/Member/AboutIU";
    }
}

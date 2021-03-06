package com.majiang.community.controller;

import com.majiang.community.dto.PaginationDTO;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.service.NotificationService;
import com.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "size", defaultValue = "5") Integer size,
                          @RequestParam(value = "page", defaultValue = "1") Integer page){

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDTO);
        } else if ("replies".equals(action)) {
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}

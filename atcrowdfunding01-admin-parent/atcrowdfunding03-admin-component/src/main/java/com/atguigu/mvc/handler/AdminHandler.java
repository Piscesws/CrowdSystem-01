package com.atguigu.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;


@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct , @RequestParam("userPswd") String userPswd ,
                          HttpSession session){

        Admin admin = adminService.getAdminByLoginAcct(loginAcct , userPswd);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){

        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword" , defaultValue = "") String keyword ,
                              @RequestParam(value = "pageNum" , defaultValue = "1") Integer pageNum ,
                              @RequestParam(value = "pageSize" , defaultValue = "5") Integer pageSize ,
                              ModelMap modelMap){
        System.out.println(keyword);
        // 1.调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        // 2.将pageInfo存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO , pageInfo);

        return "admin-page";
    }

    @RequestMapping("/admin/remove/{adminId}.html")
    public String remove(@PathVariable("adminId") Integer adminId){
        // 1.执行删除
        adminService.remove(adminId);
        // 2.跳转到分页页面
        // 尝试方案一 ： 直接转换到admin-page不可以，无法显示分页
        // 尝试方案二 : forward:/admin/get/page.html,刷新页面会再次删除一次，浪费性能。
        // 尝试方案三 ：
        return "redirect:/admin/get/page.html？pageNum=$keyword=";
    }



}

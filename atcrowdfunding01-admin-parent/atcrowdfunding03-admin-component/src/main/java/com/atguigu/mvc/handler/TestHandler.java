package com.atguigu.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.atguigu.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @ResponseBody
    @RequestMapping("/send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){
        for(Integer num:array){
            System.out.println("number = " + num);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTwo(@RequestParam("array") List<Integer> array){
        for(Integer num:array){
            System.out.println("number = " + num);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array){


        for(Integer num:array){
            logger.info("number" + num);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("send/compose/object.json")
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student , HttpServletRequest request){

        boolean judgeRequest = CrowdUtil.judgeRequestType(request);
        logger.info("judgeRequest:" + judgeRequest);

        logger.info(student.toString());
        //封装通用数据结构并返回
        ResultEntity<Student> resultEntity = ResultEntity.successWithData(student);
        String a = null;
        System.out.println(a.length());
        return resultEntity;
    }

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap , HttpServletRequest request){
        boolean judgeRequest = CrowdUtil.judgeRequestType(request);
        logger.info("judgeRequest:" + judgeRequest);
        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList" , adminList);

//        String a = null;
//        System.out.println(a.length());
        System.out.println(10 / 0);
        return "target";
    }
}

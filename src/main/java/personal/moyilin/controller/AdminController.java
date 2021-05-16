package personal.moyilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.moyilin.mapper.AdminMapper;
import personal.moyilin.pojo.Admin;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
//@RestController
public class AdminController {

    @Autowired
    private AdminMapper AdminMapper;

    @RequestMapping("/allAdmin")
    public String allAdmin(Model model){
        List<Admin> admins = AdminMapper.allAdmin();

        model.addAttribute("adminsAll",admins );
        return "admin/manage-admin";
    }

    //    添加管理员页面
    @GetMapping("/toAddAdmin")
    public String toAddAdmin(Model model){
//        List<Admin> adminSort=AdminMapper.();
//        model.addAttribute("addsortadmin",adminSort);
        return "admin/addadmin";
    }
    //    添加管理员请求
    @PostMapping("/toAddAdmin")
    public String AddAdmin(Admin admin,Model model){
        Admin admin2 = AdminMapper.loginByPhone(admin.getManager_phone());
        if (admin2 != null) {
            model.addAttribute("msg", "该手机已经被注册");
            model.addAttribute("line", "/toAddAdmin");
            return "error/add-admin-false";
        } else {
            Admin admin1 = AdminMapper.adminGetEndOne();
            admin.setManager_id(admin1.getManager_id() + 1);
            System.out.println(admin);
            AdminMapper.addAdmin(admin);
            return "redirect:/allAdmin";
        }
    }
    //修改管理员
    @GetMapping("/toUpAdmin/{id}")
    public String toUpAdmin(
            @PathVariable("id")Integer id, Model model){
        Admin admin = AdminMapper.AdminById(id);
        model.addAttribute("oneadmin",admin);
        return "admin/upadmin";
    }
    //    修改管理员请求
    @PostMapping("/upAdmin")
    public String UpAdmin(Admin admin, Model model){
        Admin admin2 = AdminMapper.loginByPhone(admin.getManager_phone());
        if (admin2 != null && admin2.getManager_id()!=admin.getManager_id()) {
            model.addAttribute("msg", "该手机已经被注册");
            String s;
            s = "/toUpAdmin/" + admin.getManager_id();
            model.addAttribute("line", s);
            return "error/add-admin-false";
        } else {
            System.out.println(admin);
            AdminMapper.updateAdmin(admin);
            return "redirect:/allAdmin";
        }
    }
    //删除管理员
    @GetMapping("/toDelAdmin/{id}")
    public String toDelAdmin(
            @PathVariable("id")Integer id){
        AdminMapper.deleteAdmin(id);
        return "redirect:/allAdmin";
    }

    //登录判断
    @RequestMapping("/aLoginRequest")
    public String loginRequest(
            @RequestParam("adminname") String adminname,
            @RequestParam("pw")String pw,
            Model model,
            HttpSession session) {
        if(adminname.isEmpty()){
            model.addAttribute("msg", "请输入管理员账号");
            return "admin/login-admin";
        }else{
            Admin admin=AdminMapper.loginByPhone(adminname);
            if (admin==null) {
                model.addAttribute("msg", "账号或密码错误");
                return "admin/login-admin";
            }else {
                if (admin.getManager_pw().equals(pw)) {
                    int g=Integer.parseInt(admin.getManager_grade());
                    session.setAttribute("adminGrade",admin.getManager_grade());
                    System.out.println(admin.getManager_grade());
                    if(g==1){
                        session.setAttribute("loginAdmin","超级管理员"+admin.getManager_name());
                    }else if(g==2){
                        session.setAttribute("loginAdmin","二级管理员"+admin.getManager_name());
                    }else {
                        session.setAttribute("loginAdmin","普通管理员"+admin.getManager_name());
                    }
        //            System.out.println(Admin.getAdmin_PW()+Admin.getAdmin_name());
                    return "redirect:/adminbook";
                }else {
                    model.addAttribute("msg", "账号或密码错误");
                    return "admin/login-admin";
                }
            }

        }

    }

    //    退出登录
    @RequestMapping("/endAdmin")
    public String endAdmin(Model model,HttpSession session){
        session.setAttribute("loginAdmin",null);
        session.setAttribute("adminGrade",0);
        return "admin/login-admin";
    }



    }

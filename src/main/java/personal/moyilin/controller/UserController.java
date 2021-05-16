package personal.moyilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.moyilin.mapper.CartMapper;
import personal.moyilin.mapper.UserMapper;
import personal.moyilin.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CartMapper cartMapper;

    //登录判断
    @RequestMapping("/loginRequest")
    public String loginRequest(
            @RequestParam("username") String username,
            @RequestParam("pw")String pw,
            Model model,
            HttpSession session) {
        if(username.isEmpty() || pw.isEmpty()){
            model.addAttribute("msg", "请输入账号密码");
            return "user/login";
//            return "redirect:/login.html";
        }else{
        User user=userMapper.loginByPhone(username);
            if (user==null) {
                model.addAttribute("msg", "账号错误");
                return "user/login";
            }else {
                if (user.getUser_pw().equals(pw)) {
                    session.setAttribute("loginUser",user.getUser_name());
                    session.setAttribute("loginId",user.getUser_id());
                    session.setAttribute("cartNumber",cartMapper.cartByUid(user.getUser_id()).size());
                    session.setAttribute("loginimg",user.getUser_image()+"?s="+Math.random()*10000);
                    System.out.println("Loginimg:"+session.getAttribute("loginimg"));

        //            System.out.println(user.getUser_PW()+user.getUser_name());
                    return "redirect:/getPopuBook";
                }else {
                    model.addAttribute("msg", "密码错误");
                    return "user/login";
                }
            }
        }
    }
    //登录判断-邮箱
    @RequestMapping("/loginEmail")
    public String loginEmail(
            @RequestParam("username") String username,
            @RequestParam("pw")String pw,
            Model model,
            HttpSession session) {
        if(username.isEmpty() || pw.isEmpty()){
            model.addAttribute("msg", "请输入账号密码");
            return "user/login-email";
        }else{
            User user=userMapper.loginByEmail(username);
            if (user==null) {
                model.addAttribute("msg", "账号错误");
                return "user/login-email";
            }else {
                if (user.getUser_pw().equals(pw)) {
                    session.setAttribute("loginUser",user.getUser_name());
                    session.setAttribute("loginId",user.getUser_id());
                    session.setAttribute("cartNumber",cartMapper.cartByUid(user.getUser_id()).size());
                    session.setAttribute("loginimg",user.getUser_image());
                    //            System.out.println(user.getUser_PW()+user.getUser_name());
                    return "redirect:/getPopuBook";
                }else {
                    model.addAttribute("msg", "密码错误");
                    return "user/login-email";
                }
            }
        }
    }
    //注册判断
    //    添加用户手机注册请求
    @PostMapping("/toRegPhone")
    public String toRegPhone(User user,HttpSession session,Model model){
        if(user.getUser_name().isEmpty() || user.getUser_sex().isEmpty() || user.getUser_phone().isEmpty() || user.getUser_pw().isEmpty()){
            model.addAttribute("msg", "请完成所有信息填写");
            return "user/register";
        }else {
            User user2 = userMapper.loginByPhone(user.getUser_phone());
            if (user2 != null) {
                model.addAttribute("msg", "该手机号已经被注册");
                return "user/register";
            } else {
                userMapper.regUserByPhone(user);
                User user1 = userMapper.loginByPhone(user.getUser_phone());
                session.setAttribute("loginUser", user1.getUser_name());
                session.setAttribute("loginId", user1.getUser_id());
                session.setAttribute("cartNumber", cartMapper.cartByUid(user1.getUser_id()).size());
                session.setAttribute("loginimg",user.getUser_image());
                //            System.out.println(user.getUser_PW()+user.getUser_name());
                return "redirect:/getPopuBook";
            }
        }
    }
    //    添加用户邮箱注册请求
    @PostMapping("/toRegEmail")
    public String toRegEmail(User user,HttpSession session,Model model){
        if(user.getUser_name().isEmpty() || user.getUser_sex().isEmpty() || user.getUser_email().isEmpty() || user.getUser_pw().isEmpty()){
            model.addAttribute("msg", "请完成所有信息填写");
            return "user/register-email";
        }else {
            System.out.println(user);
            User user2 = userMapper.loginByEmail(user.getUser_email());
            if (user2 != null) {
                model.addAttribute("msg", "该邮箱已经被注册");
                return "user/register-email";
            } else {
                userMapper.regUserByEmail(user);
                User user1 = userMapper.loginByEmail(user.getUser_email());
                session.setAttribute("loginUser", user1.getUser_name());
                session.setAttribute("loginId", user1.getUser_id());
                session.setAttribute("cartNumber", cartMapper.cartByUid(user1.getUser_id()).size());
                session.setAttribute("loginimg",user.getUser_image());
                return "redirect:/getPopuBook";
            }
        }
    }
//    退出登录
    @RequestMapping("/endUser")
    public String endUser(Model model,HttpSession session){
        session.setAttribute("loginUser",null);
        session.setAttribute("loginId",null);
        session.setAttribute("cartNumber",0);
        session.setAttribute("loginimg",null);
        return "redirect:/getPopuBook";
    }
    //更新用户
    @RequestMapping("/toUpUserByUser")
    public String toUpUserByUser(HttpSession session,Model model){
        int uid=(Integer)session.getAttribute("loginId");
//        User user = userMapper.userById(uid);
//        model.addAttribute("oneuserbyuser",user);
//        int uid=2;
        User user = userMapper.userById(uid);
        user.setUser_image(user.getUser_image()+"?"+Math.random()*10000);
        model.addAttribute("oneuserbyuser",user);
        session.setAttribute("imguserpathbyuser",session.getAttribute("loginimg"));
        return "user/information";
    }
    //更新用户请求
    @PostMapping("/toUpUserByUser")
    public String toUpUserByUser(HttpSession session,User user,Model model){
        User user2 = userMapper.loginByPhone(user.getUser_phone());
        if (user2 != null && user2.getUser_id()!=user.getUser_id()) {
            model.addAttribute("msg", "该手机号已经被注册");
            model.addAttribute("line", "/toUpUserByUser");
            return "error/user-false";
        }else {
            User user3 = userMapper.loginByEmail(user.getUser_email());
            if (user3 != null && user3.getUser_id()!=user.getUser_id()) {
                model.addAttribute("msg", "该邮箱已经被注册");
                model.addAttribute("line", "/toUpUserByUser");
                return "error/user-false";
            } else {
                user.setUser_image((String) session.getAttribute("imguserpathbyuser"));
                session.setAttribute("loginimg", session.getAttribute("imguserpathbyuser"));
                System.out.println("user:" + user);
                System.out.println("loginimg:"+session.getAttribute("loginimg"));
                userMapper.updateUser(user);
                int uid = (Integer) session.getAttribute("loginId");
                User user1 = userMapper.userById(uid);
                System.out.println("user1-1:"+user1.getUser_image());
                session.setAttribute("loginimg",user1.getUser_image()+"?s="+Math.random()*10000);
                user1.setUser_image(user1.getUser_image()+"?s="+Math.random()*10000);
                System.out.println("loginimg2:"+session.getAttribute("loginimg"));
                System.out.println("user1-2:"+user1.getUser_image());
                model.addAttribute("oneuserbyuser", user1);

                model.addAttribute("msg", "修改成功");

                return "user/information";
            }
        }
    }


    //    -------------管理员----------------------------------------------------------------
//    查看用户
    @RequestMapping("/adminUser")
    public String allUser(Model model){
        List<User> users = userMapper.allUser();
        model.addAttribute("catchuser",users);
        return "admin/manage-user";
    }
    //    添加用户页面
    @GetMapping("/toAddUser")
    public String toAddUser(Model model){
//        List<User> userSort=userMapper.();
//        model.addAttribute("addsortuser",userSort);
        return "admin/adduser";
    }
    //    添加用户请求
    @PostMapping("/toAddUser")
    public String AddUser(User user, HttpSession session,Model model){
        User user2 = userMapper.loginByPhone(user.getUser_phone());
        if (user2 != null) {
            model.addAttribute("msg", "该手机号已经被注册");
            model.addAttribute("line", "/toAddUser");
            return "error/add-admin-false";
        }else {
            User user3 = userMapper.loginByEmail(user.getUser_email());
            if (user3 != null) {
                model.addAttribute("msg", "该邮箱已经被注册");
                model.addAttribute("line", "/toAddUser");
                return "error/add-admin-false";
            } else {
                User user1 = userMapper.userGetEndOne();
                System.out.println(user1);
                user.setUser_id(user1.getUser_id()+1);
                user.setUser_image((String) session.getAttribute("imguserpath"));
                System.out.println(user);
                userMapper.addUser(user);
                return "redirect:/adminUser";


            }
        }




    }
    //修改用户
    @GetMapping("/toUpUser/{id}")
    public String toUpUser(
            @PathVariable("id")Integer id,Model model, HttpSession session){
        User user = userMapper.userById(id);
        model.addAttribute("oneuser",user);
        session.setAttribute("imguserid",id);
        session.setAttribute("imguserpath",user.getUser_image());
        return "admin/upuser";
    }
    //    修改用户请求
    @PostMapping("/upUser")
    public String UpUser(User user, HttpSession session,Model model){
        User user2 = userMapper.loginByPhone(user.getUser_phone());
        if (user2 != null && user2.getUser_id()!=user.getUser_id()) {
            String s;
            s = "/toUpUser/" + user.getUser_id();
            model.addAttribute("msg", "该手机号已经被注册");
            model.addAttribute("line", s);
            return "error/add-admin-false";
        }else {
            User user3 = userMapper.loginByEmail(user.getUser_email());
            if (user3 != null && user3.getUser_id() != user.getUser_id()) {
                String s;
                s = "/toUpUser/" + user.getUser_id();
                model.addAttribute("msg", "该邮箱已经被注册");
                model.addAttribute("line", s);
                return "error/add-admin-false";
            } else {
                user.setUser_image((String) session.getAttribute("imguserpath"));
                System.out.println("user:" + user);
                userMapper.updateUser(user);
                return "redirect:/adminUser";
            }
        }
    }
    //删除用户
    @GetMapping("/toDelUser/{id}")
    public String toDelUser(
            @PathVariable("id")Integer id){
        userMapper.deleteUser(id);
        return "redirect:/adminUser";
    }
//
//    @GetMapping("/oneUser")
//    public User oneUser(){
//        User users = userMapper.userById(1);
//        System.out.println(users);
//        return users;
//    }

    }

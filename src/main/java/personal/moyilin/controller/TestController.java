package personal.moyilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import personal.moyilin.mapper.BookMapper;
import personal.moyilin.mapper.CartMapper;
import personal.moyilin.mapper.UserMapper;
import personal.moyilin.pojo.Book;
import personal.moyilin.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Controller
public class TestController {
    Scanner s =new Scanner(System.in);




//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private CartMapper cartMapper;
//    @Autowired
//    private BookMapper bookMapper;
//
//    @RequestMapping(value = "/addPhoto",method = RequestMethod.POST)
//    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file, HttpSession session,
//                                          HttpServletRequest request) {
//        Book book1 = bookMapper.bookById(67);
//        Book book=new Book();
//        String str="";
////        String b_id=request.getSession().getAttribute("loginId").toString();
//        int b_id=book1.getBook_id();
//        System.out.println("书籍id："+b_id);
//        String url=request.getRequestURI();
//        System.out.println("URL:"+url);
//        try {
//            if(null!=file){
//                //获得当前项目所在路径
//                String pathRoot=request.getSession().getServletContext().getRealPath("");
//                System.out.println("当前项目所在路径："+pathRoot);
//                //生成uuid作为文件名称
//                String uuid=book1.getBook_name();
//                System.out.println("文件名称："+uuid);
//                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
//                String contentType=file.getContentType();
//                System.out.println("文件类型："+contentType);
//                //获得文件后缀名称
//                String imageName=contentType.substring(contentType.indexOf("/")+1);
//                System.out.println("文件后缀名称："+imageName);
//
////                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/book/";
//                String filePath="src/main/resources/static/assets/img/book/";
//                //根据日期来创建对应的文件夹
//                String datePath=new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
//                System.out.println("日期："+datePath);
//                //根据id分类来创建对应的文件夹
//
//                //日期
//                /*String path=filePath+datePath;*/
//                //联赛id
//                String path=filePath;
//
//                //如果不存在，则创建新文件夹
//                File f=new File(path);
//                if(!f.exists()){
//                    f.mkdirs();
//                }
//
//                //新生成的文件名称
//                String fileName=uuid+"."+imageName;
//                System.out.println("新生成的文件名称："+fileName);
//                session.setAttribute("fileName",fileName);
//
//                //图片保存的完整路径
//                String pathName=path+fileName;
//                System.out.println(pathName);
//
//
//                //图片的静态资源路径
//                String staticPath="/assets/img/book/"+fileName;
//
//                System.out.println("静态资源路径："+staticPath);
//
//                //复制操作
//                //将图片从源位置复制到目标位置
//                file.transferTo(new File(pathName));
//
//                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+datePath+fileName + "\"}}";
//                System.out.println(str);
//
////                photo.setStaticPath(staticPath);    //将图片保存的静态资源路径插入数据库
////                photo.setLeagueID(leagueID);        //将所属联赛ID插入数据库
////                photo.setUploadDate(datePath);      //将上传日期插入数据库
////                photo.setDestFile(pathName);        //将完整路径插入数据库
////                photo.setFileName(fileName);        //将最后保存的完整文件名插入数据库
////                photoRepository.save(photo);
//            }
//            else{
//                System.out.println("文件为空");
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            return str;
//        }
//
//    }



    //    测试
//    @RequestMapping("/test")
//    public String test(HttpSession session, Model model) {
//        String s,name="游戏";
//        s="%"+name+"%";
//        System.out.println(s);
////        int uid=(Integer)session.getAttribute("loginId");
//        List<Book> books = bookMapper.searchBook(s);
//        for (Book book : books) {
//            System.out.println(book);
//        }
//        return "test";
//    }
//    @GetMapping("/tests/{name}")
//    public String test(@PathVariable("name")String name) {
//        String s;
//        s="%"+name+"%";
//
//
//
//        return "test";
//    }



//    @RequestMapping(value="/hello", method= RequestMethod.GET)
//    public String index() {
//
//        String sql = "SELECT user_name FROM t_user WHERE user_id = ?";
//
//        // 通过jdbcTemplate查询数据库
//        String name = (String)jdbcTemplate.queryForObject(
//                sql, new Object[] { 1 }, String.class);
//
//        return "Hello " + name;
//    }

}

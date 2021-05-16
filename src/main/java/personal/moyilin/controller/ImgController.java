package personal.moyilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import personal.moyilin.mapper.BookMapper;
import personal.moyilin.mapper.UserMapper;
import personal.moyilin.pojo.Book;
import personal.moyilin.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ImgController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    //*******************书籍*******************************
    @RequestMapping(value = "/addPhoto",method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(@RequestParam("file") MultipartFile file, HttpSession session,
                     HttpServletRequest request) {
        Book book1=bookMapper.bookGetEndOne();
        System.out.println(book1);
        String str="";
//        String b_id=request.getSession().getAttribute("loginId").toString();
        int b_id=book1.getBook_id()+1;
        System.out.println("书籍id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/book/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/book/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imgpath",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
    @RequestMapping(value = "/upPhoto",method = RequestMethod.POST)
    public @ResponseBody
    String upPhoto(@RequestParam("file") MultipartFile file, HttpSession session,
                       HttpServletRequest request) {
        String str="";
        int b_id=Integer.parseInt(session.getAttribute("imgbookid").toString());
        System.out.println("书籍分类id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String format = formatter.format(new Date());
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/book/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/book/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imgbookpath",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
//*******************分类*******************************
    @RequestMapping(value = "/addPhotoSort",method = RequestMethod.POST)
    public @ResponseBody
    String uploadSortImg(@RequestParam("file") MultipartFile file, HttpSession session,
                     HttpServletRequest request) {
        Book book1=bookMapper.bookSortGetEndOne();
        System.out.println(book1);
        String str="";
        int b_id=book1.getSort_id()+1;
        System.out.println("书籍分类id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/book-sort/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/book-sort/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imgsortpath",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
    @RequestMapping(value = "/upPhotoSort",method = RequestMethod.POST)
    public @ResponseBody
    String upPhotoSort(@RequestParam("file") MultipartFile file, HttpSession session,
                         HttpServletRequest request) {
        String str="";
//        String b_id=request.getSession().getAttribute("loginId").toString();
        int b_id=Integer.parseInt(session.getAttribute("imgsortid").toString());
        System.out.println("书籍分类id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                //--------------------------获得当前时间-----------------------------------------
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String format = formatter.format(new Date());
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/book-sort/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/book-sort/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imgsortpath",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
    //*******************管理用户*******************************
    @RequestMapping(value = "/addUserPhoto",method = RequestMethod.POST)
    public @ResponseBody
    String addUserPhoto(@RequestParam("file") MultipartFile file, HttpSession session,
                     HttpServletRequest request) {
        User user = userMapper.userGetEndOne();
        System.out.println(user);
        String str="";
//        String b_id=request.getSession().getAttribute("loginId").toString();
        int b_id=user.getUser_id()+1;
        System.out.println("用户id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/author/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/author/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imguserpath",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
    @RequestMapping(value = "/upUserPhoto",method = RequestMethod.POST)
    public @ResponseBody
    String upUserPhoto(@RequestParam("file") MultipartFile file, HttpSession session,
                   HttpServletRequest request) {
        String str="";
        int b_id=Integer.parseInt(session.getAttribute("imguserid").toString());
        System.out.println("用户id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String format = formatter.format(new Date());
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/author/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/author/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imguserpath",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
    @RequestMapping(value = "/upUserPhotoByUser",method = RequestMethod.POST)
    public @ResponseBody
    String upUserPhotoByUser(@RequestParam("file") MultipartFile file, HttpSession session,
                       HttpServletRequest request) {
        String str="";
        int b_id=Integer.parseInt(session.getAttribute("loginId").toString());
        System.out.println("用户id："+b_id);
        String url=request.getRequestURI();
        System.out.println("URL:"+url);
        try {
            if(null!=file){
                //获得当前项目所在路径
                String pathRoot=request.getSession().getServletContext().getRealPath("");
                System.out.println("当前项目所在路径："+pathRoot);
                SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");//设置日期格式
                String format = formatter.format(new Date());
                //生成uuid作为文件名称
                String uuid=String.valueOf(b_id);
                System.out.println("文件名称："+uuid);
                //获得文件类型（判断如果不是图片文件类型，则禁止上传）
                String contentType=file.getContentType();
                System.out.println("文件类型："+contentType);
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                System.out.println("文件后缀名称："+imageName);
                String filePath="D:/Tools/springboot/IdeaProjects/src/main/resources/static/assets/img/author/";
                String path=filePath;
                //如果不存在，则创建新文件夹
                File f=new File(path);
                if(!f.exists()){
                    f.mkdirs();
                }
                //新生成的文件名称
                String fileName=uuid+"."+imageName;
                System.out.println("新生成的文件名称："+fileName);
                session.setAttribute("fileName",fileName);
                //图片保存的完整路径
                String pathName=path+fileName;
                System.out.println(pathName);
                //图片的静态资源路径
                String staticPath="/assets/img/author/"+fileName;
                System.out.println("静态资源路径："+staticPath);
                session.setAttribute("imguserpathbyuser",staticPath);
                //复制操作
                //将图片从源位置复制到目标位置
                file.transferTo(new File(pathName));
                str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + "pic/"+fileName + "\"}}";
                System.out.println(str);
            }
            else{
                System.out.println("文件为空");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return str;
        }
    }
}

package personal.moyilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.moyilin.mapper.BookMapper;
import personal.moyilin.mapper.CartMapper;
import personal.moyilin.pojo.Book;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
//@RestController
public class BookController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CartMapper cartMapper;

    @RequestMapping("/")
    public String index(Model model) {
        return "redirect:/getPopuBook";
    }

    //    --书籍-----------------------------------------------------------------
//    按照热门排序
    @RequestMapping("/getPopuBook")
    public String getAllBook(Model model) {
        List<Book> books = bookMapper.popuBook();
        List<Book> bookSort = bookMapper.getBookSort();
        model.addAttribute("pbooksAll", books);
        model.addAttribute("pbookSort", bookSort);
        return "blog-category";
    }

    //  按照分类排序
    @RequestMapping("/getSortBook")
    public String getAllBook2(Model model) {
        List<Book> books = bookMapper.sortBook();
        List<Book> bookSort = bookMapper.getBookSort();
        model.addAttribute("sbooksAll", books);
        model.addAttribute("sbookSort", bookSort);
        return "blog-gallery";
    }


    //    单个分类
    @GetMapping("/getBook/{id}")
    public String getSortBook(
            @PathVariable("id") Integer id, Model model) {
        List<Book> books = bookMapper.sortBookOnly(id);
        List<Book> bookSort = bookMapper.getBookSort();
        String s = bookMapper.sortById(id);
        int i = id;
        model.addAttribute("gbooksAll", books);
        model.addAttribute("gbookSort", bookSort);
        model.addAttribute("getname", s);
        model.addAttribute("getid", i);
        return "blog-gallery";
    }
//    获得书籍详情

    @GetMapping("/getOneBook/{id}")
    public String getOneBook(
            @PathVariable("id") Integer id, Model model) {
        Book book = bookMapper.bookById(id);
        String s = bookMapper.sortById(book.getSort_id());
        List<Book> books = bookMapper.bookByAuthor(book.getBook_author());
        int size = books.size();
        model.addAttribute("onebook", book);
        model.addAttribute("onename", s);
        model.addAttribute("oneid", book.getSort_id());
        model.addAttribute("onesize", size);

        return "booktake";
    }

    //    模糊查询
    @PostMapping("/toSearchBook")
    public String toSearchBook(String name, Model model) {
        String s;
        s = "%" + name + "%";
        System.out.println(name);
        List<Book> books = bookMapper.searchBook(s);
        model.addAttribute("searchbook", books);
        model.addAttribute("sname", name);
        return "blog-list";
    }
    @GetMapping("/toSearchBook/{name}")
    public String getSearchBook(@PathVariable("name")String name, Model model) {
        String s;
        s = "%" + name + "%";
        System.out.println(name);
        List<Book> books = bookMapper.searchBook(s);
        model.addAttribute("searchbook", books);
        model.addAttribute("sname", name);
        return "blog-list";
    }


    //    -------------管理员----------------------------------------------------------------
//    查看书籍
    @RequestMapping("/adminbook")
    public String catchAllBook(Model model) {
        List<Book> books = bookMapper.getBook();
        model.addAttribute("catchbook", books);
        return "admin/manage";
    }

    //    添加书籍页面
    @GetMapping("/toAddBook")
    public String toAddBook(Model model) {
        List<Book> bookSort = bookMapper.getBookSort();
        model.addAttribute("addsortbook", bookSort);
        return "admin/addbook";
    }

    //    添加书籍请求
    @PostMapping("/toAddBook")
    public String toAddBook(Book book, HttpSession session, Model model) {
//      上传图片
        Book book1 = bookMapper.bookGetEndOne();
        System.out.println(book1);
        book.setBook_id(book1.getBook_id() + 1);
        book.setBook_image((String) session.getAttribute("imgpath"));
        System.out.println(book);
        bookMapper.addBook(book);
        return "redirect:/adminbook";
    }

    //修改书籍
    @GetMapping("/toUpBook/{id}")
    public String toUpBook(
            @PathVariable("id") Integer id, Model model, HttpSession session) {
        Book book = bookMapper.bookById(id);
        List<Book> bookSort = bookMapper.getBookSort();
        model.addAttribute("addsortbook", bookSort);
        model.addAttribute("onebook", book);
        session.setAttribute("imgbookid",id);
        session.setAttribute("imgbookpath",book.getSort_img());
        return "admin/upbook";
    }

    //    修改书籍请求
    @PostMapping("/upBook")
    public String UpBook(Book book, HttpSession session) {
        book.setBook_image((String)session.getAttribute("imgbookpath"));
        System.out.println("book:"+book);
        bookMapper.updateBook(book);

        return "redirect:/adminbook";
    }

    //删除书籍
    @GetMapping("/toDelBook/{id}")
    public String toDelBook(
            @PathVariable("id") Integer id) {
        bookMapper.deleteBook(id);
        return "redirect:/adminbook";
    }

    //    ******分类管理*****
//    查看书籍分类
    @RequestMapping("/getSort")
    public String getSort(Model model) {
        List<Book> bookSort = bookMapper.getBookSort();
        model.addAttribute("sbookSort", bookSort);
        return "admin/manage-sort";
    }

    //    添加分类页面
    @GetMapping("/toAddBookSort")
    public String toAddBookSort(Model model) {
        return "admin/addbooksort";
    }
    //    添加书籍分类请求
    @PostMapping("/toAddBookSort")
    public String toAddBookSort(Book book, HttpSession session, Model model) {
//      上传图片
        Book sort = bookMapper.bookSortGetEndOne();
        System.out.println(sort);
        book.setSort_id(sort.getSort_id() + 1);
        book.setSort_img((String) session.getAttribute("imgsortpath"));
        System.out.println(book);
        bookMapper.addBookSort(book);
        return "redirect:/getSort";
    }
    //修改书籍分类
    @GetMapping("/toUpBookSort/{id}")
    public String toUpBookSort(
            @PathVariable("id") Integer id, Model model, HttpSession session) {
        Book book = bookMapper.bookSortById(id);
        model.addAttribute("addsortbook", book);
        session.setAttribute("imgsortid",id);
        session.setAttribute("imgsortpath",book.getSort_img());
        return "admin/upsort";
    }

    //    修改书籍分类请求
    @PostMapping("/upBookSort")
    public String UpBookSort(Book book, HttpSession session) {
        book.setSort_img((String)session.getAttribute("imgsortpath"));
        System.out.println("sort:"+book);
        bookMapper.updateBookSort(book);
        return "redirect:/getSort";
    }
    //删除书籍分类
    @GetMapping("/toDelBookSort/{id}")
    public String toDelBookSort(
            @PathVariable("id") Integer id, Model model) {
        List<Book> books = bookMapper.sortBookOnly(id);
        System.out.println(books.size());
        if(books.size()==0){
            bookMapper.deleteBookSort(id);
            return "redirect:/getSort";
        }else {
            model.addAttribute("msg","删除失败，该分类已有书籍正在使用，推荐更改分类名字或请删除该分类下的书籍");
            model.addAttribute("line", "/getSort");
            return "error/add-admin-false";
        }
//        bookMapper.deleteBook(id);

    }
    //    管理员书籍模糊查询
    @PostMapping("/adminToSeachBook")
    public String adminToSeachBook(String name, Model model) {
        String s;
        s = "%" + name + "%";
        List<Book> books = bookMapper.searchBook(s);
        model.addAttribute("catchbook", books);
        model.addAttribute("sname", name);
        return "admin/manage";
    }
    //    管理员书籍模糊查询
    @PostMapping("/adminToSeachBookisbn")
    public String adminToSeachBookisbn(String isbn, Model model) {
        String s;
        s = "%" + isbn + "%";
        List<Book> books = bookMapper.searchBookisbn(s);
        model.addAttribute("catchbook", books);
        model.addAttribute("sisbn", isbn);
        return "admin/manage";
    }


}
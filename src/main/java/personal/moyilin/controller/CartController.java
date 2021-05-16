package personal.moyilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import personal.moyilin.mapper.BookMapper;
import personal.moyilin.mapper.CartMapper;
import personal.moyilin.pojo.Book;
import personal.moyilin.pojo.Cart;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CartMapper cartMapper;
    //     --用户-----------------------------------------------------------------
//    购物车
    @GetMapping("/cartUser/{id}")
    public String test(HttpSession session,@PathVariable("id")int id, Model model) {
        List<Book> book = bookMapper.getBook();
        List<Cart> carts = cartMapper.cartByUid(id);
        int uid=(Integer) session.getAttribute("loginId");
        model.addAttribute("cartuser",carts);
        model.addAttribute("getbook",book);
        session.setAttribute("cartNumber",cartMapper.cartByUid(uid).size());
        return "book-cart";
    }
    //删除一条购物车内容
    @GetMapping("/toDelOneCart/{id}")
    public String toDelOneCart( HttpSession session,
            @PathVariable("id")Integer id, Model model){
        System.out.println("id"+id);
        int uid=(Integer) session.getAttribute("loginId");
        System.out.println(uid);
        cartMapper.deleteCartOne(id,uid);
        List<Book> book = bookMapper.getBook();
        List<Cart> carts = cartMapper.cartByUid(uid);
        model.addAttribute("cartuser",carts);
        model.addAttribute("getbook",book);
        session.setAttribute("cartNumber",cartMapper.cartByUid(uid).size());
        return "book-cart";
    }

//    清空购物车
    @GetMapping("/toDelCart/{id}")
    public String toDelCart(HttpSession session,
            @PathVariable("id")Integer id,Model model){
        cartMapper.deleteCart(id);
        List<Book> book = bookMapper.getBook();
        List<Cart> carts = cartMapper.cartByUid(id);
        model.addAttribute("cartuser",carts);
        model.addAttribute("getbook",book);
        session.setAttribute("cartNumber",cartMapper.cartByUid(id).size());
        return "redirect:/book-cart";
    }
    //    添加书籍请求
    @GetMapping("/toAddCart/{id}")
    public String toAddCart(HttpSession session,@PathVariable int id,Model model){
        int uid=(Integer) session.getAttribute("loginId");
        Cart cart = cartMapper.cartById(id, uid);
        if (cart==null){
            cartMapper.addCart(id,uid);
            model.addAttribute("cartUpdateTip","添加书籍入购物车成功");
        }else {
            cartMapper.updateCartOne(id, uid, cart.getCart_count() + 1);
            System.out.println(cart.getCart_count() + 1);
            model.addAttribute("cartUpdateTip","购物车中该书籍数量加一");
        }
//        获得书籍详情
        Book book = bookMapper.bookById(id);
        String s = bookMapper.sortById(book.getSort_id());
        List<Book> books = bookMapper.bookByAuthor(book.getBook_author());
        int size = books.size();
        model.addAttribute("onebook",book);
        model.addAttribute("onename",s);
        model.addAttribute("oneid",book.getSort_id());
        model.addAttribute("onesize",size);
        session.setAttribute("cartNumber",cartMapper.cartByUid(uid).size());
        return "booktake";
    }




}

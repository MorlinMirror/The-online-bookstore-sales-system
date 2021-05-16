package personal.moyilin.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.moyilin.mapper.BookMapper;
import personal.moyilin.mapper.CartMapper;
import personal.moyilin.mapper.OrderBookMapper;
import personal.moyilin.mapper.OrderMapper;
import personal.moyilin.pojo.Book;
import personal.moyilin.pojo.Cart;
import personal.moyilin.pojo.Order;
import personal.moyilin.pojo.OrderBook;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderBookMapper orderBookMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    BookMapper bookMapper;

    //    修改书籍请求
    @PostMapping("/toOrder")
    public String toOrder(HttpSession session, String cart_count, Model model){
        int uid=(Integer) session.getAttribute("loginId");
//       --------------------------获得全部购物车----------------------------------------
        List<Cart> carts = cartMapper.cartByUid(uid);
//        --------------------------获得最后一个订单-------------------------------------
        Order order1 = orderMapper.orderGetEndOne();
        //******************订单设置初始化**************************************************
        //--------------------------获得当前时间-----------------------------------------
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //--------------------------订单设置------------------------------------------
        Order order= new Order();
        order.setOrder_id(order1.getOrder_id()+1);
        order.setUser_id(uid);
        order.setOrder_time(formatter.format(new Date()));
//       ******************订单书籍初始化***************************************************
        OrderBook orderBook=new OrderBook();
        orderBook.setOrder_id(order.getOrder_id());
//      --------------------------更改数量-------------------------------------
        String[] s=cart_count.split(",");
//        --------------------------自增量-------------------------------------
        int x=0;
//        --------------------------书籍量-------------------------------------
        int y;
//        --------------------------书籍总量-------------------------------------
        int count=0;
//        --------------------------书籍总价格-----------------------------------------
        float sum=0;
        for (Cart cart : carts) {
//          --------------------------单个书籍数量----------------------------------------
            y=Integer.parseInt(s[x]) ;
            cart.setCart_count(y);
            count+=y;
            Book book = bookMapper.bookById(cart.getBook_id());
            orderBook.setBook_id(book.getBook_id());
            orderBook.setCart_count(y);
            orderBook.setCart_price(book.getBook_price()*y);
            sum+=book.getBook_price()*y;
            System.out.println(orderBook);//--------------插入订单书籍----------------------
            orderBookMapper.addOrderBook(orderBook);
            x++;
        }
        order.setOrder_total(count);
        order.setOrder_price(sum);
        System.out.println(order);//----------------------插入订单----------------------
        orderMapper.addOrder(order);
        // ------------------清空购物车-----------------
        cartMapper.deleteCart(uid);
//        ---------------获得当前订单信息--------------
        Order order2 = orderMapper.orderById(order.getOrder_id());
        List<OrderBook> orderBooks = orderBookMapper.orderBookById(order.getOrder_id());
        model.addAttribute("orderNow",order2);
        model.addAttribute("orderBooksNow",orderBooks);
        session.setAttribute("cartNumber",cartMapper.cartByUid(uid).size());


//----------------------跳转订单界面----------------------
        return "order/order";
    }
//    获取全部订单
    @RequestMapping("/toAllOrder")
    public String toAllOrder(HttpSession session, Model model){
        int uid=(Integer) session.getAttribute("loginId");
        List<Order> orders = orderMapper.allOrderById(uid);
        model.addAttribute("orderAll",orders);
        return "order/order-all";
    }
//    获取订单号书籍
    @GetMapping("/toOneOrder/{id}")
    public String toOneOrder(
            @PathVariable("id")Integer id, Model model){
        Order order = orderMapper.orderById(id);
        List<OrderBook> orderBooks = orderBookMapper.orderBookById(id);
        model.addAttribute("orderNow",order);
        model.addAttribute("orderBooksNow",orderBooks);
        return "order/order";
    }
//*******************************管理员******************************
//    获取全部订单
    @RequestMapping("/getAllOrder")
    public String getAllOrder(Model model){
        List<Order> orders = orderMapper.allOrder();
        model.addAttribute("orderAll",orders);
        return "admin/manage-order";
    }
    //    获取订单号书籍
    @GetMapping("/getOneOrder/{id}")
    public String getOneOrder(
            @PathVariable("id")Integer id, Model model){
        Order order = orderMapper.orderById(id);
        List<OrderBook> orderBooks = orderBookMapper.orderBookById(id);
        model.addAttribute("orderNow",order);
        model.addAttribute("orderBooksNow",orderBooks);
        return "admin/orderdetails";
    }
    //    管理员订单模糊查询
    @PostMapping("/adminToSeachOrder")
    public String adminToSeachOrder(String time,String id, Model model) {
        if (time==null){
            String s;
            s = "%" + id + "%";
            List<Order> orders = orderMapper.searchOrder("%%", s);
            System.out.println(orders);
            model.addAttribute("orderAll",orders);
            model.addAttribute("sid", id);
            return "admin/manage-order";
        }else if (id==null){
            String t;
            t = time + "%";
            List<Order> orders = orderMapper.searchOrder(t,"%%");
            System.out.println(orders);
            model.addAttribute("orderAll",orders);
            model.addAttribute("stime", time);
            return "admin/manage-order";
        }else {
            String t;
            t = time + "%";
            String s;
            s = "%" + id + "%";
            List<Order> orders = orderMapper.searchOrder(t,s);
            System.out.println(orders);
            model.addAttribute("orderAll",orders);
            model.addAttribute("stime", time);
            model.addAttribute("sid", id);
            return "admin/manage-order";
        }
    }


}

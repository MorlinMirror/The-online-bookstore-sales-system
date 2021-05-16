package personal.moyilin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import personal.moyilin.pojo.Order;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

//    获得全部订单
    List<Order> allOrder();

//  获得该用户所有订单
    List<Order> allOrderById(int uid);
//  获得当前订单
    Order orderById(int id);
//*****************获得最后一个**************
    Order orderGetEndOne();
//*************插入订单****************
    int addOrder(Order order);

    int updateOrder(Order order);

    int deleteOrder(int id);

    //    模糊查询
    List<Order> searchOrder(String time,String id);
}

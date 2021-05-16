package personal.moyilin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import personal.moyilin.pojo.OrderBook;

import java.util.List;

@Mapper
@Repository
public interface OrderBookMapper {
    List<OrderBook> allOrderBook();

//    获得指定订单购买的书籍
    List<OrderBook> orderBookById(int id);

//**********插入订单书籍***************
    int addOrderBook(OrderBook orderBook);

    int updateOrderBook(OrderBook orderBook);

    int deleteOrderBook(int id);
}

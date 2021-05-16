package personal.moyilin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import personal.moyilin.pojo.Cart;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
    List<Cart> allCart();

//    搜索一个用户所有购物车
    List<Cart> cartByUid(int id);

//    搜索一个用户一本书
    Cart cartById(int id,int uid);

//   增加一个用户购物车一本书
    int addCart(int id,int uid);

    //   更新一个用户购物车一本书
    int updateCartOne(int id,int uid,int num);

//    清空购物车
    int deleteCart(int id);

//    删除一个用户购物车一本书
    int deleteCartOne(int id,int uid);
    
    
    
}

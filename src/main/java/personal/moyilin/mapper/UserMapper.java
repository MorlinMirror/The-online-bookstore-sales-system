package personal.moyilin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import personal.moyilin.pojo.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> allUser();

    User userById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

//    通过手机查找
    User loginByPhone(String phone);
//    通过邮箱查找
    User loginByEmail(String email);
//    手机注册
    int regUserByPhone(User user);
    //邮箱注册
    int regUserByEmail(User user);

    User userGetEndOne();

}

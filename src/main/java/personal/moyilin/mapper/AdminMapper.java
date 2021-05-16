package personal.moyilin.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import personal.moyilin.pojo.Admin;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<Admin> allAdmin();

    Admin AdminById(int id);

    Admin adminGetEndOne();

    int addAdmin(Admin Admin);

    int updateAdmin(Admin Admin);

    int deleteAdmin(int id);

    Admin loginByPhone(String phone);
}

package personal.moyilin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import personal.moyilin.pojo.Book;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
//    -----------------获得书籍-----------------------------------------
//    正常获得全部书籍
    List<Book> getBook();
    //按热度获得全部书籍
    List<Book> popuBook();
    //按分类获得全部书籍
    List<Book> sortBook();
//    按分类id获得该分类全部书籍
    List<Book> sortBookOnly(int id);
//    获得分类
    List<Book> getBookSort();

//   按书号获得单个书籍
    Book bookById(int id);
//    按作者名字获得书籍
    List<Book> bookByAuthor(String name);
//    按id搜索分类
    String sortById(int id);
    //   按书号获得单个书籍
    Book bookSortById(int id);
    //*****************书籍获得最后一个**************
    Book bookGetEndOne();
    //*****************书籍分类获得最后一个**************
    Book bookSortGetEndOne();

//    模糊查询
    List<Book> searchBook(String name);
    //    模糊查询
    List<Book> searchBookisbn(String isbn);
//    ----------书籍操作-----------------------------------------------------
//增加书籍
    int addBook(Book Book);
//更新书籍
    int updateBook(Book Book);
//删除书籍
    int deleteBook(int id);
    //    ----------书籍操作-----------------------------------------------------
//增加书籍分类
    int addBookSort(Book Book);
    //更新书籍分类
    int updateBookSort(Book Book);
    //删除书籍分类
    int deleteBookSort(int id);

}

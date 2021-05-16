package personal.moyilin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Clob;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Book implements Serializable {
    int book_id;
    String book_isbn;
    String book_name;
    String book_author;
    String book_pub;
    int book_num;
    float book_price;
    String book_image;
    int sort_id;
    String book_time;
    String book_outline;
    String sort_name;
    String sort_img;


    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_pub() {
        return book_pub;
    }

    public void setBook_pub(String book_pub) {
        this.book_pub = book_pub;
    }

    public int getBook_num() {
        return book_num;
    }

    public void setBook_num(int book_num) {
        this.book_num = book_num;
    }

    public float getBook_price() {
        return book_price;
    }

    public void setBook_price(float book_price) {
        this.book_price = book_price;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public String getBook_outline() {
        return book_outline;
    }

    public void setBook_outline(String book_outline) {
        this.book_outline = book_outline;
    }

    public String getSort_name() {
        return sort_name;
    }

    public void setSort_name(String sort_name) {
        this.sort_name = sort_name;
    }

    public String getSort_img() {
        return sort_img;
    }

    public void setSort_img(String sort_img) {
        this.sort_img = sort_img;
    }
}



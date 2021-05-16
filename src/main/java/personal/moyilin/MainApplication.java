package personal.moyilin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author cloudlandboy
 * @Date 2020/9/17 am_11:29
 * @Since 1.0.0
 * springBootApplication：标注一个主程序类，表示这个是一个Springboot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {

        //启动
        SpringApplication.run(MainApplication.class,args);
    }
}

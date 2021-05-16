package personal.moyilin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("user/login");
//        registry.addViewController("/login.html").setViewName("user/login");
        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/login2").setViewName("user/login-email");
        registry.addViewController("/register").setViewName("user/register");
        registry.addViewController("/register-email").setViewName("user/register-email");
//        registry.addViewController("/register.html").setViewName("user/register");
//        registry.addViewController("/blog-category.html").setViewName("blog-category");
//        registry.addViewController("/blog-category").setViewName("blog-category");
//        registry.addViewController("/blog-gallery.html").setViewName("blog-gallery");
//        registry.addViewController("/blog-gallery").setViewName("blog-gallery");
//        registry.addViewController("/blog-list.html").setViewName("blog-list");
//        registry.addViewController("/blog-list").setViewName("blog-list");
        registry.addViewController("/test").setViewName("test");
//        registry.addViewController("/upbook").setViewName("admin/upbook");
        registry.addViewController("/admin").setViewName("admin/login-admin");
//        registry.addViewController("/booktake").setViewName("/booktake.html");
//        registry.addViewController("/book-cart").setViewName("/book-cart.html");
//        registry.addViewController("/information").setViewName("user/information");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/information","/book-cart","/order-all","/toUpUserByUser","/cartUser/*","/toAllOrder","/toAddCart/*"
                        );
        registry.addInterceptor(new AdminLoginHandlerInterceptor())
                .addPathPatterns("/adminbook","/toAddBook","/toUpBook/*","/getAllOrder","/getOneOrder/*","/toDelBook/*"
                ,"/getSort" ,"/toAddBookSort" ,"/toUpBookSort/*","/toDelBookSort/*");
        registry.addInterceptor(new AdminGradeHandlerInterceptor())
                .addPathPatterns("/adminUser","/toAddUser","/toUpUser/*","/toDelUser/*");
        registry.addInterceptor(new AdminGradeTwoHandlerInterceptor())
                .addPathPatterns("/allAdmin","/toAddAdmin","/toUpAdmin/*","/toDelAdmin/*");


    }
//    @Configuration public class ResourcesConfig implements WebMvcConfigurer {
//        @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            // 静态文件版本管理（MD5方式）
//            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
//                    .resourceChain(false) .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
//        }
//    }



    //    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }
}

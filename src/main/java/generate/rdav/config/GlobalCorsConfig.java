package generate.rdav.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOrigins("*");
//        registry.addMapping("/**")   //允许跨域访问的路径
//        .allowedOrigins("*")                    //允许跨域访问的源
//        .allowedHeaders("*")                    //允许头部设置
//        .allowedMethods("get","post")                    //允许的请求方法
//        .maxAge(1800)                           //预检间隔时间
//        .allowCredentials(true);                //是否允许发送Cookie
    }
}

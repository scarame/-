package generate.rdav.config;

import generate.rdav.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    public void addInterceptors(InterceptorRegistry registry) {
        String[] generalApi =
                {
                        "/**/user/login/**/",
                        "/**/user/register/**/",
                        "/**/user/getUserById/**/",
                        "/**/rentalInfo/getInfoList/**/",
                        "/**/rentalInfo/getDetail/**/",
                        "/**/rentalInfo/recommend/**/",
                        "/**/rentalInfo/search/**/"
                };
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(generalApi);

    }
}

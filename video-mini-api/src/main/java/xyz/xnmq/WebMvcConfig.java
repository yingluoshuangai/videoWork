package xyz.xnmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import xyz.xnmq.interceptor.MimiInterceptor;

/**
 * @Author: by xnmq
 * @Data: 2019/7/25
 * @Description: 将 "D:/material/video_space/" 文件夹下的静态资源映射出去，使得通过url可以访问到
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    /**
     * 将本地资源暴露
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:D:/material/video_space/")
                ;
    }

    //注册拦截器
    @Bean
    public MimiInterceptor mimiInterceptor(){
        return new MimiInterceptor();
    }

    //启用拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mimiInterceptor()).addPathPatterns("/user/findOne")
                .addPathPatterns("/userLikeVideo/**");
        super.addInterceptors(registry);
    }
}

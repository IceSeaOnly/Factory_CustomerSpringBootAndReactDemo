package site.binghai.biz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.binghai.biz.filter.NormalUserFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * */
    @Bean
    public NormalUserFilter normalUserFilter() {
        return new NormalUserFilter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/normal/index").setViewName("normalView");
        registry.addViewController("/normalLogin").setViewName("normalView");
    }

    /**
     * 设置拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(normalUserFilter()).addPathPatterns("/normal/**");
    }
}


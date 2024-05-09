package com.gdb.doordash.config;

import com.gdb.doordash.interceptor.JwtTokenAdminInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */

@Slf4j
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Resource
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");
    }

    /**
     * 设置静态资源映射
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始做静态映射...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 扩展 SpringMVC 框架的消息转化器
     * @param converters the list of configured converters to extend
     */
    // todo 不知道为什么添加了消息转换器后，knife4j 没法使用
/*    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器....");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对像转换器可以将 Java 对象序列化为 json 数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转换器加入容器中，并且把优先级设置为最高
        converters.add(0, converter);
    }*/
}

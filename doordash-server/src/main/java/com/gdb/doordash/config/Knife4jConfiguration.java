package com.gdb.doordash.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Mr.Gui
 * @program: sky-take-out
 * @create: 2024-03-26 10:08
 * @description: knife4j 相关配置
 **/

@Slf4j
@Configuration
public class Knife4jConfiguration {
    /**
     * 通过knife4j生成接口文档
     */
    @Bean
    public OpenAPI myOpenAPI() {
        // todo 不知道为什么这里会执行两次
        log.info("准备生成接口文档...");
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("苍穹外卖项目接口文档")
                        // 接口文档简介
                        .description("苍穹外卖项目接口文档（这是基于Knife4j OpenApi3的接口文档）")
                        .termsOfService("https://blog.csdn.net/weixin_65032328?type=blog")
                        // 接口文档版本
                        .version("v1.0.0")
                        // 接口文档协议
                        .license(new License()
                                .name("许可协议")
                                .url("https://blog.csdn.net/weixin_65032328?type=blog"))
                        // 开发者联系方式
                        .contact(new Contact()
                                .name("迷人的小宝")
                                .email("dingbaogui8@gmail.com")
                                .url("https://blog.csdn.net/weixin_65032328?type=blog")))
                .externalDocs(new ExternalDocumentation()
                        .description("小宝945博客")
                        .url("https://blog.csdn.net/weixin_65032328?type=blog"));
    }
}

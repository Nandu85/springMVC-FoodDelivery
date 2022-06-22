package com.narola.fooddelivery;

import com.narola.fooddelivery.utility.DBConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.narola.fooddelivery")
@EnableWebMvc
@PropertySource("classpath:config.properties")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResoler() {
        InternalResourceViewResolver irv = new InternalResourceViewResolver();
        irv.setPrefix("/WEB-INF/pages/");
        irv.setSuffix(".jsp");
        return irv;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
        registry.addResourceHandler("images/**").addResourceLocations("./WEB-INF/resources/images/");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public DBConnection getDBConfig(@Value("${"+"${DB-IN-USE}"+"_dbname}") String dbName, @Value("${"+"${DB-IN-USE}"+"_dburl}") String dbUrl, @Value("${"+"${DB-IN-USE}"+"_username}") String dbUser, @Value("${"+"${DB-IN-USE}"+"_password}") String dbPass) {
        DBConnection dbConnection = new DBConnection(dbName,dbUrl,dbUser,dbPass);
        return dbConnection;
    }

}

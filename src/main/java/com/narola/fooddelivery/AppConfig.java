package com.narola.fooddelivery;

import com.narola.fooddelivery.utility.CustomInterceptor;
import com.narola.fooddelivery.utility.CustomLocalResolver;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.narola.fooddelivery")
@EnableWebMvc
@PropertySource("classpath:config.properties")
//@Import(DAOConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories("com.narola.fooddelivery.restaurants.repository")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean managerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPersistenceUnitName("persistenceUnit");
        entityManagerFactoryBean.setPackagesToScan("com.narola.fooddelivery");

        Map<String,Object> propertyMap=new HashMap<>();
        propertyMap.put(AvailableSettings.JPA_JDBC_DRIVER,env.getProperty(AvailableSettings.JPA_JDBC_DRIVER));
        propertyMap.put(AvailableSettings.JPA_JDBC_URL,env.getProperty(AvailableSettings.JPA_JDBC_URL));
        propertyMap.put(AvailableSettings.JPA_JDBC_USER,env.getProperty(AvailableSettings.JPA_JDBC_USER));
        propertyMap.put(AvailableSettings.JPA_JDBC_PASSWORD,env.getProperty(AvailableSettings.JPA_JDBC_PASSWORD));
        propertyMap.put(AvailableSettings.SHOW_SQL,env.getProperty(AvailableSettings.SHOW_SQL));
        propertyMap.put(AvailableSettings.FORMAT_SQL,env.getProperty(AvailableSettings.FORMAT_SQL));
//        propertyMap.put(AvailableSettings.HBM2DDL_DATABASE_ACTION,env.getProperty(AvailableSettings.HBM2DDL_DATABASE_ACTION));
        propertyMap.put(AvailableSettings.HBM2DDL_AUTO,env.getProperty(AvailableSettings.HBM2DDL_AUTO));

        entityManagerFactoryBean.setJpaPropertyMap(propertyMap);
        return entityManagerFactoryBean;
    }

    @Bean("transactionManager")
    public JpaTransactionManager createTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        return transactionManager;
    }

//    @Bean("entityManager")
//    public EntityManager managerFactoryBean(LocalContainerEntityManagerFactoryBean entityManagerFactory){
//        EntityManager entityManager = entityManagerFactory.createNativeEntityManager();
//
//    }

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
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(60);
        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setFileEncodings();
        return messageSource;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(new CustomInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocalResolver();
    }
}

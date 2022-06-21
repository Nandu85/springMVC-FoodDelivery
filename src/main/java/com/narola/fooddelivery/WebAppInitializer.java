package com.narola.fooddelivery;

import com.narola.fooddelivery.utility.DBConnection;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null ;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // Optionally also set maxFileSize, maxRequestSize, fileSizeThreshold
        registration.setMultipartConfig(new MultipartConfigElement("E:/tmp"));
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("DB-IN-USE","MYSQL");
        servletContext.setInitParameter("MYSQL_dbname","foodorderingsystem");
        servletContext.setInitParameter("MYSQL_dburl","jdbc:mysql://localhost:3306/");
        servletContext.setInitParameter("MYSQL_username","root");
        servletContext.setInitParameter("MYSQL_password","123456");

        DBConnection.getInstance().setUrl("jdbc:mysql://localhost:3306/");
        DBConnection.getInstance().setDbname("foodorderingsystem");
        DBConnection.getInstance().setUsername("root");
        DBConnection.getInstance().setPassword("123456");
        super.onStartup(servletContext);
    }
}

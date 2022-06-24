package com.narola.fooddelivery.utility;

import com.narola.fooddelivery.dishes.dao.IDishDAO;
import com.narola.fooddelivery.restaurants.dao.IRestDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class DAOConfig {

    @Value("${DB-IN-USE}")
    private String dbType;

    public static IDaoConfig daoConfig;

    @Bean
    public DBConnection getDBConfig(@Value("${"+"${DB-IN-USE}"+"_dbname}") String dbName, @Value("${"+"${DB-IN-USE}"+"_dburl}") String dbUrl, @Value("${"+"${DB-IN-USE}"+"_username}") String dbUser, @Value("${"+"${DB-IN-USE}"+"_password}") String dbPass) {
        DBConnection dbConnection = new DBConnection(dbName,dbUrl,dbUser,dbPass);
        return dbConnection;
    }

    @Bean
    public IDaoConfig getDaoConfig(){
        if (dbType.equals("MYSQL"))
            daoConfig = new MYSQLDaoConfig();
        else if (dbType.equals("POSTGRESQL"))
            daoConfig = new PostGresDaoConfig();
        return daoConfig;
    }

    @Bean
    public IRestDAO getRestDAO(){
        return daoConfig.getRestDAO();
    }

    @Bean
    public IDishDAO getDishDAO(){
        return daoConfig.getDishDAO();
    }
}

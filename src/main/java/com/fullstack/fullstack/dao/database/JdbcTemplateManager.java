package com.fullstack.fullstack.dao.database;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.fullstack.fullstack.util.database_prop.DataSourcePropertiesProvider;

/**
 * This class is a configuration class that provides a JdbcTemplate depending onn the database required.
 */
@Configuration
public class JdbcTemplateManager {

    @Autowired
    private DataSourcePropertiesProvider dataSourcePropertiesprovider;
    
    public JdbcTemplate getFirsJdbcTemplate(){
        return new JdbcTemplate( getFirstDataSource() );
    }

    private DataSource getFirstDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        DataSourceProperties properties = this.dataSourcePropertiesprovider.getFirstDataSourceProperties();
        dataSource.setUrl(properties.getUrl());
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;        
    }

    public JdbcTemplate getSeconJdbcTemplate(){
        return new JdbcTemplate( getSeconDataSource() );
    }

    private DataSource getSeconDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        DataSourceProperties properties = this.dataSourcePropertiesprovider.getSeconDataSourceProperties();
        dataSource.setUrl(properties.getUrl());
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    // create a bean for the spring framework to initialize the database
    @Bean
    public DataSourceInitializer dataSourceInitializer(){
        DataSourceInitializer dataSourceinitializer = new DataSourceInitializer();
        dataSourceinitializer.setDataSource(getSeconDataSource());

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("scripts/schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("scripts/data.sql"));

        dataSourceinitializer.setDatabasePopulator(resourceDatabasePopulator);
        dataSourceinitializer.setEnabled(true);
        return dataSourceinitializer;
    }
}

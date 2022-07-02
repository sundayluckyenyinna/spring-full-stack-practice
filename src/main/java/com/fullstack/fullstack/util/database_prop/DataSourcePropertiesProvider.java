package com.fullstack.fullstack.util.database_prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * This class is a configuration class that provides the basic configuartion properties for the
 * datasources used in this application. It will have tight connection between the properties files 
 * and the configuration parameters.
 * Since the parameters are supposed to be in thw application.properties file, there is no need to
 * explicitly state it in the @PropertySource or @PropertySources annotation.
 */

@Configuration
public class DataSourcePropertiesProvider {
    
    @Autowired
    private Environment environment;


    /**
     * Returns the DataSourceProperties object of the first datasource.
     * @return DataSourceProperties
     */
    public DataSourceProperties getFirstDataSourceProperties(){
        DataSourceProperties firstDataSourceProperties = new DataSourceProperties();
        firstDataSourceProperties.setUrl(environment.getProperty(getPropertyKey("first.url")));
        firstDataSourceProperties.setDriverClassName(environment.getProperty(getPropertyKey("first.driver-class-name")));
        firstDataSourceProperties.setUsername(environment.getProperty(getPropertyKey("first.username")));
        firstDataSourceProperties.setPassword(environment.getProperty(getPropertyKey("first.password")));
        return firstDataSourceProperties;
    }

    /**
     * Returns the DataSourceProperties object for the second datasource
     * @return DataSourceProperties
     */
    public DataSourceProperties getSeconDataSourceProperties(){
        DataSourceProperties secondDataSourceProperties = new DataSourceProperties();
        secondDataSourceProperties.setUrl(environment.getProperty(getPropertyKey("second.url")));
        secondDataSourceProperties.setDriverClassName(environment.getProperty(getPropertyKey("second.driver-class-name")));
        secondDataSourceProperties.setUsername(environment.getProperty(getPropertyKey("second.username")));
        secondDataSourceProperties.setPassword(environment.getProperty(getPropertyKey("second.password")));
        return secondDataSourceProperties;
    }

    /**
     * A method that returns the complete path of the key used to access the properties in the application.properties
     * file
     */
    private String getPropertyKey(String uniqueKey ){
        return "spring.datasource." + uniqueKey.trim();
    }

}

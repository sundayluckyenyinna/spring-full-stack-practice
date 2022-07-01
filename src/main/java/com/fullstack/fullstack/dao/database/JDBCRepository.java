package com.fullstack.fullstack.dao.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fullstack.fullstack.dto.User;

@Configuration
public class JDBCRepository{

    // autowire an instance of the JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * A method to return a user from the database
     **/

    @Transactional
    public User getUserByEmail( String email ){
        String sql = "SELECT * FROM Users WHERE Email = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int id) throws SQLException {
                User user = new User();
                user.setEmail(email)
                    .setFirstName( rs.getString("First_Name"))
                    .setLastName(rs.getString("Last_Name"))
                    .setDepartment(rs.getString("Department"));
                return user;
            }
            
        }, new Object[]{email});
    }
}
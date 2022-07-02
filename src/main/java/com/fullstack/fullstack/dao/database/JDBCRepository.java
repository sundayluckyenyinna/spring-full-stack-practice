package com.fullstack.fullstack.dao.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fullstack.fullstack.dto.AuthenticationDTO;
import com.fullstack.fullstack.dto.User;

@Configuration
public class JDBCRepository{

    // autowire an instance of the JdbcTemplate
    @Autowired
    private JdbcTemplateManager jdbcTemplateManager;

    /**
     * Return a user with a given email address.
     * @param email
     * @return User
     */
    @Transactional
    public User getUserByEmail( String email ){

        JdbcTemplate jdbcTemplate = this.jdbcTemplateManager.getFirsJdbcTemplate();
        
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

    @Transactional
    public AuthenticationDTO getAuthenticatedUser(String username){
        JdbcTemplate jdbcTemplate = this.jdbcTemplateManager.getSeconJdbcTemplate();
        
        String sql = "SELECT * FROM Users WHERE Username = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<AuthenticationDTO>(){
            @Override
            public AuthenticationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                AuthenticationDTO authDTO = new AuthenticationDTO();
                authDTO.setUserId( rs.getString("User_ID"))
                        .setUsername(username)
                        .setPassword(rs.getString("Password"))
                        .setRoles(rs.getString("Roles"))
                        .setAuthorities(rs.getString("Authorities"));
                return authDTO;
            }
        }, new Object[]{username});
    }
}
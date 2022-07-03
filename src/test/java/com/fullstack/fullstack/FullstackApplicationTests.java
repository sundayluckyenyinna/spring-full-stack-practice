package com.fullstack.fullstack;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fullstack.fullstack.dao.database.JDBCRepository;
import com.fullstack.fullstack.dto.AuthenticationDTO;

import jdk.internal.joptsimple.internal.Strings;

@SpringBootTest
class FullstackApplicationTests {

	// import the repository object
	@Autowired
	private JDBCRepository jdbcRepository;

	@Test
	void contextLoads() {
	}

	// test for the database
	@Test
	void testFindAllUsers(){
		List<AuthenticationDTO> list = jdbcRepository.getAllAuthenticationDTOs();
		assertNotNull(list);
		assertTrue(!list.isEmpty());
	}

	@Test
	public void testFindUserByEmail(){
		AuthenticationDTO dto = jdbcRepository.getAuthenticatedUser("sundaylucky360@yahoo.com");
		assertNotNull(dto);
		assertTrue(dto.getUsername() != Strings.EMPTY);
	}
}

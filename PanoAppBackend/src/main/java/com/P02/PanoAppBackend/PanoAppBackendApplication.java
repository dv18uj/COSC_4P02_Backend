package com.P02.PanoAppBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PanoAppBackendApplication implements CommandLineRunner{

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(PanoAppBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		String sql = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
		int result = jdbcTemplate.update(sql, "test name", "test password", "testemail@email.com");

		if (result > 0)
			System.out.println("Insert successful.");
		else
			System.out.println("Insert failed.");
	}
}

package com.example.demo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
public class UserControllerTest {

	@LocalServerPort
	private int port;

	@Value("classpath:testbed.sql")
	private Resource testbed;

	@Autowired
	private DataSource dataSource;

	private static final String endpoint = "http://localhost:%s/users";

	@Before
	public void before() {
		new ResourceDatabasePopulator(testbed).execute(dataSource);
	}

	@Test
	public void get() {
		Response response = RestAssured.given()
				.when()
				.get(String.format(endpoint, port));
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Test
	public void getAgain() {
		Response response = RestAssured.given()
				.when()
				.get(String.format(endpoint, port));
		Assert.assertEquals(200, response.getStatusCode());
	}

}

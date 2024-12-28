package com.ebazar.microservices.order_service;

import com.ebazar.microservices.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
 * The @AutoConfigureWireMock annotation is
 * used to automatically configure a WireMock server instance in the test context.
 * import the TestcontainersConfiguration class to run the db.
 * import the spring-cloud-starter-contract-stub-runner for m/s call.
 * import rest assured for api testing.
 */
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {


	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		//set the base uri to the random port assigned to the application
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
				{
				    "skuCode":"iphone_15",
				    "price":1000,
				    "quantity":1
				}
				""";

		InventoryClientStub.stubInventoryCall("iphone_15", 1);

		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order")
				.then()
				.statusCode(201)
				.extract()
				.body()
				.asString();
	}

}

package com.sitoo.warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sitoo.warehouse.models.Products;
import com.sitoo.warehouse.repository.ProductsRepository;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WarehouseManagementApplicationTests {
	@LocalServerPort
	private int port;
	@MockBean
	ProductsRepository productsRepository;
  @Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void testPostProduct() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("{\"title\": \"Awesome socks\", \"sku\": \"SC-4511\",\"barcodes\": [\"7410852096307\"]}", headers);
		ResponseEntity<Products> response = restTemplate
				.postForEntity(new URL("http://localhost:" + port + "/api/products").toString(), request, Products.class);
		assertEquals(1, response.getBody().getProductId());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

}

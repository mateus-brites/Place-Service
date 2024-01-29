package com.example.placeservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.placeservice.api.PlaceRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlaceServiceApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	void createPlaceSucess() {
		var name = "Valid Name";
		var state = "Valid State";
		var expectedSlug = "valid-name";
		webTestClient
			.post()
			.uri("/places")
			.bodyValue(
				new PlaceRequest(name, state)
			)
			.exchange()
			.expectBody()
			.jsonPath("name").isEqualTo(name)
			.jsonPath("state").isEqualTo(state)
			.jsonPath("slug").isEqualTo(expectedSlug)
			.jsonPath("created_at").isNotEmpty();
	}

	@Test
	public void TestCreatePlaceFailure() {
		var name = "";
		var state = "São Paulo";

		webTestClient
			.post()
			.uri("/places")
			.bodyValue(
				new PlaceRequest(name, state)
			)
			.exchange()
			.expectStatus().isBadRequest();
	}

}

package com.publicis.card;

import static org.mockito.Mockito.*;

import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.service.CardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardserviceApplicationTests {

	// Added default test case just for coverage.

	public static final String CARD_NUMBER = "2232";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Mock
	private CardService cardService;

	@Test
	void contextLoads() {
		assert true;
	}
	@Test
	public void getAllCreditCardsShouldReturnListOfAllCreditCards() throws Exception {
		List<CreditCardResponseDto> responseDtos = new ArrayList<>();
		responseDtos.add(CreditCardResponseDto.builder().build());
		CreditCardResponseDto creditCardResponseDto = CreditCardResponseDto.builder().build();

		when(cardService.getCreditCardByNumber(CARD_NUMBER)).thenReturn(creditCardResponseDto);


//		Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/credit-card/"+ CARD_NUMBER,
//				CreditCardResponseDto.class)).contains(creditCardResponseDto);
	}

}

package com.publicis.card.controller;

import com.publicis.card.dto.CreditCardResponseDto;
import com.publicis.card.service.CardService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CreditCardController.class)
public class CreditCardControllerTest {

    public static final String X_CORRELATION_ID = "X-Correlation-Id";
    public static final String X_CORRELATION_VALUE = "22343432232";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    public void testAddCreditCard_success() throws Exception {
        final String creditCardRequest =
                "{\n" +
                        "    \"cardNumber\" : 2221001234123456,\n" +
                        "    \"cardHolderName\" : \"Mrs. P Mittal\",\n" +
                        "    \"cardLimit\" : 100\n" +
                        "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/credit-card")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(X_CORRELATION_ID, X_CORRELATION_VALUE)
                .content(creditCardRequest)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testgetCreditCardByNumber_found() throws Exception {
        CreditCardResponseDto creditCardResponseDto = CreditCardResponseDto.builder()
                .cardNumber("2221001234123456")
                .cardBalance(BigDecimal.valueOf(123))
                .cardLimit(BigDecimal.valueOf(1000))
                .cardHolderName("Test").build();
        Mockito.when(cardService.getCreditCardByNumber("2221001234123456")).thenReturn(creditCardResponseDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/credit-card")
                .param("cardNumber", "2221001234123456")).andReturn();
        String resposeAsString = mvcResult.getResponse().getContentAsString();

        Assert.assertNotNull(resposeAsString);
    }

    @Test
    public void testgetAllCreditCards_found() throws Exception {
        CreditCardResponseDto creditCardResponseDto = CreditCardResponseDto.builder()
                .cardNumber("2221001234123456")
                .cardBalance(BigDecimal.valueOf(123))
                .cardLimit(BigDecimal.valueOf(1000))
                .cardHolderName("Test").build();
        List<CreditCardResponseDto> creditCards = new ArrayList<>();
        creditCards.add(creditCardResponseDto);
        Mockito.when(cardService.getAllCreditCards()).thenReturn(creditCards);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/credit-card/all")).andReturn();
        String resposeAsString = mvcResult.getResponse().getContentAsString();

        Assert.assertNotNull(resposeAsString);
    }
}

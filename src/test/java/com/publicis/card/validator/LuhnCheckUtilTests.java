package com.publicis.card.validator;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LuhnCheckUtilTests {

	@Test
	@DisplayName("Test Success scenario")
	void shouldReturnTrueForValidNumbers() {
		Assert.assertTrue(LuhnCheckUtil.checkLuhn(Long.valueOf(2221001234123456l)));
	}

	@Test
	void shouldReturnFalseForInvalidNumbers() {
		Assert.assertFalse(LuhnCheckUtil.checkLuhn(Long.valueOf(222100123412346l)));
	}
}

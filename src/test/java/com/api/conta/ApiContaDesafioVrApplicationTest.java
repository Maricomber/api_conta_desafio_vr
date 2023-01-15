package com.api.conta;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiContaDesafioVrApplicationTest {

	private ApiContaDesafioVrApplication createTestSubject() {
		return new ApiContaDesafioVrApplication();
	}

	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		ApiContaDesafioVrApplication.main(args);
	}
}
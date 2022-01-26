package com.snack.learning.lambda.snacklambda;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SnackLambdaApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(SnackLambdaApplicationTests.class);

	@Test
	void contextLoads() {
		logger.info("contextLoads");
	}

}

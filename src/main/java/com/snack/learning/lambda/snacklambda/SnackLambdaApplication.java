package com.snack.learning.lambda.snacklambda;

import java.io.IOException;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SnackLambdaApplication {

	private static final Logger logger = LoggerFactory.getLogger(SnackLambdaApplication.class);

	public static void main(String[] args) {
	
		OkHttpClient client = new OkHttpClient();

		String url = "https://9hdekv98gh.execute-api.us-east-1.amazonaws.com/test/testLambda";
		IntStream.range(1, 8).parallel().forEach(e->sendUrlRepeatedly(client, url, 10));

		// String sleepUrl = "https://uzypgy0p98.execute-api.us-east-1.amazonaws.com/test/testParallelLambda";
		// IntStream.range(1, 8).parallel().forEach(e->sendUrlRepeatedly(client, sleepUrl, 10));
	}

	private static void sendUrlRepeatedly(OkHttpClient client, String url, int repeatTimes) {
		IntStream.range(1, repeatTimes).forEach(e->sendUrl(client, url));
	}
	private static void sendUrl(OkHttpClient client, String url) {
		Request request = new Request.Builder().url(url).build();

		try {
			Response response = client.newCall(request).execute();
			ResponseBody body = response.body();
			if (body == null) {
				logger.error("response body is null");
			} else {
				logger.info(body.string());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

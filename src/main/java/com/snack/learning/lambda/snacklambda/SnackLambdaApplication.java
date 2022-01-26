package com.snack.learning.lambda.snacklambda;

import java.io.IOException;
import java.util.stream.IntStream;

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
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
		
		String url = "https://9hdekv98gh.execute-api.us-east-1.amazonaws.com/test/testLambda";
		IntStream.rangeClosed(1, 20).parallel().forEach(e->sendUrl(client, url));

		// String sleepUrl = "https://uzypgy0p98.execute-api.us-east-1.amazonaws.com/test/testParallelLambda";
		// IntStream.rangeClosed(1, 20).parallel().forEach(e->sendUrl(client, sleepUrl));
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

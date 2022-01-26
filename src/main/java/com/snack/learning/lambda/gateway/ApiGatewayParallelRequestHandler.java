package com.snack.learning.lambda.gateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiGatewayParallelRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, Object> {

    private static final Logger logger = LoggerFactory.getLogger(ApiGatewayParallelRequestHandler.class);

    @Override
    public Object handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        logger.info("ApiGatewayParallelRequestHandler: " + input.getBody());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("ApiGatewayParallelRequestHandler: after");

        return new APIGatewayProxyResponseEvent().withBody("Hi " + input.getBody()).withStatusCode(200);
    }
}

package content.integration.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

// Currently not being used. It was just an example how we can build our own AWS client

public class MyAWSClient implements AWSClient {
    private AmazonS3 client;

    @Handler //Default method for Camel
    public void putFileinS3(Exchange exchange) throws Exception {
        System.out.println("Processing file for S3: "
                + exchange.getIn().getHeader("CamelFileName") + "\n"
                + exchange.getIn().getBody(String.class)
        );
        client = AmazonS3ClientBuilder.defaultClient();
        client.putObject("1p-graph", "load/out/" + exchange.getIn().getMessageId() + ".json",exchange.getIn().getBody(String.class));
    }

    public void putMessageInSQS(Exchange exchange) throws Exception {
        System.out.println("Processing message for SQS: "
                + exchange.getIn().getHeader("CamelFileName") + "\n"
                + exchange.getIn().getBody(String.class)
        );
        AmazonSQS client = AmazonSQSClientBuilder.defaultClient();
        client.sendMessage("WorkflowTasksQueue", exchange.getIn().getBody(String.class));
    }

}

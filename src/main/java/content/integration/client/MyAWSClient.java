package content.integration.client;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

/**
 * Created by u6023035 on 24/01/2017.
 */
public class MyAWSClient implements AWSClient {
    private AmazonS3Client client;

    @Handler //Default method for Camel
    public void putFileinS3(Exchange exchange) throws Exception {
        System.out.println("Processing file for S3: "
                + exchange.getIn().getHeader("CamelFileName") + "\n"
                + exchange.getIn().getBody(String.class)
        );
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJKCXXNGWDYGLTRDA", "rTuMVlKmO2s74i2ol+nkohmM/a5U0/aNvCIRYrMM");
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        client = new AmazonS3Client(awsCredentials, clientConfiguration);
        client.putObject("1p-graph", "load/out/" + exchange.getIn().getMessageId() + ".json",exchange.getIn().getBody(String.class));
    }

    public void putMessageInSQS(Exchange exchange) throws Exception {
        System.out.println("Processing message for SQS: "
                + exchange.getIn().getHeader("CamelFileName") + "\n"
                + exchange.getIn().getBody(String.class)
        );
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJKCXXNGWDYGLTRDA", "rTuMVlKmO2s74i2ol+nkohmM/a5U0/aNvCIRYrMM");
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        AmazonSQS client = new AmazonSQSClient(awsCredentials, clientConfiguration);

        client.sendMessage("WorkflowTasksQueue", exchange.getIn().getBody(String.class));
//        client.sendMessage("WorkflowTasksQueue", "<test>aaa</test>");
    }

}

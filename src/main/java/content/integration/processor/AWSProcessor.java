package content.integration.processor;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by u6023035 on 24/01/2017.
 */
public class AWSProcessor implements Processor {
    private AmazonS3Client client;

    public void process(Exchange exchange) throws Exception {
        System.out.println("Processing file: "
                + exchange.getIn().getHeader("CamelFileName") + "\n"
                + exchange.getIn().getBody(String.class)
        );
        AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJKCXXNGWDYGLTRDA", "rTuMVlKmO2s74i2ol+nkohmM/a5U0/aNvCIRYrMM");
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        client = new AmazonS3Client(awsCredentials, clientConfiguration);
        client.putObject("1p-graph", "load/in/" + exchange.getIn().getMessageId() + ".json",exchange.getIn().getBody(String.class));
    }
}

package content.integration.processor;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

// Currently not being used. It was just an example how we can build our own AWS client as a Camel Processor

public class AWSProcessor implements Processor {
    private AmazonS3 client;

    public void process(Exchange exchange) throws Exception {
        System.out.println("Processing file: "
                + exchange.getIn().getHeader("CamelFileName") + "\n"
                + exchange.getIn().getBody(String.class)
        );
        client = AmazonS3ClientBuilder.defaultClient();
        client.putObject("1p-graph", "load/in/" + exchange.getIn().getMessageId() + ".json",exchange.getIn().getBody(String.class));
    }
}

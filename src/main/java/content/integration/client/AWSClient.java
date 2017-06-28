package content.integration.client;

import org.apache.camel.Exchange;

/**
 * Created by u6023035 on 27/01/2017.
 */
public interface AWSClient {

    public void putFileinS3(Exchange exchange) throws Exception;

    public void putMessageInSQS(Exchange exchange) throws Exception;
}

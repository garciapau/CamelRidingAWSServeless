package content.integration.aggregation;

/**
 * Created by u6023035 on 01/02/2017.
 */

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {
    /**
     * Aggregates the messages.
     *
     * @param oldExchange  the existing aggregated message. Is <tt>null</tt> the
     *                     very first time as there are no existing message.
     * @param newExchange  the incoming message. This is never <tt>null</tt>.
     * @return the aggregated message.
     */
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newExchange.getIn().getBody(String.class);

        String body = oldBody + newBody;

        oldExchange.getIn().setBody(body);
        return oldExchange;
    }

}

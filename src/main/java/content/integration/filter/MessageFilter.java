package content.integration.filter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MessageFilter implements Processor {
    private Logger logger = LoggerFactory.getLogger(MessageFilter.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Filtering file: "+ exchange.getIn().getHeader("ArticleTitle"));
        String name = exchange.getIn().getHeader("ArticleTitle", String.class);
        if ("12 Emerging Sources".equalsIgnoreCase(name)) {
            exchange.getOut().setFault(true);
            logger.info("--> Article discarded : " + exchange.getIn().getHeader("ArticleTitle", String.class));
        }
    }
}

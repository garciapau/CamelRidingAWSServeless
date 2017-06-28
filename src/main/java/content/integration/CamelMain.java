package content.integration;

import content.integration.dashboard.Hawtio;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.camel.spring.Main;

import static org.apache.camel.com.github.benmanes.caffeine.cache.Caffeine.from;
import static org.apache.camel.model.rest.RestParamType.body;

public class CamelMain {
    // Use the Hawtio camel plugin instead of this main class
    public static void main(String[] args) throws Exception {
/*        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        Hawtio bean = (Hawtio) context.getBean("hawtio");
        bean.startDashboard();*/

//        CamelContext camelContext = new DefaultCamelContext();
        String filename = "META-INF/spring/camel-context.xml";
        ConfigurableApplicationContext spring =
                new ClassPathXmlApplicationContext(filename);
        SpringCamelContext camelContext = SpringCamelContext.springCamelContext(spring);

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
        System.out.println("Consumer started");

        System.out.println("Message sent");
        Exchange exchange = consumerTemplate.receiveNoWait("direct:end");
        producerTemplate.sendBody("direct:start", "Starting manual route... Camel Rocks");

        exchange = consumerTemplate.receiveNoWait("direct:end");
        System.out.println("\nResult is: " + exchange.getIn().getBody().toString());

        camelContext.stop();

/*
        String filename = "META-INF/spring/camel-context.xml";
        AbstractXmlApplicationContext spring =
                new ClassPathXmlApplicationContext(filename);
        spring.start();
        Thread.sleep(10000);
        spring.stop();
        spring.destroy();
*/

/*        Main main = new Main();
        main.setApplicationContextUri("META-INF/spring/camel-context.xml");
        main.enableHangupSupport();
        main.start();*/
    }
}
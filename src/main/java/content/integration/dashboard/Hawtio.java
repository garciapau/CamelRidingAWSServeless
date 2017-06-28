package content.integration.dashboard;

import io.hawt.embedded.Main;
import org.apache.camel.Handler;

/**
 * Created by u6023035 on 30/01/2017.
 */
public class Hawtio {
    // Use Hawtio camel plugin instead
    @Handler
    public void startDashboard() throws Exception {
        Main main = new Main();
        main.setWar("C:\\Products\\hawtio.war");
        main.showOptions();
        main.run();
    }
}

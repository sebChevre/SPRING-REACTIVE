package ch.globaz.dso.reactivespring.standardserver.application;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricRegistryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    private final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    private Meter helloGetCount;

    public TestController(MetricRegistry metricRegistry) {
        this.helloGetCount = metricRegistry.meter("helloGetCount");
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        LOGGER.debug("/hello endpoint, client: {}", request.getRemoteUser());


        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        helloGetCount.mark();
        return "Hello Spring Reactive Lab, standard server";
    }
}

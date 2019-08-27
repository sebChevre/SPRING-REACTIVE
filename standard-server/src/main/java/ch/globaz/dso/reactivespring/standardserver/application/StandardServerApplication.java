package ch.globaz.dso.reactivespring.standardserver.application;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class StandardServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardServerApplication.class);
    }

    @Bean
    public MetricRegistry metricRegistry(){
        return new MetricRegistry();
    }

    @Bean
    public Slf4jReporter slf4jReporter(){
        Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry()).build();
        reporter.start(1, TimeUnit.SECONDS);
        return reporter;
    }
}

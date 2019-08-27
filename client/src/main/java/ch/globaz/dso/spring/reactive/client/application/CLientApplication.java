package ch.globaz.dso.spring.reactive.client.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.stream.IntStream;

@SpringBootApplication
public class CLientApplication implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(CLientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CLientApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("Application start...");

        Date startTime = new Date();
        IntStream.range(0,10000).forEach(count -> {

            Date requestTime = new Date();
            WebClient client = WebClient.create("http://localhost:8080");


                   Mono<String> res =  client
                        .get()
                        .uri("/hello")
                        .exchange().block().bodyToMono(String.class);

            LOGGER.info("Request n° {}, executed in {} ms, cumulative time: {},resp: {}",
                    count,
                    new Date().getTime() - requestTime.getTime(),
                    new Date().getTime() - startTime.getTime(),
                    res.block());

        });

        LOGGER.info("Process ending: 10000 get request in {} ms", new Date().getTime()-startTime.getTime());
        LOGGER.info("APplication ending, exit now!");
    }
}

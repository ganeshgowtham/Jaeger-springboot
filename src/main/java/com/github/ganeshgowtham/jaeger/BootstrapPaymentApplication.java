package com.github.ganeshgowtham.jaeger;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class BootstrapPaymentApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootstrapPaymentApplication.class, args);
        log.info("TutorialApplication started");
    }

    @Bean
    public static JaegerTracer getTracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        Configuration config = new Configuration("Payments Application").withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }
}

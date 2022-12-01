package com.company.jmixpm.integration;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "main.datasource.url=jdbc:tc:postgresql:15.1:///postgres-test-db",
                "main.datasource.username=test",
                "main.datasource.password=pass",
                "jmix.data.dbmsType=postgres",
                "jmix.liquibase.contexts=!production-data"
        ).applyTo(applicationContext.getEnvironment());
    }
}

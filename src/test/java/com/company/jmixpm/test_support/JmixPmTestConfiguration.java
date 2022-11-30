package com.company.jmixpm.test_support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;

@Configuration
public class JmixPmTestConfiguration {

    @Bean("jmixpm_test_OauthHelper")
    OauthHelper oauthHelper(MockMvc mockMvc) {
        return new OauthHelper(mockMvc);
    }
}

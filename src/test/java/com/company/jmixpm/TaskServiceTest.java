package com.company.jmixpm;

import com.company.jmixpm.test_support.JmixPmTestConfiguration;
import com.company.jmixpm.test_support.OauthHelper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {JmixPmApplication.class, JmixPmTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class TaskServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OauthHelper oauthHelper;

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @DisplayName("Checks the computation of the least busy user")
    public void checkLeastBusyUser() throws Exception {
        String accessToken = oauthHelper.getAccessToken();

        GraphQLResponse graphQLResponse = graphQLTestTemplate.withBearerAuth(accessToken)
                .postForResource("com/company/jmixpm/api/query-least-busy-user.gql");

        Assertions.assertEquals("{\"data\":{\"leastBusyUser\":{\"username\":\"admin\"}}}",
                graphQLResponse.getRawResponse().getBody());
    }
}

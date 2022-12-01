package com.company.jmixpm.app;

import io.jmix.graphql.service.UserInfo;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@GraphQLApi
@Service
public class TaskGraphqlService {

    @Autowired
    private TaskService taskService;

    @GraphQLQuery(name = "leastBusyUser")
    @Transactional
    public UserInfo findLeastBusyUser() {
        return new UserInfo(taskService.findLeastBusyUser());
    }
}

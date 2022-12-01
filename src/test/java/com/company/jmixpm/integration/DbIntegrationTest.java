package com.company.jmixpm.integration;

import com.company.jmixpm.JmixPmApplication;
import com.company.jmixpm.app.TaskService;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.Task;
import com.company.jmixpm.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({PostgreSqlExtension.class, SpringExtension.class})
@ContextConfiguration(classes = JmixPmApplication.class, initializers = TestContextInitializer.class)
public class DbIntegrationTest {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SystemAuthenticator systemAuthenticator;

    @Test
    @DisplayName("Checks the computation of least busy user")
    void checkLeastBusyUser() {
        User user1 = dataManager.create(User.class);
        user1.setUsername("user1");
        User user2 = dataManager.create(User.class);
        user2.setUsername("user2");

        Project project = dataManager.create(Project.class);
        project.setName("Db integration test");
        project.setManager(user1);

        Task task = dataManager.create(Task.class);
        task.setName("Write integration test");
        task.setAssignee(user1);
        task.setProject(project);
        task.setEstimatedEfforts(10);

        Task task1 = dataManager.create(Task.class);
        task1.setName("Write integration test");
        task1.setAssignee(user2);
        task1.setProject(project);
        task1.setEstimatedEfforts(20);

        systemAuthenticator.runWithSystem(() -> {
            dataManager.save(user1, user2, project, task, task1);

            Assertions.assertEquals(user1, taskService.findLeastBusyUser());
        });
    }
}

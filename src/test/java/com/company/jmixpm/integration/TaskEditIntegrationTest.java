package com.company.jmixpm.integration;

import com.company.jmixpm.JmixPmApplication;
import com.company.jmixpm.entity.Task;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.screen.task.TaskEdit;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.ValueLoadContext;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.Screens;
import io.jmix.ui.testassist.UiTestAssistConfiguration;
import io.jmix.ui.testassist.junit.UiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@UiTest(mainScreenId = "MainScreen")
@ContextConfiguration(classes = {JmixPmApplication.class, UiTestAssistConfiguration.class})
public class TaskEditIntegrationTest {

    @Autowired
    private Metadata metadata;

    @MockBean
    private DataManager dataManager;

    @Autowired
    private ScreenBuilders screenBuilders;

    @Test
    @DisplayName("Check the computation of the least busy user")
    protected void openTaskEdit(Screens screens) {
        List<KeyValueEntity> entities = generateTestData();

        Mockito.when(dataManager.loadValues(any(ValueLoadContext.class))).thenReturn(entities);

        TaskEdit taskEdit = screenBuilders.editor(Task.class, screens.getOpenedScreens().getRootScreen())
                .withScreenClass(TaskEdit.class)
                .newEntity()
                .show();

        Assertions.assertEquals(entities.get(0).getValue("user"), taskEdit.getEditedEntity().getAssignee());
    }

    private List<KeyValueEntity> generateTestData() {
        User user1 = metadata.create(User.class);
        user1.setUsername("user1");
        User user2 = metadata.create(User.class);
        user2.setUsername("user2");

        KeyValueEntity entity1 = metadata.create(KeyValueEntity.class);
        entity1.setValue("user", user1);
        entity1.setValue("estimatedEfforts", 1L);

        KeyValueEntity entity2 = metadata.create(KeyValueEntity.class);
        entity2.setValue("user", user2);
        entity2.setValue("estimatedEfforts", 2L);

        return List.of(entity1, entity2);
    }
}

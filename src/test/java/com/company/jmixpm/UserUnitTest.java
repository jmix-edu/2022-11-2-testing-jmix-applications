package com.company.jmixpm;

import com.company.jmixpm.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserUnitTest {

    @Test
    @DisplayName("Checks the computation of the instance name")
    void testInstanceName() {
        User user = new User();
        user.setUsername("ivanov_i");
        user.setLastName("Ivanov");
        user.setFirstName("Ivan");

        Assertions.assertEquals("Ivan Ivanov [ivanov_i]", user.getDisplayName());
    }
}

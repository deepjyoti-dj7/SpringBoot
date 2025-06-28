package com.dj.journalApp.service;

import com.dj.journalApp.entity.User;
import com.dj.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

    @ParameterizedTest
    @CsvSource({
            "DJ1",
            "DJ2",
            "DJ3",
            "DJ7",
            "DJ77",
            "DJ100"
    })
    public void test(String name) {
        assertNotNull(userRepository.findByUserName(name),"Failed for " + name);
    }
}

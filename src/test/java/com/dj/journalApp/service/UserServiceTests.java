package com.dj.journalApp.service;

import com.dj.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTests {

    private UserRepository userRepository;

    @Test
    public void testFindByUserName() {
        assertEquals(4, 2+2);
        assertNotNull(userRepository.findByUserName("DJ1"));
    }
}

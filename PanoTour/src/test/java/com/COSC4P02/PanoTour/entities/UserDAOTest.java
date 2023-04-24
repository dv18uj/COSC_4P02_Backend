package com.COSC4P02.PanoTour.entities;

import  org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDAOTest
{
    @Autowired
    private UserDAO underTest;

    @Test
    void addUser()
    {
        // given
        User user = new User("JohnSmith", "12345", "USER");

        // when
        boolean success = underTest.addUser(user);

        // then
        assertThat(success).isTrue();
    }

//    @Test
//    void getUserByUid()
//    {
//    }
//
//    @Test
//    void getUserByName()
//    {
//    }
//
//    @Test
//    void deleteUser()
//    {
//    }
//
//    @Test
//    void getAllUsers()
//    {
//    }
}
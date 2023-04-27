package com.COSC4P02.PanoTour.entities;


import  org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(UserDAO.class)
@DataJpaTest
public class UserDAOTest
{
    @Autowired
    private UserDAO underTest;

    @Test
    void canAddUser()
    {
        System.out.println("Testing addUser");
        // given
        User user = new User();
        user.setName("TestName");
        user.setPassword("Password");
        user.setRole("USER");

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
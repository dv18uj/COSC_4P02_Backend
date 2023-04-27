package com.COSC4P02.PanoTour.entities;

import com.COSC4P02.PanoTour.PanoTourApplication;
import com.COSC4P02.PanoTour.entities.User;
import com.COSC4P02.PanoTour.entities.UserDAO;
import  org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


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
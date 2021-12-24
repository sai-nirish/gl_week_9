package com.example.greatlearning.serviceImpl;

import com.example.greatlearning.entity.Users;
import com.example.greatlearning.repository.UsersRepository;
import com.example.greatlearning.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceImplTest {

    List<Users> users = new ArrayList<>();

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl userServiceImplTest;

    @BeforeEach
    public void setUp(){
        Users user1 = new Users(1,"john","123",true, "ROLE_USER");
        Users user2 = new Users(2,"jane","123",true, "ROLE_USER");
        users.add(user1);
        users.add(user2);
    }

    @Test
    @DisplayName(value = "Test adding user")
    public void testAddUser(){
        Users user = Users.builder().id(3).username("john").password("123").enabled(true).role("ROLE_USER").build();
        Mockito.when(usersRepository.save(user)).thenReturn(user);
        userServiceImplTest.addUser(user);
        Mockito.verify(usersRepository).save(user);
    }

    @Test
    @DisplayName(value = "Test get by user id")
    public void testGetUsersById(){
        Mockito.when(usersRepository.findById(1)).thenReturn(Optional.ofNullable(users.get(0)));
        Optional<Users> user = userServiceImplTest.getUsersById(1);
        Mockito.verify(usersRepository).findById(1);
        assertEquals(users.get(0), user.get());
    }

    @Test
    @DisplayName(value = "Test delete by user id")
    public void testDeleteUsersById(){
        userServiceImplTest.deleteUsersById(1);
        Mockito.verify(usersRepository).deleteById(1);
    }

    @Test
    @DisplayName(value = "Test get all users")
    public void testGetAllUsers(){
        Mockito.when(usersRepository.findAll()).thenReturn(users);
        List<Users> testUsers = userServiceImplTest.getAllUsers();
        Mockito.verify(usersRepository).findAll();
        assertEquals(users.size(), testUsers.size());
    }

    @Test
    @DisplayName(value = "Test update user when user exists")
    public void testUpdateUser(){
        Mockito.when(usersRepository.existsById(1)).thenReturn(true);
        Mockito.when(usersRepository.save(users.get(0))).thenReturn(users.get(0));
        userServiceImplTest.updateUser(users.get(0));
        Mockito.verify(usersRepository).existsById(1);
        Mockito.verify(usersRepository).save(users.get(0));
    }

    @Test
    @DisplayName(value = "Test update user when user doesn't exist")
    public void testUpdateUserWhenNotExists(){
        Mockito.when(usersRepository.existsById(1)).thenReturn(false);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userServiceImplTest.updateUser(users.get(0)));
        Mockito.verify(usersRepository).existsById(1);
        assertEquals("There is not user with id 1", ex.getMessage());
    }
}

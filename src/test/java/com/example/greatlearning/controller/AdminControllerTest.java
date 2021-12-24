package com.example.greatlearning.controller;

import com.example.greatlearning.entity.Users;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminControllerTest extends BaseControllerTest {


    private String ADMIN_URI = "/admin";


    private List<Users> users = new ArrayList<>();

    private Users testUser;


    @BeforeAll
    public void setUp() {
        users.add(Users.builder().id(1).username("john").password("afdf").role("USER").build());
        users.add(Users.builder().id(2).username("jane").password("afdf").role("USER").build());
        testUser = Users.builder().id(3).username("doe").password("afdf").role("USER").build();
    }

    @Test
    @WithMockUser()
    @DisplayName(value = "A user cannot get all users")
    public void test_user_getAllUsers() throws Exception {
        final String uri = ADMIN_URI + "/users";

        String json = mapper.writeValueAsString(users);
        Mockito.when(usersService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get(uri)).
                andExpect(status().is(403));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName(value = "An admin can get all users")
    public void test_getAllUsers() throws Exception {
        final String uri = ADMIN_URI + "/users";

        String json = mapper.writeValueAsString(users);
        Mockito.when(usersService.getAllUsers()).thenReturn(users);
        MvcResult mvcResult = mockMvc.perform(get(uri)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(2))).andReturn();
        Mockito.verify(usersService).getAllUsers();
        assertEquals(json, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName(value = "An admin can add user")
    public void testAddUser() throws Exception {
        final String uri = ADMIN_URI + "/addUser";
        MultiValueMap<String, String> params = convert(testUser);
        MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").params(params)).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(usersService).addUser(testUser);
        assertEquals("User Added", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName(value = "An admin can delete user")
    public void testDeleteUser() throws Exception {
        final String uri = ADMIN_URI + "/deleteUserByid";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "1");
        MvcResult mvcResult = mockMvc.perform(delete(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").params(params)).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(usersService).deleteUsersById(1);
        assertEquals("User Deleted", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName(value = "An admin can get user by id")
    public void testGetUserById() throws Exception {
        final String uri = ADMIN_URI + "/getUserById";
        String json = mapper.writeValueAsString(testUser);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "3");
        Mockito.when(usersService.getUsersById(3)).thenReturn(Optional.ofNullable(testUser));
        MvcResult mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").params(params)).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(usersService).getUsersById(3);
        assertEquals(json, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName(value = "An admin can get all bills today")
    public void testGetAllBillsToday() throws Exception {
        final String uri = ADMIN_URI + "/getAllBillsToday";
        Mockito.when(auditService.getAllBillsToday()).thenReturn(new ArrayList<>());
        MvcResult mvcResult = mockMvc.perform(get(uri)).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(auditService).getAllBillsToday();
        assertEquals("[]", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName(value = "An admin can total sales in a month")
    public void testGetSalesInAMonth() throws Exception {
        final String uri = ADMIN_URI + "/getTotalSaleInMonth";
        Mockito.when(auditService.getTotalSalesForMonth()).thenReturn(100);
        MvcResult mvcResult = mockMvc.perform(get(uri)).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(auditService).getTotalSalesForMonth();
        assertEquals("100", mvcResult.getResponse().getContentAsString());
    }
}

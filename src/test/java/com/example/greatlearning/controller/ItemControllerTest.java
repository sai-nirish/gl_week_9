package com.example.greatlearning.controller;

import com.example.greatlearning.entity.Items;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemControllerTest extends BaseControllerTest{

    private String USER_URI = "/users";

    private List<Items> items = new ArrayList<>();

    @BeforeAll
    public void setUp(){
        items.add(Items.builder().id(1).name("tea").price(100).build());
        items.add(Items.builder().id(1).name("coffee").price(150).build());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Get all items")
    public void testGetAllItems() throws Exception {
        final String uri = USER_URI + "/items";

        String json = mapper.writeValueAsString(items);
        Mockito.when(itemsService.getAllItems()).thenReturn(items);
        MvcResult mvcResult = mockMvc.perform(get(uri)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(2))).andReturn();
        Mockito.verify(itemsService).getAllItems();
        assertEquals(json,mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Get items by id")
    public void testItemsById() throws Exception {
        final String uri = USER_URI + "/items/1";
        String json = mapper.writeValueAsString(items.get(0));
        Mockito.when(itemsService.getItemById(1)).thenReturn(Optional.ofNullable(items.get(0)));
        MvcResult mvcResult = mockMvc.perform(get(uri)).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(itemsService).getItemById(1);
        assertEquals(json,mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Get items by ids")
    public void testItemsByIds() throws Exception {
        final String uri = USER_URI + "/items/ids";
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        String json = mapper.writeValueAsString(items);
        Mockito.when(itemsService.getItemsByIds(ids)).thenReturn(items);
        MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(String.valueOf(ids))).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(itemsService).getItemsByIds(ids);
        assertEquals(json,mvcResult.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Get Bill by ids")
    public void testBillByIds() throws Exception {
        final String uri = USER_URI + "/items/bill";
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        Mockito.when(itemsService.getAggregatePrice(ids)).thenReturn(100);
        MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(String.valueOf(ids))).
                andExpect(status().isOk()).andReturn();
        Mockito.verify(itemsService).getAggregatePrice(ids);
        assertEquals("100",mvcResult.getResponse().getContentAsString());
    }

}

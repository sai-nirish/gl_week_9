package com.example.greatlearning.serviceImpl;

import com.example.greatlearning.config.AspectConfig;
import com.example.greatlearning.entity.Items;
import com.example.greatlearning.repository.ItemsRepository;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// activate aspect
@Import({AnnotationAwareAspectJAutoProxyCreator.class, AspectConfig.class})
public class ItemServiceImplTest {

    @Mock
    ItemsRepository itemsRepository;

    @InjectMocks
    ItemsServiceImpl itemsService;

    private List<Items> items = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        items.add(Items.builder().id(1).name("tea").price(100).build());
        items.add(Items.builder().id(1).name("coffee").price(150).build());
    }

    @Test
    @DisplayName(value = "Test get item by id")
    public void testGetItemById(){
        Mockito.when(itemsRepository.findById(1)).thenReturn(Optional.ofNullable(items.get(0)));
        Optional<Items> testItem = itemsService.getItemById(1);
        Mockito.verify(itemsRepository).findById(1);
        assertEquals(items.get(0), testItem.get());
    }

    @Test
    @DisplayName(value = "Test get item by ids")
    public void testGetItemByIds(){
        List<Integer> ids = new ArrayList<>(Arrays.asList(1,2));
        Mockito.when(itemsRepository.findAllById(ids)).thenReturn(items);
        List<Items> testItems = itemsService.getItemsByIds(ids);
        Mockito.verify(itemsRepository).findAllById(ids);
        assertEquals(items, testItems);
    }

    @Test
    @DisplayName(value = "Test get all items")
    public void testGetAllItems(){
        Mockito.when(itemsRepository.findAll()).thenReturn(items);
        List<Items> testItems = itemsService.getAllItems();
        Mockito.verify(itemsRepository).findAll();
        assertEquals(items, testItems);
    }

    @Test
    @DisplayName(value = "Test aggregate price of items when items exists")
    public void testAggregatePrice(){
        List<Integer> ids = new ArrayList<>(Arrays.asList(1,2));
        Mockito.when(itemsRepository.findAllById(ids)).thenReturn(items);
        Integer total = itemsService.getAggregatePrice(ids);
        assertEquals(250, total);
    }

    @Test
    @DisplayName(value = "Test aggregate price of items when items don't exist")
    public void testAggregatePrice_whenException(){
        List<Integer> ids = new ArrayList<>(Arrays.asList(3,4));
        Mockito.when(itemsRepository.findAllById(ids)).thenReturn(new ArrayList<>());
        RuntimeException exception = assertThrows(IllegalArgumentException.class, () -> itemsService.getAggregatePrice(ids));
        assertEquals("User ids not present", exception.getMessage());
    }
}

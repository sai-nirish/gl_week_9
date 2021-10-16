package com.example.greatlearning.controller;

import com.example.greatlearning.entity.Items;
import com.example.greatlearning.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    ItemService itemsService;

    @GetMapping("/items")
    @ResponseBody
    public List<Items> getAllItems() {
        List<Items> items = itemsService.getAllItems();
        log.info(String.valueOf(items));
        return items;
    }

    @GetMapping("/items/{id}")
    @ResponseBody
    public Optional<Items> getItemById(@PathVariable Integer id) {
        return itemsService.getItemById(id);
    }

    @PostMapping("/items/ids")
    @ResponseBody
    public List<Items> getItemsByIds(@RequestBody  List<Integer> ids) {
        return itemsService.getItemsByIds(ids);
    }

    @PostMapping("/items/bill")
    @ResponseBody
    public Integer getBillByItems(@RequestBody List<Integer> id) {
        return itemsService.getAggregatePrice(id);
    }
}

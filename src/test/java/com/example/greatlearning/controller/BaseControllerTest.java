package com.example.greatlearning.controller;

import com.example.greatlearning.service.AuditService;
import com.example.greatlearning.service.ItemService;
import com.example.greatlearning.service.UsersService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.sql.DataSource;
import java.util.Map;

public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected DataSource dataSource;

    @MockBean
    protected UsersService usersService;

    @MockBean
    protected AuditService auditService;

    @MockBean
    protected ItemService itemsService;

    protected static ObjectMapper mapper = new ObjectMapper();

    MultiValueMap<String, String> convert(Object obj) {
        MultiValueMap parameters = new LinkedMultiValueMap<String, String>();
        Map<String, String> maps = mapper.convertValue(obj, new TypeReference<Map<String, String>>() {});
        parameters.setAll(maps);

        return parameters;
    }
}

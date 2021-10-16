package com.example.greatlearning.controller;

import com.example.greatlearning.entity.AuditLog;
import com.example.greatlearning.entity.Users;
import com.example.greatlearning.service.AuditService;
import com.example.greatlearning.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UsersService usersService;

    @Autowired
    AuditService auditService;

    @GetMapping("/users")
    @ResponseBody
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUser(Users user) {
        usersService.addUser(user);
        return "User Added";
    }

    @PutMapping("/updateUser")
    public String updateUser(Users users) {
        usersService.updateUser(users);
        return "User updated";
    }

    @GetMapping("/getUserById")
    public Optional<Users> getUserById(int id) {
        return usersService.getUsersById(id);
    }

    @DeleteMapping("/deleteUserByid")
    public String deleteUserByid(int id) {
        usersService.deleteUsersById(id);
        return "User Deleted";
    }


    @GetMapping("/getAllBillsToday")
    public List<AuditLog> getAllBillsToday(){
        return auditService.getAllBillsToday();
    }

    @GetMapping("/getTotalSaleInMonth")
    public Integer getTotalSaleInMonth(){
        return auditService.getTotalSalesForMonth();
    }
}

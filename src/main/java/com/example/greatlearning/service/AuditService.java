package com.example.greatlearning.service;

import com.example.greatlearning.entity.AuditLog;

import java.util.List;

public interface AuditService {

    List<AuditLog> getAllBillsToday();

    Integer getTotalSalesForMonth();
}

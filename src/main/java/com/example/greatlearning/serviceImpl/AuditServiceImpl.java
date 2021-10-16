package com.example.greatlearning.serviceImpl;

import com.example.greatlearning.entity.AuditLog;
import com.example.greatlearning.repository.AuditRepository;
import com.example.greatlearning.service.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {

    @Autowired
    AuditRepository auditRepository;

    @Override
    public List<AuditLog> getAllBillsToday() {
        Date currentDate = new Date();
        return auditRepository.getTotalBillsOnDate(currentDate);
    }

    @Override
    public Integer getTotalSalesForMonth() {
        LocalDate todaydate = LocalDate.now();
        Date currentDate = new Date();
        todaydate = todaydate.withDayOfMonth(1);
        Date fromDate = java.sql.Date.valueOf(todaydate);
        return auditRepository.getTotalSalesBetweenDates(fromDate, currentDate);
    }
}

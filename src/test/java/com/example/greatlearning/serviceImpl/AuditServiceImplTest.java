package com.example.greatlearning.serviceImpl;

import com.example.greatlearning.entity.AuditLog;
import com.example.greatlearning.repository.AuditRepository;
import com.example.greatlearning.utils.DateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuditServiceImplTest {

    @Mock
    AuditRepository auditRepository;

    @Mock
    DateFactory dateFactory;

    @InjectMocks
    AuditServiceImpl auditService;

    List<AuditLog> auditLogList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        auditLogList.add(AuditLog.builder().description("test").
                createDate(new Date()).billValue(1500).id(1).error(null).build());
    }

    @Test
    @DisplayName(value = "Test get all bills today")
    public void testGetAllBillsToday() {
        Date currentDate = new Date();
        Mockito.when(dateFactory.getCurrentDate()).thenReturn(currentDate);
        Mockito.when(auditRepository.getTotalBillsOnDate(currentDate)).thenReturn(auditLogList);
        List<AuditLog> auditLogs = auditService.getAllBillsToday();
        Mockito.verify(auditRepository).getTotalBillsOnDate(currentDate);
        assertEquals(auditLogList, auditLogs);
    }

    @Test
    @DisplayName(value = "Test get total sales for month")
    public void testTotalSalesForAMonth() {
        Date currentDate = new Date();
        Mockito.when(dateFactory.getCurrentDate()).thenReturn(currentDate);
        LocalDate localDate = LocalDate.now();
        localDate = localDate.withDayOfMonth(1);
        Date fromDate = dateFactory.getDateFromLocalDate(localDate);
        Mockito.when(dateFactory.getCurrentDateInLocal()).thenReturn(localDate);
        Mockito.when(dateFactory.getDateFromLocalDate(localDate)).thenReturn(fromDate);
        Mockito.when(auditRepository.getTotalSalesBetweenDates(fromDate,currentDate)).thenReturn(100);
        Integer total = auditService.getTotalSalesForMonth();
        Mockito.verify(auditRepository).getTotalSalesBetweenDates(fromDate,currentDate);
        assertEquals(100,total);
    }

}

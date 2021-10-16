package com.example.greatlearning.repository;

import com.example.greatlearning.entity.AuditLog;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer> {

    @Query("SELECT a FROM AuditLog a where a.createDate =:date")
    List<AuditLog> getTotalBillsOnDate(@Param("date") Date date);

    @Query("SELECT sum(a.billValue) FROM AuditLog a where a.createDate >= :fromDate and a.createDate <= :toDate")
    Integer getTotalSalesBetweenDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}

package com.example.greatlearning.utils;

import java.time.LocalDate;
import java.util.Date;

public class DateFactory {

    public Date getCurrentDate(){
       return new Date();
    }

    public LocalDate getCurrentDateInLocal(){
       return LocalDate.now();
    }

    public Date getDateFromLocalDate(LocalDate date){
        return java.sql.Date.valueOf(date);
    }
}

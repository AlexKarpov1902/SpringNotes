/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karpov.app.service;

import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.WEEKS;
import static java.time.temporal.ChronoUnit.YEARS;

import org.springframework.stereotype.Component;


@Component
public class TimePass {
     /**
     * Выводит прошедшее время в виде строки в годах, месяцах и т.д.по настоящее время
     * @param dateFrom с какого момента считать
     * @return прошедшее время
     */
    public static String getTimePass(LocalDateTime dateFrom) {
      return TimePass.getTimePass(dateFrom, LocalDateTime.now());
    }
    /**
     * Выводит прошедшее время в виде строки в годах, месяцах и т.д.
     *
     * @param dateFrom с какого момента считать
     * @param dateTo  до какого момента
     * @return прошедшее время
     */
    public static String getTimePass(LocalDateTime dateFrom, LocalDateTime dateTo) {
        LocalDateTime dateNow = LocalDateTime.now();
        long diff;
        diff=dateFrom.until(dateNow,YEARS);
        if (diff > 0) 
           { return String.valueOf(diff)+" г."; }
        diff=dateFrom.until(dateNow,MONTHS);
        if (diff > 0) 
           { return String.valueOf(diff)+" мес."; }        
        diff=dateFrom.until(dateNow,WEEKS);
        if (diff > 0) 
           { return String.valueOf(diff)+" нед."; }                
        diff=dateFrom.until(dateNow,DAYS);
        if (diff > 0) 
           { return String.valueOf(diff)+" дн."; }                
        diff=dateFrom.until(dateNow,HOURS);
        if (diff > 0) 
           { return String.valueOf(diff)+" час."; }        
        diff=dateFrom.until(dateNow,MINUTES);
        if (diff > 0) 
           { return String.valueOf(diff)+" мин."; }        
        return " 0 мин.";
    }

}

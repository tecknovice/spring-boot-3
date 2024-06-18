package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("contructor:" + getClass().getSimpleName());
    }

    //define our init method
    @Override
    public String getDailyWorkout() {
        return "CricketCoach getDailyWorkout";
    }
}

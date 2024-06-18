package com.luv2code.springcoredemo.common;

//do not use @Component
public class SwimCoach implements Coach{

    public SwimCoach(){
        System.out.println("contructor:" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "SwimCoach getDailyWorkout";
    }
}

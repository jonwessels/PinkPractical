package com.example.pinkpractical.util;

import com.example.pinkpractical.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeChecker
{
    public Integer currentAge(PersonEntity person)
    {
        return Period.between(person.getBirthDate(), LocalDate.now()).getYears();
    }
}

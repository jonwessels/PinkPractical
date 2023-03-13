package com.example.pinkpractical.controller;

import com.example.pinkpractical.entity.PersonEntity;
import com.example.pinkpractical.repository.PersonRepository;
import com.example.pinkpractical.util.AgeChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    PersonRepository personRepository;

    @Autowired
    AgeChecker ageChecker;

    @GetMapping("/partner_and_children")
    public List<PersonEntity> findWherePartnerAndThreeChildren()
    {
        return null;
    }
}

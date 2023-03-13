package com.example.pinkpractical.controller;

import com.example.pinkpractical.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    PersonRepository personRepository;
}

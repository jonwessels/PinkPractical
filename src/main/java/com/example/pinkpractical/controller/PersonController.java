package com.example.pinkpractical.controller;

import com.example.pinkpractical.entity.PersonEntity;
import com.example.pinkpractical.repository.PersonRepository;
import com.example.pinkpractical.util.ChildChecker;
import com.example.pinkpractical.util.CsvGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    PersonRepository personRepository;

    @Autowired
    ChildChecker childChecker;

    @Autowired
    CsvGenerator csvGenerator;

    @GetMapping(value = "/partner_and_children")
    public String findWherePartnerAndThreeChildren()
    {
        List<PersonEntity> filterList = new ArrayList<>();

        for(PersonEntity person : personRepository.findAll())
        {
            //Won't check if current partner, as child count will be 0 if no partner
            if(childChecker.childrenWithCurrentPartner(person) == 3 //# of children should be variable input
                    && childChecker.hasChildUnder18WithPartner(person))
            {
                filterList.add(person);
            }
        }

        try
        {
            return csvGenerator.csvEncode(filterList);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        try
        {
            PersonEntity newPerson = personRepository.save(new PersonEntity(person));
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

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
import java.util.Optional;

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
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person)
    {
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

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable("id") long id,@RequestBody PersonEntity person)
    {
        Optional<PersonEntity> personData = personRepository.findById(id);

        //Assuming parents won't change
        if(personData.isPresent())
        {
            PersonEntity updatePerson = personData.get();
            updatePerson.setName(person.getName());
            updatePerson.setBirthDate(person.getBirthDate());
            updatePerson.setPartner(person.getPartner());

            return new ResponseEntity<>(personRepository.save(updatePerson), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        try
        {
            personRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

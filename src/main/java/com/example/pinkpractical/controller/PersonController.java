package com.example.pinkpractical.controller;

import com.example.pinkpractical.entity.PersonEntity;
import com.example.pinkpractical.repository.PersonRepository;
import com.example.pinkpractical.util.AgeChecker;
import com.example.pinkpractical.util.ChildChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    PersonRepository personRepository;

    @Autowired
    AgeChecker ageChecker;

    @Autowired
    ChildChecker childChecker;

    @GetMapping("/partner_and_children")
    public List<PersonEntity> findWherePartnerAndThreeChildren()
    {
        List<PersonEntity> returnList = new ArrayList<>();

        for(PersonEntity person : personRepository.findAll())
        {
            //Won't check if current partner, as child count will be 0 if no partner
            if(childChecker.childrenWithCurrentPartner(person) == 3) //# of children should be variable input
            {
                //Check if one of the children is under 18
                for(PersonEntity child : person.getChildren())
                {
                    if(ageChecker.currentAge(child) < 18) //Age should be variable input
                    {
                        if(returnList.contains(person))
                        {
                            returnList.add(person);
                        }
                    }
                }
            }
        }

        return returnList;
    }
}

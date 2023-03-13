package com.example.pinkpractical.controller;

import com.example.pinkpractical.entity.PersonEntity;
import com.example.pinkpractical.repository.PersonRepository;
import com.example.pinkpractical.util.ChildChecker;
import com.example.pinkpractical.util.CsvGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/partner_and_children", produces = "text/csv")
    public void findWherePartnerAndThreeChildren(HttpServletResponse response)
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

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"persons.csv\"");

        try
        {
            csvGenerator.writePersonsToCsv(filterList, response.getWriter());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

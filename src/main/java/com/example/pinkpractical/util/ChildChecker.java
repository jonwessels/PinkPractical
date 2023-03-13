package com.example.pinkpractical.util;

import com.example.pinkpractical.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class ChildChecker
{
    public Integer childrenWithCurrentPartner(PersonEntity person)
    {
        Integer children = 0;

        //With no partner, cannot have any children they share
        if(person.getPartner() == null)
        {
            return 0;
        }

        //Go through all the children they have and count those with current partner
        for(PersonEntity child : person.getChildren())
        {
            if(child.getParent1() == person.getPartner() || child.getParent2() == person.getPartner())
            {
                children++;
            }
        }

        return children;
    }
}

package com.example.pinkpractical.util;

import com.example.pinkpractical.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

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
            if(childIsWithPartner(person, child))
            {
                children++;
            }
        }

        return children;
    }

    public Boolean childIsWithPartner(PersonEntity parent, PersonEntity child)
    {
        if(parent.getPartner() == null)
        {
            return false;
        }
        else if(child.getParent1() == parent.getPartner() || child.getParent2() == parent.getPartner())
        {
            return true;
        }

        return false;
    }

    public Integer currentAge(PersonEntity person)
    {
        return Period.between(person.getBirthDate(), LocalDate.now()).getYears();
    }

    public Boolean hasChildUnder18WithPartner(PersonEntity person)
    {
        for(PersonEntity child : person.getChildren())
        {
            if(childIsWithPartner(person, child))
            {
                if(currentAge(child) < 18) //Age should be a variable input
                {
                    return true;
                }
            }
        }

        return false;
    }
}

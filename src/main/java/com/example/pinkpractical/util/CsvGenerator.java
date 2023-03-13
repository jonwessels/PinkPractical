package com.example.pinkpractical.util;

import com.example.pinkpractical.entity.PersonEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvGenerator
{
    public void writePersonsToCsv(List<PersonEntity> persons, Writer writer)
    {
        try
        {
            CSVPrinter printer =  new CSVPrinter(writer, CSVFormat.DEFAULT);

            //Writing headers, excluding children
            printer.printRecord("ID", "Name", "Birth Date", "Parent 1 ID", "Parent 2 ID", "Partner ID");
            for(PersonEntity person : persons)
            {
                printer.printRecord(person.getId(),person.getName(),
                        person.getBirthDate(),person.getParent1().getId(),
                        person.getParent2().getId(),person.getPartner().getId());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

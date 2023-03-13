package com.example.pinkpractical.util;

import com.example.pinkpractical.entity.PersonEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;
import java.util.List;

@Component
public class CsvGenerator
{

    final String[] HEADERS = { "ID", "Name", "Birth Date", "Parent 1 ID", "Parent 2 ID", "Partner ID"};

    public String csvEncode(List<PersonEntity> persons) throws IOException
    {
        StringWriter sw =  new StringWriter();
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).build();

        final CSVPrinter printer = new CSVPrinter(sw, csvFormat);

        for(PersonEntity person : persons)
        {
            printer.printRecord(person.getId(),person.getName(),
                    person.getBirthDate(),person.getParent1().getId(),
                    person.getParent2().getId(),person.getPartner().getId());
        }

        return Base64.getEncoder().encodeToString(sw.toString().getBytes());
    }
}

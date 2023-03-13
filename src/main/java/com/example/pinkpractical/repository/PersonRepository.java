package com.example.pinkpractical.repository;

import com.example.pinkpractical.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//Mapping will go to /personEntites/search for now
public interface PersonRepository extends JpaRepository<PersonEntity, Long>
{
    List<PersonEntity> findByOrderByName();

    List<PersonEntity> findByOrderById();
}
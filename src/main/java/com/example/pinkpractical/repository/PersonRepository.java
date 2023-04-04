package com.example.pinkpractical.repository;

import com.example.pinkpractical.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface PersonRepository extends JpaRepository<PersonEntity, Long>
{
    List<PersonEntity> findByOrderByNameAsc();

    List<PersonEntity> findByOrderByIdAsc();
}
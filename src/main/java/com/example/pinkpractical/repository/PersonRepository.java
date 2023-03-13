package com.example.pinkpractical.repository;

import com.example.pinkpractical.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>
{

}
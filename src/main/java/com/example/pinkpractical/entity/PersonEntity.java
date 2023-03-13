package com.example.pinkpractical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PersonEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "parent_1_id",nullable = false)
    private PersonEntity parent1;

    @ManyToOne
    @JoinColumn(name = "parent_2_id",nullable = false)
    private PersonEntity parent2;

    @OneToMany
    @JoinColumn(name = "id")
    private List<PersonEntity> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private PersonEntity partner;
}
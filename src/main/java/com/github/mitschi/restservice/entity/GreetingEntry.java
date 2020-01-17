package com.github.mitschi.restservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "greetingEntryIdSequence",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "greetingEntryIdSequence"
)
@Data // From project lombok, creates all necessary setter/getter/default constructor...
@NoArgsConstructor
@AllArgsConstructor //add constructors via lombok
@EqualsAndHashCode
public class GreetingEntry {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "greetingEntryIdSequence"
    )
    private Long id;

    @Column(
            columnDefinition = "TEXT"
    ) //To make sure that there are no conflicts with encoding and stuff
    private String name;

}

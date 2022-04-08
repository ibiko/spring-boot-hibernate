package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq_id")
    @SequenceGenerator(allocationSize = 5, name = "property_seq_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private String value;
}

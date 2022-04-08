package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleProperty {

    @Id
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Include
    private Role role;

    @Id
    @ManyToOne(optional = false)
    @EqualsAndHashCode.Include
    private Property property;

    @Column(nullable = false)
    private Integer sorting;
}

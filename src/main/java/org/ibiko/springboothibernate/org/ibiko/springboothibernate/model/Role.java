package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_id")
    @SequenceGenerator(allocationSize = 5, name = "role_seq_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private String label;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleProperty> rolePropertyList = new ArrayList<>();
}

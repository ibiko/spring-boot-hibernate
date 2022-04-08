package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq_id")
    @SequenceGenerator(allocationSize = 5, name = "property_seq_id")
    private Integer id;

    @Column(nullable = false)
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Property property = (Property) o;
        return id != null && Objects.equals(id, property.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

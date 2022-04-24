package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(RolePropertyPk.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RoleProperty implements Serializable {

    @Id
    @ManyToOne(optional = false)
    private Role role;

    @Id
    @ManyToOne(optional = false)
    private Property property;

    @Column(nullable = false)
    private Integer sorting;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleProperty that = (RoleProperty) o;
        return role != null && Objects.equals(role, that.role)
                && property != null && Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, property);
    }
}

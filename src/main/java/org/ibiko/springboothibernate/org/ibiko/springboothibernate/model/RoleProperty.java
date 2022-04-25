package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@IdClass(RolePropertyPk.class)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table
public class RoleProperty implements Serializable {

    @Id
    @ManyToOne(optional = false)
    private Role role;

    @Id
    //@ManyToOne(optional = false)
    @Column(nullable = false)
    private UUID propertyId;

    @Column(nullable = false)
    private Integer sorting;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleProperty that = (RoleProperty) o;
        return role != null && Objects.equals(role, that.role)
                && propertyId != null && Objects.equals(propertyId, that.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, propertyId);
    }
}

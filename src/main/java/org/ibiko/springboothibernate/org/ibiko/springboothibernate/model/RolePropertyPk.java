package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class RolePropertyPk implements Serializable {

    private Integer role;
    private UUID propertyId;
}

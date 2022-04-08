package org.ibiko.springboothibernate.org.ibiko.springboothibernate.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePropertyPk implements Serializable {

    private Integer role;
    private Integer property;
}

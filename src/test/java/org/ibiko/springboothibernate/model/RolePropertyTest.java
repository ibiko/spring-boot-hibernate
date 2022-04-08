package org.ibiko.springboothibernate.model;

import org.ibiko.springboothibernate.org.ibiko.springboothibernate.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class RolePropertyTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    void testAddAllToList(){
        //setup
        Property property = createProperty("value");
        Role role = createRole("label");
        RoleProperty roleProperty = createRoleProperty(property, role, 1);
        role.getRolePropertyList().add(roleProperty);

        this.roleRepository.saveAndFlush(role);

        //execute

        Role roleFromRepository = this.roleRepository.getById(role.getId());

        Property incomingPropertyFromDto = createProperty("valueNew");
        incomingPropertyFromDto.setId(property.getId());
        RoleProperty rolePropertyNewFromDto = createRoleProperty(incomingPropertyFromDto, roleFromRepository, 2);
        roleFromRepository.getRolePropertyList().add(rolePropertyNewFromDto);


        this.roleRepository.saveAndFlush(roleFromRepository);

        //assert
        assertEquals(1, roleFromRepository.getRolePropertyList().size());
        assertEquals(property.getId(), roleFromRepository.getRolePropertyList().iterator().next().getProperty().getId());
        assertEquals(2, roleFromRepository.getRolePropertyList().iterator().next().getSorting());
        assertEquals("valueNew", roleFromRepository.getRolePropertyList().iterator().next().getProperty().getValue());
    }

    private RoleProperty createRoleProperty(Property property, Role role, int sorting) {
        RoleProperty roleProperty = new RoleProperty();
        roleProperty.setRole(role);
        roleProperty.setProperty(property);
        roleProperty.setSorting(sorting);
        return roleProperty;
    }

    private Role createRole(String label) {
        Role role = new Role();
        role.setLabel(label);
        return role;
    }

    private static Property createProperty(String value) {
        Property property = new Property();
        property.setValue(value);
        return property;
    }
}

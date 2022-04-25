package org.ibiko.springboothibernate.model;

import org.ibiko.springboothibernate.org.ibiko.springboothibernate.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableJpaAuditing
@Transactional
class RolePropertyTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RoleStatusRepository roleStatusRepository;

    @Test
    void testAddAllToList(){
        //setup
        Property property = createProperty("value");
        Property property2 = createProperty("value2");
        Property property3 = createProperty("value3");

        property = this.propertyRepository.save(property);
        property2 = this.propertyRepository.save(property2);
        property3 = this.propertyRepository.save(property3);

        RoleStatus roleStatus = new RoleStatus();
        roleStatus.setLabel("roleStatus label");
        roleStatus = this.roleStatusRepository.save(roleStatus);

        Role role = createRole("label");
        role.setRoleStatus(roleStatus);
        role = this.roleRepository.save(role);

        RoleProperty roleProperty = createRoleProperty(property, role, 1);
        role.getRolePropertyList().add(roleProperty);

        RoleProperty roleProperty2 = createRoleProperty(property2, role, 7);
        role.getRolePropertyList().add(roleProperty2);


        this.roleRepository.saveAndFlush(role);


        //execute

        Role roleFromRepository = this.roleRepository.getById(role.getId());

        //Property incomingPropertyFromDto = createProperty("valueNew");
        //incomingPropertyFromDto.setId(property.getId());
        //RoleProperty rolePropertyNewFromDto = createRoleProperty(incomingPropertyFromDto, roleFromRepository, 2);
        RoleProperty rolePropertyNewFromDto2 = createRoleProperty(property2, roleFromRepository, 3);
        RoleProperty rolePropertyNewFromDto3 = createRoleProperty(property3, roleFromRepository, 4);

        roleFromRepository.getRolePropertyList().clear();
        Set<RoleProperty> rolePropertyNewFromDto1 = new HashSet<>(Arrays.asList(rolePropertyNewFromDto3, rolePropertyNewFromDto2));
        roleFromRepository.getRolePropertyList().addAll(rolePropertyNewFromDto1);


       roleFromRepository = this.roleRepository.saveAndFlush(roleFromRepository);

        //assert
        assertEquals(2, roleFromRepository.getRolePropertyList().size());
        //assertEquals(property.getId(), roleFromRepository.getRolePropertyList().iterator().next().getProperty().getId());
        assertTrue(roleFromRepository.getRolePropertyList().stream().anyMatch(r -> r.getSorting() == 3));
        assertTrue(roleFromRepository.getRolePropertyList().stream().anyMatch(r -> r.getSorting() == 4));
        assertNotNull(roleFromRepository.getCreatedAt());

        //assertEquals("valueNew", roleFromRepository.getRolePropertyList().iterator().next().getProperty().getValue());
    }

    private RoleProperty createRoleProperty(Property property, Role role, int sorting) {
        RoleProperty roleProperty = new RoleProperty();
        roleProperty.setRole(role);
        roleProperty.setPropertyId(property.getId());
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

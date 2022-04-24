package org.ibiko.springboothibernate.model;

import org.ibiko.springboothibernate.org.ibiko.springboothibernate.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableJpaAuditing
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
        Property property2 = createProperty("value2");
        property = this.propertyRepository.save(property);
        property2 = this.propertyRepository.save(property2);
        Role role = createRole("label");
        role = this.roleRepository.save(role);

        RoleProperty roleProperty = createRoleProperty(property, role, 1);
        role.getRolePropertyList().add(roleProperty);

        this.roleRepository.saveAndFlush(role);

        //execute

        Role roleFromRepository = this.roleRepository.getById(role.getId());

        Property incomingPropertyFromDto = createProperty("valueNew");
        incomingPropertyFromDto.setId(property.getId());
        RoleProperty rolePropertyNewFromDto = createRoleProperty(incomingPropertyFromDto, roleFromRepository, 2);
        RoleProperty rolePropertyNewFromDto2 = createRoleProperty(property2, roleFromRepository, 3);

        roleFromRepository.getRolePropertyList().clear();
        Set<RoleProperty> rolePropertyNewFromDto1 = new HashSet<>(Arrays.asList(rolePropertyNewFromDto, rolePropertyNewFromDto2));
        roleFromRepository.getRolePropertyList().addAll(rolePropertyNewFromDto1);


       roleFromRepository = this.roleRepository.saveAndFlush(roleFromRepository);

        //assert
        assertEquals(2, roleFromRepository.getRolePropertyList().size());
        assertEquals(property.getId(), roleFromRepository.getRolePropertyList().iterator().next().getProperty().getId());
        assertTrue(roleFromRepository.getRolePropertyList().stream().anyMatch(r -> r.getSorting() == 2));
        assertTrue(roleFromRepository.getRolePropertyList().stream().anyMatch(r -> r.getSorting() == 3));
        assertNotNull(roleFromRepository.getCreatedAt());

        //assertEquals("valueNew", roleFromRepository.getRolePropertyList().iterator().next().getProperty().getValue());
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

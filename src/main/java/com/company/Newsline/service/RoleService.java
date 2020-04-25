package com.company.Newsline.service;

import com.company.Newsline.entity.Role;
import com.company.Newsline.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleService {

    RoleRepository roleRepository;

    //gets a role by its name
    public Role findRoleByName(String roleName) {
        return roleRepository.findFirstByName(roleName);
    }

    //adds a new role to DB
    public Role save (Role role) {
        return roleRepository.save(role);
    }
}

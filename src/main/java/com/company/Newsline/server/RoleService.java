package com.company.Newsline.server;

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

    public Role findRoleByName(String roleName) {
        return roleRepository.findFirstByName(roleName);
    }

    public Role save (Role role) {
        return roleRepository.save(role);
    }
}

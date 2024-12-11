package com.reststay.user_service.service;

import com.reststay.user_service.exception.RoleNotFoundException;
import com.reststay.user_service.model.Role;
import com.reststay.user_service.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    protected Role getRoleById(Long id){
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }
}

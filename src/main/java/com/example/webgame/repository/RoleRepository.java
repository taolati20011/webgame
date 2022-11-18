package com.example.webgame.repository;

import com.example.webgame.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Modifying
    @Query(value = "INSERT INTO roles(role_id, role_name) values (?, ?)", nativeQuery = true)
    public void addRole(Long role_id, String role_name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM roles r WHERE r.role_id = ?1", nativeQuery = true)
    public void deleteRoleById(Long role_id);

    @Query(value = "SELECT * FROM roles r WHERE r.role_name = ?1", nativeQuery = true)
    Role findByRoleName(String roleName);

    @Query(value = "SELECT COUNT(r) FROM Role r WHERE r.roleId=?1")
    public int countId(Long role_id);

    @Query(value = "SELECT COUNT(r) FROM Role r WHERE r.roleName=?1")
    public int countName(String role_name);
}

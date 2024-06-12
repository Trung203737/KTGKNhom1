package com.example.KT_Giua_Ky.repository;

import com.example.KT_Giua_Ky.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<AppRole, Long>{
    @Query("SELECT r.id FROM AppRole r WHERE r.name = ?1")
    Long getRoleIdByName(String roleName);
}

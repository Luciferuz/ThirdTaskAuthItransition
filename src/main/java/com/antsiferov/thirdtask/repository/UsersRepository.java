package com.antsiferov.thirdtask.repository;

import com.antsiferov.thirdtask.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    
    Optional<Users> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users_table u SET u.last_login = ?1 WHERE u.name = ?2", nativeQuery = true)
    void setLastDate(String lastLogin, String username);
    
}
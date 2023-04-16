package com.example.webgame.repository;

import com.example.webgame.dto.UserDTO;
import com.example.webgame.dto.UserListDTO;
import com.example.webgame.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUserEmail(String email);

    @Query(value = """
            select * from user u
            where u.token = ?1 and (current_timestamp() - u.created_at) < 1000
            """, nativeQuery = true)
    User findByToken(String token);

    @Query(value = """
            select * from user u
            where u.token = ?1
            """, nativeQuery = true)
    User findByTokenLogin(String token);

    @Modifying
    @Query(value = """
            delete from user 
            where user_id = ?1 ;
            """, nativeQuery = true)
    void deleteUserById(Long userId);

    @Query("""
            select u from User u
            where u.userFullname = ?1
            """)
    List<User> findAllByFullName(String fullname);

    @Query("""
            select count(u) from User u
            where u.username = ?1
            """)
    Integer countUsername(String username);

    @Query("""
            select count(u) from User u
            where u.userEmail = ?1
            """)
    Integer countEmail(String userEmail);

    @Query("""
            select count(u) from User u
            where u.userPhone = ?1
            """)
    Integer countPhone(String userPhone);

    @Query("""
            select count(u) from User u
            where u.userId = ?1
            """)
    Integer countId(Long userId);

    @Query("""
            select u from User u
            where u.username like %?1% or u.userFullname like %?1%
            """)
    List<User> findByFilter(String words, Pageable pageable);

    @Query("""
            select u from User u
            where u.userId = ?1
            """)
    UserDTO getUserInfoById(Long id);

    @Query("""
            select count(u) from User u
            where u.username like %?1% or u.userFullname like %?1%
            """)
    Integer countNumberOfUser(String words);

    @Modifying
    @Query(value = """
            delete from role_user 
            where user_id = ?1 ;
            """, nativeQuery = true)
    Integer deleteRoleUserByUserId(Long Id);
}

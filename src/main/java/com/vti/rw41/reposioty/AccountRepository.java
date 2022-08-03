package com.vti.rw41.reposioty;

import com.vti.rw41.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

//    @Query("SELECT (count(a) = 0) from AccountEntity a where a.email = :email")
    boolean existsByEmail(String email);

    // select * from accounts where full_name like ?
    @Query("FROM AccountEntity where fullName like %:name%")
    List<AccountEntity> timAccountTheoTen(String name);

    @Query("FROM AccountEntity where fullName like %:name% OR email = :email")
    // <=>
    List<AccountEntity> findByFullNameIsContainingOrEmail(String name, String email);

    @Transactional
    @Modifying
    @Query("update AccountEntity set fullName = :name WHERE id = :id")
    int updateNameById(String name, Integer id);

}

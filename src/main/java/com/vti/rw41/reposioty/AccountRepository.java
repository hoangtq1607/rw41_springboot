package com.vti.rw41.reposioty;

import com.vti.rw41.entity.AccountEntity;
import com.vti.rw41.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    public AccountEntity saveAccount(AccountEntity accountEntity) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(accountEntity);
        session.getTransaction().commit();

        return accountEntity;
    }

    public boolean isEmailNotExists(String email) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Object notExist = session.createQuery("SELECT (count(a) = 0) from AccountEntity a where email = :email")
                .setParameter("email", email)
                .getSingleResult();
        return (boolean) notExist;
    }
}

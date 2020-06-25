package com.assj5.thien.assj5.repository.repositoryImpl;

import com.assj5.thien.assj5.model.Bill;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public interface CheckOutRepository {
    void save(Bill bill);
}

@Transactional
@Repository
class CheckOutRepositoryImpl implements CheckOutRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Bill bill) {
        Session session = entityManager.unwrap(Session.class);
        session.save(bill);
    }
}
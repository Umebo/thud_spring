package com.igniemie.thud.database.impl;

import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.model.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAO implements IGameDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addGame(Game game) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(game);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

}

package com.igniemie.thud.database.impl;

import com.igniemie.thud.database.IGameDAO;
import com.igniemie.thud.model.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

    @Override
    public Optional<Game> getGameById(String gameUUID) {
        Session session = this.sessionFactory.openSession();
        Query<Game> query = session.createQuery("FROM com.igniemie.thud.model.Game WHERE gameUUID = :gameUUID");
        query.setParameter("gameUUID", gameUUID);
        try {
            Game game = query.getSingleResult();
            session.close();
            return Optional.of(game);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Game> getGameByPlayer(String player) {
        Session session = this.sessionFactory.openSession();
        Query<Game> query = session.createQuery("FROM com.igniemie.thud.model.Game WHERE player1 = :player");
        query.setParameter("player", player);
        try {
            Game game = query.getSingleResult();
            session.close();
            return Optional.of(game);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public List<Game> getGames() {
        Session session = this.sessionFactory.openSession();
        Query<Game> query = session.createQuery("FROM com.igniemie.thud.model.Game");
        List<Game> result = query.getResultList();
        session.close();
        return result;
    }

}

package com.myejb.ejb;

import com.myejb.ejb.interfaces.GestionCDLocal;
import com.myejb.model.*;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateful
public class GestionCDBean implements GestionCDLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void ajouterCD(CD cd) {
        em.persist(cd);
    }
    @Override
    public void modifierCD(CD cd) {
        em.merge(cd);
    }
    @Override
    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) em.remove(cd);
    }


    @Override
    public List<CD> listerTousLesCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }

    @Override
    public List<Emprunt> voirEmpruntsEnCours() {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.dateRetour IS NULL", Emprunt.class)
                .getResultList();
    }

}

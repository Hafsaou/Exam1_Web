package com.myejb.ejb;

import com.myejb.model.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.myejb.ejb.interfaces.EmpruntServiceLocal;
import java.util.Date;
import java.util.List;




@Stateless
public class EmpruntServiceBean implements EmpruntServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CD> listerCDsDisponibles() {
        return em.createQuery("SELECT c FROM CD c WHERE c.disponible = TRUE", CD.class).getResultList();
    }

    @Override
    public Emprunt emprunterCD(Long cdId, Long userId) {
        CD cd = em.find(CD.class, cdId);
        User user = em.find(User.class, userId);

        if (cd != null && user != null && cd.isDisponible()) {
            cd.setDisponible(false);
            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setUser(user);
            emprunt.setDateEmprunt(new Date());
            em.persist(emprunt);
            return emprunt;
        }
        return null;
    }

    @Override
    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            CD cd = emprunt.getCd();
            cd.setDisponible(true);
            em.remove(emprunt);
        }
    }

    @Override
    public List<Emprunt> voirEmpruntsUtilisateur(Long userId) {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.user.id = :userId", Emprunt.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}


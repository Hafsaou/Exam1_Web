package com.myejb.ejb.interfaces;



import java.util.List;
import com.myejb.model.*;
import jakarta.ejb.Local;

@Local
public interface GestionCDLocal {
    void ajouterCD(CD cd);
    void modifierCD(CD cd);
    void supprimerCD(Long id);
    List<CD> listerTousLesCDs();
    public List<Emprunt> voirEmpruntsEnCours();
}

package com.myejb.ejb.interfaces;


 import com.myejb.model.*;
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface EmpruntServiceLocal {


    List<CD> listerCDsDisponibles();

    Emprunt emprunterCD(Long cdId, Long userId);


    void retournerCD(Long empruntId);


    List<Emprunt> voirEmpruntsUtilisateur(Long userId);
}

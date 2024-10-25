package com.myejb.web;
import com.myejb.ejb.interfaces.EmpruntServiceLocal;
import com.myejb.model.*;
import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class CDBean {

    @EJB
    private EmpruntServiceLocal empruntService;

    private Long selectedCDId;
    private Long userId;
    private List<CD> cdsDisponibles;
    private List<Emprunt> empruntsUtilisateur;

    public List<CD> getCdsDisponibles() {
        cdsDisponibles = empruntService.listerCDsDisponibles();
        return cdsDisponibles;
    }

    public void emprunterCD() {
        empruntService.emprunterCD(selectedCDId, userId);
    }

    public List<Emprunt> getEmpruntsUtilisateur() {
        empruntsUtilisateur = empruntService.voirEmpruntsUtilisateur(userId);
        return empruntsUtilisateur;
    }

    public void retournerCD(Long empruntId) {
        empruntService.retournerCD(empruntId);
    }

    // Getters et setters pour userId et selectedCDId
    public Long getSelectedCDId() { return selectedCDId; }
    public void setSelectedCDId(Long selectedCDId) { this.selectedCDId = selectedCDId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

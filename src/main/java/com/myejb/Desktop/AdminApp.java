package com.myejb.Desktop;



import com.myejb.ejb.interfaces.GestionCDLocal;
import com.myejb.model.*;
import jakarta.ejb.EJB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminApp extends JFrame {

    @EJB
    private GestionCDLocal gestionCD;

    private JTextField titreField;
    private JButton ajouterButton, supprimerButton;
    private JList<CD> cdList;
    private DefaultListModel<CD> cdListModel;

    public AdminApp() {
        setTitle("Gestion des CDs (Admin)");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        titreField = new JTextField(20);
        ajouterButton = new JButton("Ajouter CD");
        supprimerButton = new JButton("Supprimer CD");
        cdListModel = new DefaultListModel<>();
        cdList = new JList<>(cdListModel);

        loadCDs();

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CD newCD = new CD();
                newCD.setTitre(titreField.getText());
                gestionCD.ajouterCD(newCD);
                loadCDs();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CD selectedCD = cdList.getSelectedValue();
                if (selectedCD != null) {
                    gestionCD.supprimerCD(selectedCD.getId());
                    loadCDs();
                }
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Titre:"));
        add(titreField);
        add(ajouterButton);
        add(supprimerButton);
        add(new JScrollPane(cdList));
    }

    private void loadCDs() {
        cdListModel.clear();
        List<CD> cds = gestionCD.listerTousLesCDs();
        for (CD cd : cds) {
            cdListModel.addElement(cd);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminApp().setVisible(true));
    }
}

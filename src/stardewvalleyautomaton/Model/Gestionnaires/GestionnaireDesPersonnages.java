/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stardewvalleyautomaton.Model.Gestionnaires;

import java.util.ArrayList;
import stardewvalleyautomaton.Model.Personnages.Personnage;

/**
 *
 * @author Matthieu
 */
public class GestionnaireDesPersonnages {
    
    //DESIGN PATTERN DE LA BIBLIOTHEQUE ----------------------------------------
    private static GestionnaireDesPersonnages instance;
    private ArrayList<Personnage> listeDesPersonnages;                          //personnages déjà affichés
    private ArrayList<Personnage> listeDesNouveauxPersonnages;                  //personnages à afficher
    
    private GestionnaireDesPersonnages() {
        this.listeDesPersonnages = new ArrayList<>();
        this.listeDesNouveauxPersonnages = new ArrayList<>();
    }
    
    public static GestionnaireDesPersonnages get() {
        if(instance == null) {
            instance = new GestionnaireDesPersonnages();
        }
        return instance;
    }
    
    //GESTION DES PERSONNAGES --------- ----------------------------------------
    //ajout d'un personnage
    public static void addPersonnage(Personnage personnage) {
        get().listeDesNouveauxPersonnages.add(personnage);
    }
    
    //lister les personnages à placer
    public static ArrayList<Personnage> getListeDesNouveauxPersonnages() {
        return get().listeDesNouveauxPersonnages;
    }
    
    //lister les personnages deja placé
    public static ArrayList<Personnage> getListeDesPersonnages() {
        return get().listeDesPersonnages;
    }
    
    //VALIDATION ---------------------------------------------------------------
    //valide le placement d'un personnage
    public static void valider(Personnage personnage) {
        get().listeDesNouveauxPersonnages.remove(personnage);
        get().listeDesPersonnages.add(personnage);
    }
    
}


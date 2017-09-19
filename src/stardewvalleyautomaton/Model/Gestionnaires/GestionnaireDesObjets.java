/*
 * gestionnaire des objets
 */
package stardewvalleyautomaton.Model.Gestionnaires;

import java.util.ArrayList;
import stardewvalleyautomaton.Model.Objets.Objet;
import stardewvalleyautomaton.Model.Personnages.IA.IA;

/**
 *
 * @author simonetma
 */
public class GestionnaireDesObjets {
    
    //DESIGN PATTERN DE LA BIBLIOTHEQUE ----------------------------------------
    private static GestionnaireDesObjets instance;
    private final ArrayList<Objet> listeDesObjets;                              //liste des objets déjà affichés
    private final ArrayList<Objet> listeDesNouveauxObjets;                      //liste des objets à afficher
    private final ArrayList<Objet> listeDesAnciensObjets;                       //liste des objets à supprimer
    
    private GestionnaireDesObjets() {
        this.listeDesObjets = new ArrayList<>();
        this.listeDesNouveauxObjets = new ArrayList<>();
        this.listeDesAnciensObjets = new ArrayList<>();
    }
    
    public static GestionnaireDesObjets get() {
        if(instance == null) {
            instance = new GestionnaireDesObjets();
        }
        return instance;
    }
    
    
    //GESTION DES OBJETS -------------------------------------------------------
    //ajout d'un objet
    public static void addObjet(Objet objet) {
        get().listeDesNouveauxObjets.add(objet);
    }
    //suppression d'un objet
    public static void removeObjet(Objet objet) {
        get().listeDesAnciensObjets.add(objet);
    }
    
    //lister les objets à enlever
    public static ArrayList<Objet> getListeDesAnciensObjets() {
        return get().listeDesAnciensObjets;
    }
    
    //lister les objets à placer
    public static ArrayList<Objet> getListeDesNouveauxObjets() {
        return get().listeDesNouveauxObjets;
    }
    
    //lister les objets deja placé
    public static ArrayList<Objet> getListeDesObjets() {
        return get().listeDesObjets;
    }
    
    
    //VALIDATION ---------------------------------------------------------------
    //valide le placement d'un objet
    public static void valider(Objet objet) {
        get().listeDesNouveauxObjets.remove(objet);
        get().listeDesObjets.add(objet);
    }
    
    //valide la suppression d'un objet
    public static void devalider(Objet objet) {
        get().listeDesAnciensObjets.remove(objet);
        get().listeDesObjets.remove(objet);
    }
    
}

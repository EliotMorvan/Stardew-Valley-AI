/*
 * Case générale de la map
 */
package stardewvalleyautomaton.Model.Cases;

import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesObjets;
import stardewvalleyautomaton.Model.Objets.*;
import stardewvalleyautomaton.Model.Personnages.Personnage;

/**
 *
 * @author simonetma
 */
public abstract class Case {
    
    private final int ligne;                                                    //numéro de la ligne de la case
    private final int colonne;                                                  //numéro de la colonne de la case
    private Objet objet;                                                        //objet présent sur la case
    private Personnage personnage;                                              //personnage présent sur la case
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public Case(int _ligne, int _colonne) {
        this.ligne = _ligne;
        this.colonne = _colonne;
        this.objet = null;
        this.personnage = null;
    }
    
    //GETTER -------------------------------------------------------------------
    //renvoie la ligne de la case
    public int getLigne() {
        return this.ligne;
    }
    //renvoie la colonne de la case
    public int getColonne() {
        return this.colonne;
    }
    //renvoie l'objet présent sur la case
    public Objet getObjet() {
        return this.objet;
    }
    //renvoie le personnage présent sur la case
    public Personnage getPersonnage() {
        return this.personnage;
    }
    //renvoie le type de case
    public abstract Enum_Case getType();
    
    
    //AJOUT ET SUPPRESSION D'OBJETS --------------------------------------------
    //ajout d'un objet
    public void setObjet(Enum_Objet typeObjet) {
        switch(typeObjet) {
            case Arbre : this.objet = new Arbre(this); break;
            case Barriere : this.objet = new Barriere(this); break;
            case Oeuf : this.objet = new Oeuf(this); break;
            case Maison : this.objet = new Maison(this); break;
            case Machine_Fromage : this.objet = new Machine_Fromage(this); break;
        }
        //ajout de l'objet dans la liste des objets à afficher
        GestionnaireDesObjets.addObjet(this.objet);
    }
    //suppression d'un objet
    public void removeObjet() {
        GestionnaireDesObjets.removeObjet(this.getObjet());
        this.objet = null;
    }
    
    //AJOUT D'UN PERSONNAGE ----------------------------------------------------
    public void setPersonnage(Personnage _personnage) {
        this.personnage = _personnage;
    }
    
    //la case est elle libre ?
    public boolean estLibre() {
        boolean estLibre = false;
        if(this.objet == null && this.personnage == null) {
            estLibre = true; 
        }
        else if(this.objet != null) {
            if(this.objet.getType() == Enum_Objet.Oeuf) {
                estLibre = true;
            }
        }
        return estLibre;
    }
}

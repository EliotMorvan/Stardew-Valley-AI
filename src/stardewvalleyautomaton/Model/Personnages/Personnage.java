/*
 * Personnage de la carte
 */
package stardewvalleyautomaton.Model.Personnages;

import stardewvalleyautomaton.Model.Carte;
import stardewvalleyautomaton.Model.Cases.Case;
import stardewvalleyautomaton.Model.Personnages.IA.Enum_Action;
import stardewvalleyautomaton.Model.Personnages.IA.IA;

/**
 *
 * @author Matthieu
 */
public abstract class Personnage {
    private IA ia;                                                              //ia du personnage
    private Case saCase;                                                        //case où se trouve le personnage
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public Personnage(Case _saCase,IA _ia) {
        this.ia = _ia;
        this.ia.setPersonnage(this);
        this.saCase = _saCase;
        
    }
    
    //GETTER -------------------------------------------------------------------
    //renvoie le type du personnage
    public abstract Enum_Personnage getType();
    //renvoie la case du personnage
    public Case getCase() {
        return this.saCase;
    }
    //revnoie l'ia du personnage
    public IA getIA() {
        return this.ia;
    }
    
    //SETTER -------------------------------------------------------------------
    public void setCase(Case _case) {
        this.saCase = _case;
    }
    
    //REALISATION DES ACTIONS COMMUNES (MOUVEMENTS...) -------------------------
    public void action(Enum_Action action) {
        int ligne,colonne;
        switch(action) {
            case attendre: break;
            case moveLeft:
                ligne = this.getCase().getLigne();
                colonne = this.getCase().getColonne()-1;
                Carte.get().setPositionPersonnage(this, ligne, colonne);
                break;
            case moveRight:
                ligne = this.getCase().getLigne();
                colonne = this.getCase().getColonne()+1;
                Carte.get().setPositionPersonnage(this, ligne, colonne);
                break;
            case moveTop:
                ligne = this.getCase().getLigne()-1;
                colonne = this.getCase().getColonne();
                Carte.get().setPositionPersonnage(this, ligne, colonne);
                break;
            case moveBottom:
                ligne = this.getCase().getLigne()+1;
                colonne = this.getCase().getColonne();
                Carte.get().setPositionPersonnage(this, ligne, colonne);
                break;
            default : this.actionSpeciale(action); break;
        }
    }
    
    //REALISATION DES ACTIONS PROPRES
    public abstract void actionSpeciale(Enum_Action action);
}

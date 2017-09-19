/*
 * objet de la carte
 */
package stardewvalleyautomaton.Model.Objets;

import stardewvalleyautomaton.Model.Cases.Case;

/**
 *
 * @author Matthieu
 */
public abstract class Objet {
    private final Case Case;                                                          //case où se trouve l'objet
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public Objet(Case Case) {
        this.Case = Case;
    }
    
    //GETTER DE LA CASE --------------------------------------------------------
    public Case getCase() {
        return this.Case;
    }
    
    //GETTER DU TYPE D'OBJET ---------------------------------------------------
    public abstract Enum_Objet getType();
}

/*
 * un arbre
 */
package stardewvalleyautomaton.Model.Objets;

import stardewvalleyautomaton.Model.Cases.Case;

/**
 *
 * @author Matthieu
 */
public class Arbre extends Objet {
    
    public Arbre(Case Case) {
        super(Case);
    }

    @Override
    public Enum_Objet getType() {
        return Enum_Objet.Arbre;
    }
    
}

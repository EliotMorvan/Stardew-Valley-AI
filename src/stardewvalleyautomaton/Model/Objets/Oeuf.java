/*
 * Un oeuf
 */
package stardewvalleyautomaton.Model.Objets;

import stardewvalleyautomaton.Model.Cases.Case;

/**
 *
 * @author Matthieu
 */
public class Oeuf extends Objet {

    public Oeuf(Case Case) {
        super(Case);
    }

    @Override
    public Enum_Objet getType() {
        return Enum_Objet.Oeuf;
    }
    
}

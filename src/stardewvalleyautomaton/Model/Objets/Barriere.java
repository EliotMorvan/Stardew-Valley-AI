/*
 * une barrière
 */
package stardewvalleyautomaton.Model.Objets;

import stardewvalleyautomaton.Model.Cases.Case;

/**
 *
 * @author Matthieu
 */
public class Barriere extends Objet {
    
    public Barriere(Case Case) {
        super(Case);
    }

    @Override
    public Enum_Objet getType() {
        return Enum_Objet.Barriere;
    }
    
}

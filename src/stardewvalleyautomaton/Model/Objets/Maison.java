/*
 * maison
 */
package stardewvalleyautomaton.Model.Objets;

import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Maison;

/**
 *
 * @author Matthieu
 */
public class Maison extends Objet {

    public Maison(stardewvalleyautomaton.Model.Cases.Case Case) {
        super(Case);
    }

    @Override
    public Enum_Objet getType() {
        return Maison;
    }
    
}

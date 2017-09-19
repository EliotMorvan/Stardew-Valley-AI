/*
 * machine à fromage
 */
package stardewvalleyautomaton.Model.Objets;

import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Machine_Fromage;

/**
 *
 * @author Matthieu
 */
public class Machine_Fromage extends Objet{

    public Machine_Fromage(stardewvalleyautomaton.Model.Cases.Case Case) {
        super(Case);
    }

    @Override
    public Enum_Objet getType() {
        return Machine_Fromage;
    }
    
}

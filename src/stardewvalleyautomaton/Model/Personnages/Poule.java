/*
 * Poule
 */
package stardewvalleyautomaton.Model.Personnages;

import stardewvalleyautomaton.Model.Cases.Case;
import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Oeuf;
import stardewvalleyautomaton.Model.Personnages.IA.Enum_Action;
import stardewvalleyautomaton.Model.Personnages.IA.IA;

/**
 *
 * @author Matthieu
 */
public class Poule extends Personnage {

    public Poule(Case _saCase,IA _ia) {
        super(_saCase,_ia);
    }


    @Override
    public Enum_Personnage getType() {
        return Enum_Personnage.Poule;
    }

    private void pondre() {
        if(this.getCase().getObjet() == null) {
            this.getCase().setObjet(Oeuf);
        }
    }
    
    @Override
    public void actionSpeciale(Enum_Action action) {
        switch(action) {
            case pondre: pondre(); break;
        }
    }
    
    
}

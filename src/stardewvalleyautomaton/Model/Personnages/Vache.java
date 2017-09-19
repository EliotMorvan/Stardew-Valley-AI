/*
 * Vache
 */
package stardewvalleyautomaton.Model.Personnages;

import stardewvalleyautomaton.Model.Cases.Case;
import static stardewvalleyautomaton.Model.Personnages.Enum_Personnage.Vache;
import stardewvalleyautomaton.Model.Personnages.IA.Enum_Action;
import stardewvalleyautomaton.Model.Personnages.IA.IA;

/**
 *
 * @author Matthieu
 */
public class Vache extends Personnage {

    private boolean lait; 
        
    public Vache(Case _saCase, IA _ia) {
        super(_saCase, _ia);
        this.lait = false;
    }

    @Override
    public Enum_Personnage getType() {
        return Vache;
    }
    
    //trait la vache
    public void traire() {
        this.lait = false;
    }
    
    //getter du lait
    public boolean lait() {
        return this.lait;
    }

    //action spéciale : produire du lait
    public boolean produireLait() {
        if(!lait)  {
            System.out.println("Le Créateur :");
            System.out.println(" - Abigail ! ici Le Créateur, ta vache vient de faire du lait !");
            this.lait = true;
        }
        return lait;
    }
    
    @Override
    public void actionSpeciale(Enum_Action action) {
        switch(action) {
            case produireLait: this.produireLait(); 
        }
    }
    
    
}

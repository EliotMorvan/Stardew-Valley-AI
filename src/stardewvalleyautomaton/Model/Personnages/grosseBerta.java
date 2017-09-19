/*
 * Abigail
 */
package stardewvalleyautomaton.Model.Personnages;

import stardewvalleyautomaton.Model.Cases.Case;
import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Oeuf;
import static stardewvalleyautomaton.Model.Personnages.Enum_Personnage.grosseBerta;
import stardewvalleyautomaton.Model.Personnages.IA.Enum_Action;
import stardewvalleyautomaton.Model.Personnages.IA.IA;

/**
 *
 * @author simonetma
 */
public class grosseBerta extends Personnage {

    public grosseBerta(Case _saCase,IA _ia) {
        super(_saCase,_ia);
    
    }

    @Override
    public Enum_Personnage getType() {
        return grosseBerta;
    }
    
    public void collecterOeuf() {  
        
        boolean success = false;
        
        if(this.getCase().getObjet() != null) {
            if(this.getCase().getObjet().getType() == Oeuf) {
                this.getCase().removeObjet();
                success = true;
            }
        }
    }
    
    @Override
    public void actionSpeciale(Enum_Action action) {
        switch(action) {
            case traire:
                collecterOeuf(); 
                break;
                
            case produireFromage: 
                collecterOeuf(); 
                break;
                
            case collecterOeuf: 
                collecterOeuf(); 
                break;
        }
    }
}
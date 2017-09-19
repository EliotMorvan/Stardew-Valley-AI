/*
 * gestionnaire des IAs
 */
package stardewvalleyautomaton.Model.Gestionnaires;

import java.util.ArrayList;
import stardewvalleyautomaton.Model.Personnages.IA.IA;

/**
 *
 * @author Matthieu
 */
public class GestionnaireDesIA {
    
    //DESIGN PATTERN DE LA BIBLIOTHEQUE ----------------------------------------
    private static GestionnaireDesIA instance;
    private final ArrayList<IA> listeDesIA;
    
    private GestionnaireDesIA() {
        this.listeDesIA = new ArrayList<>();
    }
    
    public static GestionnaireDesIA get() {
        if(instance == null) {
            instance = new GestionnaireDesIA();
        }
        return instance;
    }
    
    //ajouter une ia au registre
    public static void addIA(IA ia) {
        get().listeDesIA.add(ia);
    }
    
    //lister les IA
    public static ArrayList<IA> getListeDesIA() {
        return get().listeDesIA;
    }
}

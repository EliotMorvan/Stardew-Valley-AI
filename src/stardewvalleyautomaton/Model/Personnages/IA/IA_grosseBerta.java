/*
 * ia d'abigail
 */
package stardewvalleyautomaton.Model.Personnages.IA;

import java.util.ArrayList;
import stardewvalleyautomaton.Model.Carte;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesObjets;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesPersonnages;
import stardewvalleyautomaton.Model.Graphe;
import stardewvalleyautomaton.Model.Objets.Enum_Objet;
import stardewvalleyautomaton.Model.Objets.Objet;
import stardewvalleyautomaton.Model.Personnages.Enum_Personnage;
import static stardewvalleyautomaton.Model.Personnages.IA.Enum_Action.*;

/**
 *
 * @author simonetma
 */
public class IA_grosseBerta extends IA {
    
    ArrayList<Enum_Action> actionsAfaire = new ArrayList();
    ArrayList<Integer> listIndicesOeufs;
    private Graphe monGraphe = null;
    private boolean attente = false;
    private int commecerPar = 0;
    private int distanceMini;
    private int numAction;
    private int posOeuf;
    
    @Override
    protected void setActionValide() {

        this.addActionValide(attendre);
        this.addActionValide(moveLeft);
        this.addActionValide(moveRight);
        this.addActionValide(moveTop);
        this.addActionValide(moveBottom);
        this.addActionValide(collecterOeuf);

    }

    public int numSommetOufPresD_Abbigail() {
        
        int posAbigail = this.personnage().getCase().getLigne() * 30 + this.personnage().getCase().getColonne(); 

        int distanceMini_Abi_Oeuf = 900 * 900 + 1;  
        int sommetOeuf_le_plus_Pres = -3;
        int distanceOeuf;
                                               

        for (int i = 0; i <= GestionnaireDesObjets.getListeDesObjets().size() - 1; i++) {  
            if (GestionnaireDesObjets.getListeDesObjets().get(i).getType() == Enum_Objet.Oeuf) { 
                Objet Oeuf = GestionnaireDesObjets.getListeDesObjets().get(i);   
                int numSommetOeuf = Oeuf.getCase().getLigne() * 30 + Oeuf.getCase().getColonne(); 
                distanceOeuf = (this.monGraphe.distanceDijkstra(posAbigail, numSommetOeuf));  
                if (distanceOeuf < distanceMini_Abi_Oeuf) {                  
                    distanceMini_Abi_Oeuf = distanceOeuf;                       
                    sommetOeuf_le_plus_Pres = numSommetOeuf;                    
                }
            }
        }
        return sommetOeuf_le_plus_Pres;                                        
    }

    @Override
    public Enum_Action action() {
        if (this.monGraphe == null ){
            monGraphe = new Graphe(30*30);
            Carte.get().creationGraphe(monGraphe);
        }
        
        Enum_Action resultat = attendre;
        
        if (this.attente == false) {
            choixActionGlobale();
            if (numAction <actionsAfaire.size()) {
                resultat = actionsAfaire.get(numAction);
                numAction = numAction + 1;
            } else {                                                               
                resultat = attendre;
                switch (this.commecerPar) {
                default:                      
                this.personnage().actionSpeciale(collecterOeuf);
                this.commecerPar = 1;
                break;
                }
            }
        }
        return resultat;
    }

    private void choixActionGlobale() { 

        int posAbigail = this.personnage().getCase().getLigne() * 30 + this.personnage().getCase().getColonne();
        switch (this.commecerPar) {
            default: {
                posOeuf = numSommetOufPresD_Abbigail();
                if (posOeuf < 0) {                                                   
                } else {                                                             
                    distanceMini = this.monGraphe.distanceDijkstra(posAbigail, posOeuf);                 
                    repartitionActions();
                    this.commecerPar = 3; 
                }
                break;
            }
            case 3 :{
               break ;
            }
        }
    }

    
    
  public void repartitionActions() {
     
    actionsAfaire.clear();
    ArrayList<Integer> chemin = Graphe.getParcours();              
    int i = chemin.size() - 1;

    while (i > 0) {                                                 

            int numCaseCourante = chemin.get(i);                           

            int numCaseAdroite = numCaseCourante + 1;
            if (numCaseAdroite == chemin.get(i - 1)) {                   
                actionsAfaire.add(moveRight);
            }

            int numCaseAgauche = numCaseCourante - 1;
            if (numCaseAgauche == chemin.get(i - 1)) {                       
                actionsAfaire.add(moveLeft);
            }

            int numCaseEnHaut = numCaseCourante - 30;
            if (numCaseEnHaut == chemin.get(i - 1)) {                
                actionsAfaire.add(moveTop);
            }

            int numCaseEnBas = numCaseCourante + 30;
            if (numCaseEnBas == chemin.get(i - 1)) {                       
                actionsAfaire.add(moveBottom);
            }
            i--;
        }
        this.numAction = 0;
    }
    
    @Override
    public void faireAttendre() {
        this.attente = true;

    }

    @Override
    public void faireBouger() {
        this.attente = false;

    }
}
    
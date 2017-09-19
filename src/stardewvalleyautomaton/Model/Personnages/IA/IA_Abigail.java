/*
 * ia d'abigail
 */
package stardewvalleyautomaton.Model.Personnages.IA;

import java.util.ArrayList;
import stardewvalleyautomaton.IHM.Journee;
import static stardewvalleyautomaton.IHM.Journee.aprem;
import static stardewvalleyautomaton.IHM.Journee.matin;
import static stardewvalleyautomaton.IHM.Journee.soir;
import stardewvalleyautomaton.Model.Carte;
import stardewvalleyautomaton.Model.Cases.Enum_Case;
import static stardewvalleyautomaton.Model.Cases.Enum_Case.dirt;
import static stardewvalleyautomaton.Model.Cases.Enum_Case.grass;
import static stardewvalleyautomaton.Model.Cases.Enum_Case.lightgrass;
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
public class IA_Abigail extends IA {
    
//Variables/Listes
    ArrayList<Enum_Action> actionsAfaire = new ArrayList();
    ArrayList<Integer> listIndicesOeufs;
    private Enum_Case typeCaseAbigail;
    private Journee periode;
    private int commecer = 0;
    private int ptsSoiffe =0;
    private int fatigue = 0;
    private int numAction;
    private int posVache;
    private int posOeuf;
    private int heures;
    private int temps;
    
    private boolean attente = false;
    private Graphe monGraphe = null ;
   
    
   
@Override
    protected void setActionValide() {

        this.addActionValide(attendre);
        this.addActionValide(moveLeft);
        this.addActionValide(moveRight);
        this.addActionValide(moveTop);
        this.addActionValide(moveBottom);
        this.addActionValide(traire);
        this.addActionValide(produireFromage);
        this.addActionValide(collecterOeuf);

    }
    
/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//Cette procedure va voir si il y a un oeuf, calculer sa position.                               //
//Elle va aussi prendre la position d'Abigail, et calculer la distance mini entre elle et l'oeuf.//
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/
public int numSommetOufPresD_Abbigail() {
    int posAbigail = this.personnage().getCase().getLigne() * 30 + this.personnage().getCase().getColonne();

    int distanceMini_Abi_Oeuf = 900 * 900 + 1;  
    int distanceOeuf;
    int sommetOeuf_le_plus_Pres = -3;

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

 
    
/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//               Pareil que pour l'oeuf sauf que la c'est avec la vache.                         //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/
public int laVacheAproduitDuLait() {
    int res = -3;

    for (int i = 0; i <= GestionnaireDesPersonnages.getListeDesPersonnages().size() - 1; i++) {  

        if (GestionnaireDesPersonnages.getListeDesPersonnages().get(i).getType() == Enum_Personnage.Vache) {
            if (GestionnaireDesPersonnages.getListeDesPersonnages().get(i).getIA().action() == Enum_Action.produireLait) {

                GestionnaireDesPersonnages.getListeDesPersonnages().get(i).getIA().faireAttendre();
                int ligneVache = GestionnaireDesPersonnages.getListeDesPersonnages().get(i).getIA().personnage().getCase().getLigne();
                int colonneVache = GestionnaireDesPersonnages.getListeDesPersonnages().get(i).getIA().personnage().getCase().getColonne();
                int colonneAcote = ligneVache;
                if (colonneVache < 29) {
                    colonneAcote = colonneVache + 1;
                } else if (colonneVache > 0) {
                    colonneAcote = colonneVache - 1;
                }
                res = Carte.get().getNumSommetCase(ligneVache, colonneAcote);
                return res;
            }
        }
    }
    return res;
}
    
   

/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//            Cette fonction va "dire" à Abigail quoi faire en fonction de this.commencer        //
//            Par defaut si ChoixAction ne lui renvoi rien elle ne bougera pas et attendra.      //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/
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
            numAction = numAction +1 ;
        } else {                                                                
            resultat = attendre;
            switch (this.commecer) {
                case 3:
                    this.personnage().actionSpeciale(collecterOeuf);
                    this.commecer = 1;
                    break;
                case 4:
                    this.personnage().actionSpeciale(traire);
                    for (int a = 0; a < GestionnaireDesPersonnages.getListeDesPersonnages().size(); a++) {   
                        if (GestionnaireDesPersonnages.getListeDesPersonnages().get(a).getType() == Enum_Personnage.Vache) {
                            GestionnaireDesPersonnages.getListeDesPersonnages().get(a).getIA().faireBouger();
                        }
                    }   
                    this.commecer = 2;
                    break;
                default:                      
                    this.personnage().actionSpeciale(produireFromage);
                    this.commecer = 0;
                    break;
            }
        }
    }
    return resultat;
}

    
    
/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//            Cette fonction va dire a action le chemin le plus court pour chaque tâches.        //
//            La premiere tâche étant par défaut ramasser un oeuf.                               //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/    
private void choixActionGlobale() { 
    
    int posAbigail = this.personnage().getCase().getLigne() * 30 + this.personnage().getCase().getColonne();
    switch (this.commecer) {
        case 0: 
            posOeuf = numSommetOufPresD_Abbigail();
            if (posOeuf < 0) {                                                    

            } else {                                                              
                this.monGraphe.distanceDijkstra(posAbigail, posOeuf);                
                repartitionActions();
                this.commecer = 3;  
            }
            break;

        case 1: 
            posVache = laVacheAproduitDuLait();
            if (posVache < 0) {                                                    
            } else {                                                              
               this.monGraphe.distanceDijkstra(posAbigail, posVache);                 
                repartitionActions();  
                this.commecer = 4;   

            }
            break;

        case 2: 
          this.monGraphe.distanceDijkstra(posAbigail, 230);                     
            repartitionActions();              
            this.commecer = 5;  
            break;

        case 3 : break ;
        case 4 : break;
        default: break;
    }
}
    
    

/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//            Cette fonction va dire à Abigail les actions à faire pour pouvoir atteindre        //
//            soit un oeuf soit la vache pour la traire...                                       //
//            Donc elle gère le déplacement d'Abigail.                                           //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/   
public int repartitionActions() {
    int posAbigail = this.personnage().getCase().getLigne() * 30 + this.personnage().getCase().getColonne();
    actionsAfaire.clear();
    ArrayList<Integer> chemin = Graphe.getParcours();                    
    int i = chemin.size() - 1;
    
    typeCaseAbigail = this.personnage().getCase().getType();
    while (i > 0) {                                                       
        GetPeriod();
        fatigue(posAbigail);
        
        int numCaseCourante = chemin.get(i);                               
        int numCaseAdroite = numCaseCourante + 1;
        if (numCaseAdroite == chemin.get(i - 1)) {                       
            actionsAfaire.add(moveRight);
            ptsSoiffe ++;
        }

        int numCaseAgauche = numCaseCourante - 1;
        if (numCaseAgauche == chemin.get(i - 1)) {                      
            actionsAfaire.add(moveLeft);
            ptsSoiffe ++;
        }

        int numCaseEnHaut = numCaseCourante - 30;
        if (numCaseEnHaut == chemin.get(i - 1)) {                        
            actionsAfaire.add(moveTop);
            ptsSoiffe ++;
        }

        int numCaseEnBas = numCaseCourante + 30;
        if (numCaseEnBas == chemin.get(i - 1)) {                         
            actionsAfaire.add(moveBottom);
            ptsSoiffe ++;
        }
        
        /*System.out.println(fatigue);
        System.out.println(periode);*/
    i--;
    }
    this.numAction = 0;
    return ptsSoiffe;
}
    
    

/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//            Cette fonction va détecter si il fais nuit et envoyer Abigail                      //
//            faire un petit dodo chez elle.                                                     //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/       
public void nuit(){
/*    if(fatigue <= 100 && periode == soir){
this.monGraphe.distanceDijkstra(posAbigail, 230);
repartitionActions();
this.personnage().actionSpeciale(attendre);
fatigue = 0;
}*/
System.out.println(periode);
}
    

    
/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//            Cette fonction va détecter la periode temporel dans laquelle Abigail se trouve     //
//                                                                                               //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/         
 public Journee GetPeriod(){
        heures++;
        if(heures>71 && heures<=101){
            periode=soir;
            if(heures == 100){
                heures = 2;
            }
        }
        if(heures>41 && heures<=71){
            periode=aprem;
        }
        if(heures>=1 && heures<=41){
            periode=matin;
        }
        return periode;  
    }


/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//            Cette fonction va gérer la fatigue d'abigail                                       //
//                                                                                               //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/  
public void fatigue(int posAbigail){ 
    if(fatigue <= 50 && periode == matin){
        fatigue ++;
    }
    if(typeCaseAbigail == grass && fatigue > 50 && periode == matin){
        fatigue = fatigue + 3;
    }
    if(typeCaseAbigail == dirt && fatigue > 50 && periode == matin){
        fatigue = fatigue + 1;
    }
    if(typeCaseAbigail == lightgrass && fatigue > 50 && periode == matin){
        fatigue = fatigue + 2;
    }
    if(fatigue <= 50&& periode == aprem){
        fatigue = fatigue + 2;
    }
    if(typeCaseAbigail == grass && fatigue > 50 && periode == aprem){
        fatigue = fatigue + 6;
    }
    if(typeCaseAbigail == dirt && fatigue > 50 && periode == aprem){
        fatigue = fatigue + 2;
    }
    if(typeCaseAbigail == lightgrass && fatigue > 50 && periode == aprem){
        fatigue = fatigue + 4;
    }
    /*    System.out.println(fatigue);*/
    if(fatigue+10 >94){
        this.personnage().actionSpeciale(attendre);
        fatigue = fatigue - 50;
    }   
    /*    System.out.println(periode);*/
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

/*
 * Timer gérant les IAs et l'IHM
 */
package stardewvalleyautomaton.IHM;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import static stardewvalleyautomaton.IHM.Journee.*;
import stardewvalleyautomaton.IHM.Scenes.Scene_Principale;
import stardewvalleyautomaton.IHM.Sprites.Personnages.IHM_Personnage;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesIA;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesObjets;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesPersonnages;
import stardewvalleyautomaton.Model.Objets.Objet;
import stardewvalleyautomaton.Model.Personnages.IA.IA;
import stardewvalleyautomaton.Model.Personnages.Personnage;


/**
 *
 * @author simonetma
 */
public class TimerIAHandler implements EventHandler {

    private Scene_Principale scene;                                             //scene principale

    //constructeur
    public TimerIAHandler(Scene_Principale _scene) {
        this.scene = _scene;
        
        
    }
    
    @Override
    //méthode appelée régulièrement
    public void handle(Event event) {
        //gestion des objets
        this.gestionDesObjets();
        
        //gestion des personnages
        this.gestionDesPersonnages();
        
        //gestion des IAs
        this.gestionDesIAs();
        
        
    }
    
    
    
    
    
    //gestion des objets
    private void gestionDesObjets() {
        //gestion des objets à rajouter
        ArrayList<Objet> aAjouter = new ArrayList<>(GestionnaireDesObjets.getListeDesNouveauxObjets());
        ArrayList<Objet> aSupprimer = new ArrayList<>(GestionnaireDesObjets.getListeDesAnciensObjets());
        for(Objet objet : aAjouter) {
            //ajouter l'objet à l'IHM
            scene.ajouterObjet(objet);
            //Valider l'objet
            GestionnaireDesObjets.valider(objet);
        }
        //suppression des objets
        for(Objet objet : aSupprimer) {
            //supprimer l'objet de l'IHM
            scene.supprimerObjet(objet);
            //Devalider l'objet
            GestionnaireDesObjets.devalider(objet);
        }
    }
    
    //gestion des Personnages
    private void gestionDesPersonnages() {
        //gestion des personnages à rajouter
        ArrayList<Personnage> aModifier = new ArrayList<>(GestionnaireDesPersonnages.getListeDesNouveauxPersonnages());
        for(Personnage personnage : aModifier) {
            //ajouter le personnage à l'IHM
            scene.ajouterPersonnage(personnage);
            //Valider le personnage
            GestionnaireDesPersonnages.valider(personnage);
        }
    }
    
    //gestion des IAs et des sprites personnages (pour les mouvements)
    private void gestionDesIAs() {
        //liste des IAs et des sprites personnages
        ArrayList<IA> listeDesIA = GestionnaireDesIA.getListeDesIA();
        ArrayList<IHM_Personnage> listeDesPersonnages = this.scene.getListeDesPersonnages();
        
        //actionne les IAs
        for(IA ia : listeDesIA) {
            ia.activerParTimer();
        }
        
        //actionne le mouvement des sprites
        for(IHM_Personnage perso : listeDesPersonnages) {
            perso.action();
        }

    }
    
}

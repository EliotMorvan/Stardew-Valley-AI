/*
 * Scene principale
 */
package stardewvalleyautomaton.IHM.Scenes;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import stardewvalleyautomaton.IHM.Masques.IHM_Carte;
import stardewvalleyautomaton.IHM.Sprites.Personnages.IHM_Personnage;
import stardewvalleyautomaton.Model.*;
import stardewvalleyautomaton.Model.Objets.Objet;
import stardewvalleyautomaton.Model.Personnages.Personnage;

/**
 *
 * @author simonetma
 */
public class Scene_Principale extends Scene {
    
    private final Pane root;                                                    //Panel principal supportant la scène
    private final Carte carte;                                                  //carte du jeu (modèle)
    
    private final IHM_Carte ihm_Carte;                                          //gestion de la carte IHM
    
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public Scene_Principale(Pane _root) {
        super(_root);
        //intialisation des attributs
        this.root = _root;
        this.carte = Carte.get();
        
        //création de la carte
        this.ihm_Carte = new IHM_Carte(this.carte,this.root);
        
        //affichage de la carte couche par couche
        this.ihm_Carte.getNiveau(0).setGridLinesVisible(true);
        root.getChildren().add(this.ihm_Carte.getNiveau(0));
        root.getChildren().add(this.ihm_Carte.getNiveau(1));
        root.getChildren().add(this.ihm_Carte.getNiveau(2));
        root.getChildren().add(this.ihm_Carte.getNiveau(3));
        root.getChildren().add(this.ihm_Carte.getNiveau(4));
        root.getChildren().add(this.ihm_Carte.getNiveau(5));
        root.getChildren().add(this.ihm_Carte.getNiveau(6));
        root.getChildren().add(this.ihm_Carte.getNiveau(7));
        root.getChildren().add(this.ihm_Carte.getNiveau(8));
               
    }
    
    //GETTER -------------------------------------------------------------------
    //renvoie la liste des personnages IHM présents dans la carte
    public ArrayList<IHM_Personnage> getListeDesPersonnages() {
        return this.ihm_Carte.getListeDesPersonnages();
    }
    
    
    //GESTION DES OBJETS -------------------------------------------------------
    //ajouter un objet
    public void ajouterObjet(Objet objet) {
        switch(objet.getType()) {
            case Arbre: ihm_Carte.ajouterArbre(objet.getCase().getLigne(), objet.getCase().getColonne()); break;
            case Oeuf: ihm_Carte.ajouterOeuf(objet.getCase().getLigne(), objet.getCase().getColonne()); break;
            case Barriere: ihm_Carte.ajouterBarriere(objet.getCase().getLigne(), objet.getCase().getColonne()); break;
            case Maison: ihm_Carte.ajouterMaison(objet.getCase().getLigne(), objet.getCase().getColonne()); break;
            case Machine_Fromage: ihm_Carte.ajouterFromage(objet.getCase().getLigne(), objet.getCase().getColonne()); break;
        }
    }
    
    //supprimer un objet
    public void supprimerObjet(Objet objet) {
        this.ihm_Carte.supprimerObjet(objet);
    }
    
    
    //GESTION DES PERSONNAGES --------------------------------------------------
    //ajoute un personnage
    public void ajouterPersonnage(Personnage personnage) {
        switch(personnage.getType()) {
            case Poule: ihm_Carte.ajouterPoule(personnage.getCase().getLigne(), personnage.getCase().getColonne()); break;
            case Abigail: ihm_Carte.ajouterAbigail(personnage.getCase().getLigne(), personnage.getCase().getColonne()); break;
            case Vache: ihm_Carte.ajouterVache(personnage.getCase().getLigne(), personnage.getCase().getColonne()); break;
            case grosseBerta: ihm_Carte.ajouterAbigail(personnage.getCase().getLigne(), personnage.getCase().getColonne()); break;
        }
    }
    
}

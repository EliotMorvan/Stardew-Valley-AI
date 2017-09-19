/*
 * Gestion principale de la carte IHM
 */
package stardewvalleyautomaton.IHM.Masques;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.*;
import stardewvalleyautomaton.IHM.Sprites.Fabrique_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.Personnages.IHM_Personnage;
import stardewvalleyautomaton.Model.Carte;
import stardewvalleyautomaton.Model.Cases.Case;
import static stardewvalleyautomaton.Model.Cases.Enum_Case.*;
import static stardewvalleyautomaton.Model.Objets.Enum_Objet.*;
import stardewvalleyautomaton.Model.Objets.Objet;
import static stardewvalleyautomaton.Model.Personnages.Enum_Personnage.*;

/**
 *
 * @author simonetma
 */
public class IHM_Carte {    
    private final Carte carte;                                                  //carte du jeu (modèle)
    private final IHM_Masque niveau[];                                          //les défférentes couches de sprites (0=sol, 1=objet au sol, ...)
    
    private final ArrayList<IHM_Personnage> listeDesPersonnages;                //liste de tous les personnages IHM                          
    
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Carte(Carte _carte,Pane root) {
        //initialisation des attributs
        this.carte = _carte;
        this.listeDesPersonnages = new ArrayList<>();
        this.niveau = new IHM_Masque[9];
        //intialisation des couches de sprites (vide)
        for(int i=0;i<9;i++) {
            this.niveau[i] = new IHM_Masque(this.carte,root);
        }
        //lance le chargement de la carte
        this.chargerCarte();
    }
    
    //CHARGEMENT DE LA MAP -----------------------------------------------------
    private void chargerCarte() {
        //remplit le fond de la carte (niveau 0)
        for(int i=0;i<carte.taille();i++) {
            for(int j=0;j<carte.taille();j++) {
                Case caseEnCours = carte.getCase(i, j);
                this.niveau[0].setSprite(Fabrique_IHM_Sprite.create(caseEnCours.getType(),0),i,j);
            }
        }
        
        //auto tilling du fond
        this.autoTillingLightGrass();
        this.autoTillingDirt();
    }
    
   
    
    //AUTOTILLING --------------------------------------------------------------
    //tilling du niveau dirt
    private void autoTillingDirt() {
        //tilling convexe
        for(int i=0;i<carte.taille();i++) {
            for(int j=0;j<carte.taille();j++) {
               if(this.carte.getCase(i, j).getType() == dirt) {  
                   boolean H = true,B = true,D = true,G = true;
                   if(i-1>=0) {
                       H = !(this.carte.getCase(i-1, j).getType() == lightgrass);
                   }
                   if(i+1<carte.taille()) {
                       B = !(this.carte.getCase(i+1, j).getType() == lightgrass);
                   }
                   if(j-1>=0) {
                       G = !(this.carte.getCase(i, j-1).getType() == lightgrass);
                   }
                   if(j+1<carte.taille()) {
                       D = !(this.carte.getCase(i, j+1).getType() == lightgrass);
                   }
                   
                   if(!H&&!G) { this.niveau[0].setModifieur(i,j,1); }
                   else if(!H&&!D) { this.niveau[0].setModifieur(i,j,3); }
                   else if(!B&&!G) { this.niveau[0].setModifieur(i,j,6); }
                   else if(!B&&!D) { this.niveau[0].setModifieur(i,j,8); }
                   else if(!H) { this.niveau[0].setModifieur(i,j,2); }
                   else if(!G) { this.niveau[0].setModifieur(i,j,4); }
                   else if(!D) { this.niveau[0].setModifieur(i,j,5); }
                   else if(!B) { this.niveau[0].setModifieur(i,j,7); }
               }
            } 
        }
        
        //tilling concave
        for(int i=0;i<carte.taille();i++) {
            for(int j=0;j<carte.taille();j++) {
                if(this.carte.getCase(i, j).getType() == dirt && this.niveau[0].getModifieur(i,j) == 0) {  
                    if(i-1>=0) {
                        if(this.carte.getCase(i-1, j).getType()==dirt) {
                            switch(this.niveau[0].getModifieur(i-1,j)) {
                                case 1: case 4: this.niveau[0].setModifieur(i,j,9); break;
                                case 3: case 5: this.niveau[0].setModifieur(i,j,10); break;
                            }
                        }
                    }
                    if(i+1<this.carte.taille()) {
                        if(this.carte.getCase(i+1, j).getType()==dirt) {
                            switch(this.niveau[0].getModifieur(i+1,j)) {
                                case 4: case 6: this.niveau[0].setModifieur(i,j,11); break;
                                case 5: case 8: this.niveau[0].setModifieur(i,j,12); break;
                            }
                        }
                    }
                }
                
                if(this.carte.getCase(i, j).getType() == dirt && this.niveau[0].getModifieur(i,j) == 2) {
                    if(j-1>=0) {
                        switch(this.niveau[0].getModifieur(i,j-1)) {
                            case 1:
                            case 10:
                                if(j+1<this.carte.taille()) {
                                    switch(this.niveau[0].getModifieur(i,j+1)) {
                                        case 2 : this.niveau[0].setModifieur(i,j,22); break;
                                        case 3 : this.niveau[0].setModifieur(i,j,23); break;
                                    }
                                }
                                else {
                                    this.niveau[0].setModifieur(i,j,22);
                                }
                                break;
                            case 2:
                            case 22:
                                if(j+1<this.carte.taille()) {
                                    if(this.niveau[0].getModifieur(i,j+1)==3 || this.niveau[0].getModifieur(i,j+1)==0) {
                                       this.niveau[0].setModifieur(i,j,23);
                                    }
                                }
                                else {
                                    this.niveau[0].setModifieur(i,j,23);
                                }
                                break;
                        }
                    }
                } 
            }
        }
    }
    
    //tilling du niveau lightGrass
    private void autoTillingLightGrass() {
        //tilling convexe
        for(int i=0;i<carte.taille();i++) {
            for(int j=0;j<carte.taille();j++) {
               if(this.carte.getCase(i, j).getType() == lightgrass) {  
                   boolean H = true,B = true,D = true,G = true;
                   if(i-1>=0) {
                       H = !(this.carte.getCase(i-1, j).getType() == grass);
                   }
                   if(i+1<carte.taille()) {
                       B = !(this.carte.getCase(i+1, j).getType() == grass);
                   }
                   if(j-1>=0) {
                       G = !(this.carte.getCase(i, j-1).getType() == grass);
                   }
                   if(j+1<carte.taille()) {
                       D = !(this.carte.getCase(i, j+1).getType() == grass);
                   }
                   if(!H&&!G) { this.niveau[0].setModifieur(i,j,1); }
                   else if(!H&&!D) { this.niveau[0].setModifieur(i,j,3);  }
                   else if(!B&&!G) { this.niveau[0].setModifieur(i,j,6);  }
                   else if(!B&&!D) { this.niveau[0].setModifieur(i,j,8);  }
                   else if(!H) { this.niveau[0].setModifieur(i,j,2);  }
                   else if(!G) { this.niveau[0].setModifieur(i,j,4);  }
                   else if(!D) { this.niveau[0].setModifieur(i,j,5);  }
                   else if(!B) { this.niveau[0].setModifieur(i,j,7);  }
               }
            }
        }
        //tilling concave
        for(int i=0;i<carte.taille();i++) {
            for(int j=0;j<carte.taille();j++) {
                if(this.carte.getCase(i, j).getType() == lightgrass && this.niveau[0].getModifieur(i,j) == 0) {  
                    if(i-1>=0) {
                        if(this.carte.getCase(i-1, j).getType()==lightgrass) {
                            switch(this.niveau[0].getModifieur(i-1,j)) {
                                case 1: case 4: this.niveau[0].setModifieur(i,j,9); break;
                                case 3: case 5: this.niveau[0].setModifieur(i,j,10); break;
                            }
                        }
                    }
                    if(i+1<this.carte.taille()) {
                        if(this.carte.getCase(i+1, j).getType()==lightgrass) {
                            switch(this.niveau[0].getModifieur(i+1,j)) {
                                case 4: case 6: this.niveau[0].setModifieur(i,j,11); break;
                                case 5: case 8: this.niveau[0].setModifieur(i,j,12); break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    //GETTER -------------------------------------------------------------------
    public IHM_Masque getNiveau(int i) {
        return this.niveau[i];
    }
    
    public ArrayList<IHM_Personnage> getListeDesPersonnages() {
        return this.listeDesPersonnages;
    }
    
    public Carte carte() {
        return this.carte;
    }
    
    
    //CREATION DES OBJETS ------------------------------------------------------
    //ajoute un arbre à la position donnée
    public void ajouterArbre(int ligne, int colonne) {
        this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Arbre, 0),ligne,colonne);
        if(ligne-1 >= 0) {
            this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Arbre, 1),ligne-1,colonne);
        }
        if(ligne-2 >= 0) {
            this.niveau[3].setSprite(Fabrique_IHM_Sprite.create(Arbre, 3),ligne-2,colonne);
            if(colonne-1 >=0) {
                this.niveau[3].setSprite(Fabrique_IHM_Sprite.create(Arbre, 2),ligne-2,colonne-1);
            }
            if(colonne+1 < carte.taille()) {
                this.niveau[3].setSprite(Fabrique_IHM_Sprite.create(Arbre, 4),ligne-2,colonne+1);
            }
        }
        if(ligne-3 >= 0) {
            this.niveau[4].setSprite(Fabrique_IHM_Sprite.create(Arbre, 6),ligne-3,colonne);
            if(colonne-1 >=0) {
                this.niveau[4].setSprite(Fabrique_IHM_Sprite.create(Arbre, 5),ligne-3,colonne-1);
            }
            if(colonne+1 < carte.taille()) {
                this.niveau[4].setSprite(Fabrique_IHM_Sprite.create(Arbre, 7),ligne-3,colonne+1);
            }
        }
        if(ligne-4 >= 0) {
            this.niveau[5].setSprite(Fabrique_IHM_Sprite.create(Arbre, 9),ligne-4,colonne);
            if(colonne-1 >=0) {
                this.niveau[5].setSprite(Fabrique_IHM_Sprite.create(Arbre, 8),ligne-4,colonne-1);
            }
            if(colonne+1 < carte.taille()) {
                this.niveau[5].setSprite(Fabrique_IHM_Sprite.create(Arbre, 10),ligne-4,colonne+1);
            }
        }
        if(ligne-5 >= 0) {
            this.niveau[6].setSprite(Fabrique_IHM_Sprite.create(Arbre, 12),ligne-5,colonne);
            if(colonne-1 >=0) {
                this.niveau[6].setSprite(Fabrique_IHM_Sprite.create(Arbre, 11),ligne-5,colonne-1);
            }
            if(colonne+1 < carte.taille()) {
                this.niveau[6].setSprite(Fabrique_IHM_Sprite.create(Arbre, 13),ligne-5,colonne+1);
            }
        }
    }
    //ajoute un oeuf à la position donnée
    public void ajouterOeuf(int ligne, int colonne) {
        this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Oeuf, 0), ligne, colonne);
    }
    //ajoute une barrière à la position donnée
    public void ajouterBarriere(int ligne, int colonne) {
        this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Barriere, 0), ligne, colonne);
        this.majBarriere(1, ligne, colonne);
        if(ligne-1>=0) {
            this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Barriere, 10), ligne-1, colonne);
            this.majBarriere(2, ligne-1, colonne);
        }
    }
    //mise à jour des sprites de barrières (voisins)
    private void majBarriere(int lvl, int ligne,int colonne) {
        
        //calcul du modifieur
        int modifieur = (lvl-1)*10;
        if(this.niveau[lvl].getType(ligne, colonne-1) == IHM_Barriere && this.niveau[lvl].getType(ligne, colonne+1) == IHM_Barriere) {
            modifieur += 3;
        }
        else if(this.niveau[lvl].getType(ligne, colonne-1) == IHM_Barriere) {
            modifieur += 2;
        }
        else if(this.niveau[lvl].getType(ligne, colonne+1) == IHM_Barriere) {
            modifieur += 1;
        }
        
        //si modification du modifieur récursion sur les cotés modifiants
        if(modifieur != this.niveau[lvl].getModifieur(ligne, colonne)) {
            this.niveau[lvl].setModifieur(ligne, colonne, modifieur);
            switch(modifieur % 10) {
                case 1: this.majBarriere(lvl, ligne, colonne+1); break;
                case 2: this.majBarriere(lvl, ligne, colonne-1); break;
                case 3: this.majBarriere(lvl, ligne, colonne-1); this.majBarriere(lvl, ligne, colonne+1); break;
            }
        }
    }
    //ajoute la maison
    public void ajouterMaison(int ligne, int colonne) {
        boolean premiereLigne = true;
  
        if(ligne+1<=carte.taille()) { 
            if(carte.getObjet(ligne+1, colonne)!= null) {
                if(carte.getObjet(ligne+1, colonne).getType() == Maison) {
                    premiereLigne = false;
                }
            }
        }
        if(premiereLigne) {
            this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Maison, 0), ligne, colonne);
            this.majMaison(1, ligne, colonne);
            if(ligne-1>=0) {
                this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Maison, 10), ligne-1, colonne);
                this.majMaison(2, ligne-1, colonne);
            }
            if(ligne-2>=0) {
                this.niveau[3].setSprite(Fabrique_IHM_Sprite.create(Maison, 20), ligne-2, colonne);
                this.majMaison(3, ligne-2, colonne);
            }
            if(ligne-3>=0) {
                this.niveau[4].setSprite(Fabrique_IHM_Sprite.create(Maison, 30), ligne-3, colonne);
                this.majMaison(4, ligne-3, colonne);
            }
            if(ligne-4>=0) {
                this.niveau[5].setSprite(Fabrique_IHM_Sprite.create(Maison, 40), ligne-4, colonne);
                this.majMaison(5, ligne-4, colonne);
            }
            if(ligne-5>=0) {
                this.niveau[6].setSprite(Fabrique_IHM_Sprite.create(Maison, 50), ligne-5, colonne);
                this.majMaison(6, ligne-5, colonne);
            }
            if(ligne-6>=0) {
                this.niveau[7].setSprite(Fabrique_IHM_Sprite.create(Maison, 60), ligne-6, colonne);
                this.majMaison(7, ligne-6, colonne);
            }
            if(ligne-7>=0) {
                this.niveau[8].setSprite(Fabrique_IHM_Sprite.create(Maison, 70), ligne-7, colonne);
                this.majMaison(8, ligne-7, colonne);
            }
        }
    }
    //mise à jour des sprites de la maison (voisins)
    private void majMaison(int lvl, int ligne, int colonne) {
        //calcul du modifieur
        int modifieur = (lvl-1)*10;
        if(this.niveau[lvl].getType(ligne, colonne-1) == IHM_Maison) {
            modifieur = this.niveau[lvl].getModifieur(ligne, colonne-1) +1;
        }
        
        //si modification du modifieur
        if(modifieur != this.niveau[lvl].getModifieur(ligne, colonne)) {
            this.niveau[lvl].setModifieur(ligne, colonne, modifieur);
        }
        
        //rec vers la gauche
        if(this.niveau[lvl].getType(ligne, colonne+1) == IHM_Maison) {
            if(this.niveau[lvl].getModifieur(ligne, colonne+1) != modifieur+1) {
                this.majMaison(lvl, ligne, colonne+1);
            }
        }
    }
    //ajoute la machine à fromage
    public void ajouterFromage(int ligne, int colonne) {
        this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Machine_Fromage, 0), ligne, colonne);
        if(ligne-1 >= 0) {
            this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Machine_Fromage, 1), ligne-1, colonne);
        }
    }
   
    //SUPPRESSION DES OBJETS ---------------------------------------------------
    public void supprimerObjet(Objet objet) {
        switch(objet.getType()) {
            case Oeuf: this.niveau[1].removeSprite(objet.getCase().getLigne(), objet.getCase().getColonne());
        }
    }
    
    
    //CREATION DES PERSONNAGES
    //crée une poule à la position donnée
    public void ajouterPoule(int ligne, int colonne) {
        //creation et enregistrement de l'IHM_Personnage
        IHM_Personnage ihm_personnage = new IHM_Personnage(carte.getCase(ligne, colonne).getPersonnage());
        this.listeDesPersonnages.add(ihm_personnage);
        
        //création et enregistrement des sprites
        ihm_personnage.addSprite(this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Poule,0), ligne, colonne));
    }
    
    
    //crée Abigail à la position donnée
    public void ajouterAbigail(int ligne, int colonne) {
        //creation et enregistrement de l'IHM_Personnage
        IHM_Personnage ihm_personnage = new IHM_Personnage(carte.getCase(ligne, colonne).getPersonnage());
        this.listeDesPersonnages.add(ihm_personnage);
        
        //création et enregistrement des sprites
        ihm_personnage.addSprite(this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Abigail,0), ligne, colonne));
        if(ligne-1>=0) {
            ihm_personnage.addSprite(this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Abigail,100), ligne-1, colonne));
        }
    }
    
    
     //crée Abigail à la position donnée
    public void ajoutergrosseBerta(int ligne, int colonne) {
        //creation et enregistrement de l'IHM_Personnage
        IHM_Personnage ihm_personnage = new IHM_Personnage(carte.getCase(ligne, colonne).getPersonnage());
        this.listeDesPersonnages.add(ihm_personnage);
        
        //création et enregistrement des sprites
        ihm_personnage.addSprite(this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(grosseBerta,0), ligne, colonne));
        if(ligne-1>=0) {
            ihm_personnage.addSprite(this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(grosseBerta,100), ligne-1, colonne));
        }
    }
    
    
    //crée une vache à la position donnée
    public void ajouterVache(int ligne, int colonne) {
        //creation et enregistrement de l'IHM_Personnage
        IHM_Personnage ihm_personnage = new IHM_Personnage(carte.getCase(ligne, colonne).getPersonnage());
        this.listeDesPersonnages.add(ihm_personnage);
        
        //création et enregistrement des sprites
        ihm_personnage.addSprite(this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Vache,0), ligne, colonne));
        if(colonne+1<carte.taille()) {
            ihm_personnage.addSprite(this.niveau[1].setSprite(Fabrique_IHM_Sprite.create(Vache,100), ligne, colonne+1));
        }
        if(ligne-1>=0) {
            ihm_personnage.addSprite(this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Vache,200), ligne-1, colonne));
            if(colonne+1<carte.taille()) {
                ihm_personnage.addSprite(this.niveau[2].setSprite(Fabrique_IHM_Sprite.create(Vache,300), ligne-1, colonne+1));
            }
        }
    }
}

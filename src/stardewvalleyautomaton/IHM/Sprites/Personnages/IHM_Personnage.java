/*
 * personnage visuel
 */
package stardewvalleyautomaton.IHM.Sprites.Personnages;

import java.util.ArrayList;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;
import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Oeuf;
import stardewvalleyautomaton.Model.Personnages.IA.Enum_Action;
import stardewvalleyautomaton.Model.Personnages.IA.IA;
import stardewvalleyautomaton.Model.Personnages.Personnage;

/**
 *
 * @author Matthieu
 */
public class IHM_Personnage {
    private Personnage personnage;                                              //personnage (modèle)
    private final ArrayList<IHM_Sprite_Personnage> listeDesSprites;             //liste des sprites du personnage
    private int nombreMouvement;                                                //nombre mouvement nécessaire pour le mouvement d'une case
    private int positionAnimation;                                              //index pour l'animation
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Personnage(Personnage _personnage) {
        this.personnage = _personnage;
        this.listeDesSprites = new ArrayList<>();
        this.nombreMouvement = 8;
        this.positionAnimation = 0;
    }
    
    //GETTER -------------------------------------------------------------------
    public IA getIA() {
        return this.personnage.getIA();
    }
    
    //AJOUTER UN SPRITE --------------------------------------------------------
    public void addSprite(IHM_Sprite_Personnage _sprite) {
        this.listeDesSprites.add(_sprite);
    }  
    
    //GESTION DE LA TRANSLATION DES SPRITES ------------------------------------
    public void setTranslateX(int deltaX) {
        for(IHM_Sprite ihm_sprite : this.listeDesSprites) {
            ihm_sprite.setTranslateX(ihm_sprite.getTranslateX()+deltaX);
        }
    }
    
    public void setTranslateY(int deltaY) {
        for(IHM_Sprite ihm_sprite : this.listeDesSprites) {
            ihm_sprite.setTranslateY(ihm_sprite.getTranslateY()+deltaY);
        }
    }
    
    //GESTION DES ACTIONS ------------------------------------------------------
    //si l'ia est en attente, lancer l'action suivante
    public void action() {
        if(!this.getIA().estEnAttente()) {
            this.action(this.getIA().getAction());
        }
    }
    
    //effectue l'action demandée
    public void action(Enum_Action action) {
        switch(action) {
            case moveLeft: this.moveLeft(); break;
            case moveRight: this.moveRight(); break;
            case moveTop: this.moveTop(); break;
            case moveBottom: this.moveBottom(); break;
            default : this.attendre();
        }
    }
    
    //action : attendre
    public void attendre() {
        if(this.nombreMouvement==0) {
            this.nombreMouvement = 8;
            this.getIA().setAttente(true);
        }
        else {
            this.nombreMouvement--;
        }
    }
    
    //action : déplacement vers la gauche
    public void moveLeft() {
        if(nombreMouvement != 0) {
            if(this.positionAnimation >= 30 && this.positionAnimation <=33) {
                this.positionAnimation += 1;
                if(this.positionAnimation ==34) {
                    this.positionAnimation = 30;
                }
                this.setTranslateX(-4);
                this.nombreMouvement--;
            }
            else {
                this.positionAnimation = 30;
            }
        }
        else {
            this.nombreMouvement=8;
            this.positionAnimation = 30;
            this.getIA().setAttente(true);
        }
        
        //met à jour les sprites
        for(IHM_Sprite_Personnage sprite : this.listeDesSprites) {
            sprite.setPositionAnimation(positionAnimation);
        }
    }
    
    //action : déplacement vers la droite
    public void moveRight() {
        if(nombreMouvement != 0) {
            if(this.positionAnimation >= 10 && this.positionAnimation <=13) {
                this.positionAnimation += 1;
                if(this.positionAnimation ==14) {
                    this.positionAnimation = 10;
                }
                this.setTranslateX(4);
                this.nombreMouvement--;
            }
            else {
                this.positionAnimation = 10;
            }
        }
        else {
            this.nombreMouvement=8;
            this.positionAnimation = 10;
            this.getIA().setAttente(true);
        }
        
        //met à jour les sprites
        for(IHM_Sprite_Personnage sprite : this.listeDesSprites) {
            sprite.setPositionAnimation(positionAnimation);
        }
    }
    
    //action : déplacement vers la haut
    public void moveTop() {
        if(nombreMouvement != 0) {
            if(this.positionAnimation >= 20 && this.positionAnimation <=23) {
                this.positionAnimation += 1;
                if(this.positionAnimation ==24) {
                    this.positionAnimation = 20;
                }
                this.setTranslateY(-4);
                this.nombreMouvement--;
            }
            else {
                this.positionAnimation = 20;
            }
        }
        else {
            this.nombreMouvement=8;
            this.positionAnimation = 20;
            this.getIA().setAttente(true);
        }
        
        //met à jour les sprites
        for(IHM_Sprite_Personnage sprite : this.listeDesSprites) {
            sprite.setPositionAnimation(positionAnimation);
        }
    };
    
    //action : déplacement vers la bas
    public void moveBottom() {
        if(nombreMouvement != 0) {
            if(this.positionAnimation >= 0 && this.positionAnimation <=3) {
                this.positionAnimation += 1;
                if(this.positionAnimation ==4) {
                    this.positionAnimation = 0;
                }
                this.setTranslateY(4);
                this.nombreMouvement--;
            }
            else {
                this.positionAnimation = 0;
            }
        }
        else {
            this.nombreMouvement=8;
            this.positionAnimation = 0;
            this.getIA().setAttente(true);
        }
        
        //met à jour les sprites
        for(IHM_Sprite_Personnage sprite : this.listeDesSprites) {
            sprite.setPositionAnimation(positionAnimation);
        }
    };
    
    
}

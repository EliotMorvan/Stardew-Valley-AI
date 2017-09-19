/*
 * Design Pattern Fabrique pour les Sprites
 */
package stardewvalleyautomaton.IHM.Sprites;

import stardewvalleyautomaton.IHM.Sprites.Fond.*;
import stardewvalleyautomaton.IHM.Sprites.Objets.*;
import stardewvalleyautomaton.IHM.Sprites.Personnages.*;
import stardewvalleyautomaton.Model.Cases.Enum_Case;
import stardewvalleyautomaton.Model.Objets.Enum_Objet;
import stardewvalleyautomaton.Model.Personnages.Enum_Personnage;

/**
 *
 * @author simonetma
 */
public class Fabrique_IHM_Sprite {
    //DESIGN PATTERN SINGLETON -------------------------------------------------
    //instance de la fabrique
    private static Fabrique_IHM_Sprite instance;
    //constructeur
    private Fabrique_IHM_Sprite() {}
    //getter static
    public static Fabrique_IHM_Sprite get() {
        if(instance == null) {
            instance = new Fabrique_IHM_Sprite();
        }
        return instance;
    }
    
    //DESIGN PATTERN FABRIQUE --------------------------------------------------
    //créateur à partir d'une case
    public static IHM_Sprite create(Enum_Case typeCase,int _modifieur) {
        IHM_Sprite ihm_Sprite = null;
        switch(typeCase) {
            case grass: ihm_Sprite = new IHM_Sprite_Grass(); break;
            case lightgrass: ihm_Sprite = new IHM_Sprite_LightGrass(); break;
            case dirt: ihm_Sprite = new IHM_Sprite_Dirt(); break;
        }
        if(ihm_Sprite != null) {
            ihm_Sprite.setModifieur(_modifieur);
        }
        return ihm_Sprite;
    }
    //créateur à partir d'un objet
    public static IHM_Sprite create(Enum_Objet typeObjet,int _modifieur) {
        IHM_Sprite ihm_Sprite = null;
        switch(typeObjet) {
            case Oeuf: ihm_Sprite = new IHM_Sprite_Oeuf(); break;
            case Arbre: ihm_Sprite = new IHM_Sprite_Arbre(); break;
            case Barriere: ihm_Sprite = new IHM_Sprite_Barriere(); break;
            case Maison: ihm_Sprite = new IHM_Sprite_Maison(); break;
            case Machine_Fromage: ihm_Sprite = new IHM_Sprite_Fromage(); break;
        }
        if(ihm_Sprite != null) {
            ihm_Sprite.setModifieur(_modifieur);
        }
        return ihm_Sprite;
    }
    //créateur à partir d'un personnage
    public static IHM_Sprite_Personnage create(Enum_Personnage typePersonnage,int _modifieur) {
        IHM_Sprite_Personnage ihm_Sprite = null;
        switch(typePersonnage) {
            case Poule: ihm_Sprite = new IHM_Sprite_Poule(); break;
            case Abigail: ihm_Sprite = new IHM_Sprite_Abigail(); break;
            case Vache: ihm_Sprite = new IHM_Sprite_Vache(); break;
            case grosseBerta: ihm_Sprite = new IHM_Sprite_grosseBerta(); break;
        }
        if(ihm_Sprite != null) {
            ihm_Sprite.setModifieur(_modifieur);
        }
        return ihm_Sprite;
    }
}

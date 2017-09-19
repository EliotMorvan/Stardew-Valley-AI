/*
 * sprite de personnage (gestion de l'animation en plus)
 */
package stardewvalleyautomaton.IHM.Sprites.Personnages;

import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;

/**
 *
 * @author Matthieu
 */
public abstract class IHM_Sprite_Personnage extends IHM_Sprite {
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Personnage(String nomImage, int nombreModifieur) {
        super(nomImage, nombreModifieur);
    }
    
    public abstract void setPositionAnimation(int position);
    
}

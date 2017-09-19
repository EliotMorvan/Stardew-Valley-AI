/*
 * sprite d'Abigail
 */
package stardewvalleyautomaton.IHM.Sprites.Personnages;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.IHM_Abigail;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Abigail extends IHM_Sprite_Personnage {

    private static final int NOMBRE_MODIFIEUR = 134;                            //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Abigail() {
        super("Abigail", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        //sprite bas
        this.setPosition(0, 1, 0);
        this.setPosition(1, 1, 1);
        this.setPosition(2, 1, 2);
        this.setPosition(3, 1, 3);
        this.setPosition(10, 3, 0);
        this.setPosition(11, 3, 1);
        this.setPosition(12, 3, 2);
        this.setPosition(13, 3, 3);
        this.setPosition(20, 5, 0);
        this.setPosition(21, 5, 1);
        this.setPosition(22, 5, 2);
        this.setPosition(23, 5, 3);
        this.setPosition(30, 7, 0);
        this.setPosition(31, 7, 1);
        this.setPosition(32, 7, 2);
        this.setPosition(33, 7, 3);
        //sprite haut
        this.setPosition(100, 0, 0);
        this.setPosition(101, 0, 1);
        this.setPosition(102, 0, 2);
        this.setPosition(103, 0, 3);
        this.setPosition(110, 2, 0);
        this.setPosition(111, 2, 1);
        this.setPosition(112, 2, 2);
        this.setPosition(113, 2, 3);
        this.setPosition(120, 4, 0);
        this.setPosition(121, 4, 1);
        this.setPosition(122, 4, 2);
        this.setPosition(123, 4, 3);
        this.setPosition(130, 6, 0);
        this.setPosition(131, 6, 1);
        this.setPosition(132, 6, 2);
        this.setPosition(133, 6, 3);
    }

    @Override
    public int WidthSprite() {
        return 16;
    }

    @Override
    public int HeightSprite() {
        return 16;
    }

    @Override
    public Enum_IHM_Sprite getType() {
        return IHM_Abigail;
    }

    @Override
    public void setPositionAnimation(int position) {
        int modifieur_final = position;
        //gestion du sprite du haut
        if(this.modifieur()>=100) { modifieur_final+=100; }
        //change le modifieur
        this.setModifieur(modifieur_final);
    }
    
}

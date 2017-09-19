/*
 * sprite d'une vache
 */
package stardewvalleyautomaton.IHM.Sprites.Personnages;

import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.IHM_Vache;

/**
 *
 * @author Matthieu
 */
public class IHM_Sprite_Vache extends IHM_Sprite_Personnage {

    
    private static final int NOMBRE_MODIFIEUR = 334;                            //nombre de modifieurs différents
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite_Vache() {
        super("Vache", NOMBRE_MODIFIEUR);
        this.gestionPositionSprites();
    }
    
    //INITIALISATION DES POSITIONS POSSIBLES DU SPRITES DANS LA SPRITESHEET ----
    private void gestionPositionSprites() {
        //sprite bas gauche
        this.setPosition(0, 1, 0);
        this.setPosition(1, 1, 2);
        this.setPosition(2, 1, 4);
        this.setPosition(3, 1, 6);
        this.setPosition(10, 3, 0);
        this.setPosition(11, 3, 2);
        this.setPosition(12, 3, 4);
        this.setPosition(13, 3, 6);
        this.setPosition(20, 5, 0);
        this.setPosition(21, 5, 2);
        this.setPosition(22, 5, 4);
        this.setPosition(23, 5, 6);
        this.setPosition(30, 7, 0);
        this.setPosition(31, 7, 2);
        this.setPosition(32, 7, 4);
        this.setPosition(33, 7, 6);
        //sprite bas droit
        this.setPosition(100, 1, 1);
        this.setPosition(101, 1, 3);
        this.setPosition(102, 1, 5);
        this.setPosition(103, 1, 7);
        this.setPosition(110, 3, 1);
        this.setPosition(111, 3, 3);
        this.setPosition(112, 3, 5);
        this.setPosition(113, 3, 7);
        this.setPosition(120, 5, 1);
        this.setPosition(121, 5, 3);
        this.setPosition(122, 5, 5);
        this.setPosition(123, 5, 7);
        this.setPosition(130, 7, 1);
        this.setPosition(131, 7, 3);
        this.setPosition(132, 7, 5);
        this.setPosition(133, 7, 7);
        //sprite haut gauche
        this.setPosition(200, 0, 0);
        this.setPosition(201, 0, 2);
        this.setPosition(202, 0, 4);
        this.setPosition(203, 0, 6);
        this.setPosition(210, 2, 0);
        this.setPosition(211, 2, 2);
        this.setPosition(212, 2, 4);
        this.setPosition(213, 2, 6);
        this.setPosition(220, 4, 0);
        this.setPosition(221, 4, 2);
        this.setPosition(222, 4, 4);
        this.setPosition(223, 4, 6);
        this.setPosition(230, 6, 0);
        this.setPosition(231, 6, 2);
        this.setPosition(232, 6, 4);
        this.setPosition(233, 6, 6);
        //sprite haut droit
        this.setPosition(300, 0, 1);
        this.setPosition(301, 0, 3);
        this.setPosition(302, 0, 5);
        this.setPosition(303, 0, 7);
        this.setPosition(310, 2, 1);
        this.setPosition(311, 2, 3);
        this.setPosition(312, 2, 5);
        this.setPosition(313, 2, 7);
        this.setPosition(320, 4, 1);
        this.setPosition(321, 4, 3);
        this.setPosition(322, 4, 5);
        this.setPosition(323, 4, 7);
        this.setPosition(330, 6, 1);
        this.setPosition(331, 6, 3);
        this.setPosition(332, 6, 5);
        this.setPosition(333, 6, 7);
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
        return IHM_Vache;
    }

    @Override
    public void setPositionAnimation(int position) {
        int modifieur_final = position;
        //gestion des autres slides
        if(this.modifieur()>=100 && this.modifieur()<200) { modifieur_final+=100; }
        if(this.modifieur()>=200 && this.modifieur()<300) { modifieur_final+=200; }
        if(this.modifieur()>=300) { modifieur_final+=300; }
        //change le modifieur
        this.setModifieur(modifieur_final);
    }
    
}

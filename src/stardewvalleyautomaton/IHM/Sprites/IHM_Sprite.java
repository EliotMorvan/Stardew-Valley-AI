/*
 * Sprite général
 */
package stardewvalleyautomaton.IHM.Sprites;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireImages;

/**
 *
 * @author simonetma
 */
public abstract class IHM_Sprite extends ImageView {
    private final Image ficheSprite;                                            //spriteSheet
    private int modifieur;                                                      //numero du sprite actif
    private final int colonneSprite[];                                          //liste des colonnes possibles pour le sprite (en fct du modifieur)
    private final int ligneSprite[];                                            //liste des lignes possibles pour le sprite (en fct du modifieur)
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Sprite(String nomImage,int nombreModifieur) {
        //initialisation des attributs
        this.ficheSprite = GestionnaireImages.getImage(nomImage);
        this.colonneSprite = new int[nombreModifieur];
        this.ligneSprite = new int[nombreModifieur];
        this.modifieur = 0;
        //configuration du viewer
        this.setImage(this.ficheSprite);
        this.setScaleX(2);
        this.setScaleY(2);
    }
    
    //GESTION DU MODIFIEUR -----------------------------------------------------
    //setter
    public void setModifieur(int _modifieur) {
        this.modifieur = _modifieur;
        this.setImage();
    }
    //getter
    public int modifieur() {
        return this.modifieur;
    }
    
    //GESTION DE LA POSITION DU SPRITE DANS LA SPRITESHEET ---------------------
    //ajoute une position possible pour le sprite dans la spritesheet
    public void setPosition(int _modifieur, int _ligne,int _colonne) {
        this.colonneSprite[_modifieur] = _colonne;
        this.ligneSprite[_modifieur] = _ligne;
    }
    
    //renvoie la ligne du sprite dans la spritesheet
    public int getLigneSprite() {
        return this.ligneSprite[this.modifieur];
    }
    
    //renvoie la colonne du sprite dans la spritesheet
    public int getColonneSprite() {
        return this.colonneSprite[this.modifieur];
    }
   
    //GESTION DE L'IMAGE -------------------------------------------------------
    //fixe le scope du sprite
    public void setImage() {
        this.setViewport(new Rectangle2D(getColonneSprite()*WidthSprite(),getLigneSprite()*HeightSprite(),WidthSprite(),HeightSprite()));
    }
    
    //METHODES ABSTRAITES ------------------------------------------------------
    //renvoie la largeur du sprite
    public abstract int WidthSprite();
    //renvoie la hauteur du sprite
    public abstract int HeightSprite();
    //renvoie le type du sprite
    public abstract Enum_IHM_Sprite getType();
}

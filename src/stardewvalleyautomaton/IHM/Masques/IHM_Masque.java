/*
 * Gestion d'un niveau de sprites
 */
package stardewvalleyautomaton.IHM.Masques;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite;
import stardewvalleyautomaton.IHM.Sprites.IHM_Sprite;
import stardewvalleyautomaton.Model.Carte;
import static stardewvalleyautomaton.IHM.Sprites.Enum_IHM_Sprite.IHM_Vide;
import stardewvalleyautomaton.IHM.Sprites.Personnages.IHM_Sprite_Personnage;

/**
 *
 * @author simonetma
 */
public class IHM_Masque extends GridPane {
    
    private final IHM_Sprite tableauCases[][];                                  //tableau des sprites du niveau
    private final Carte carte;                                                  //carte du jeu
    private final Pane root;                                                    //panel parent
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public IHM_Masque(Carte _carte, Pane _root) {
        //initialisation des attributs
        this.carte = _carte;
        this.root = _root;
        this.tableauCases = new IHM_Sprite[carte.taille()][carte.taille()];
        this.configurationDuGridPanel();
    }
    
    //CONFIGURATION DU PANEL ---------------------------------------------------
    private void configurationDuGridPanel() {
        //fixe la taille du panel et sa position
        this.prefWidthProperty().bind(root.widthProperty().subtract(100));
        this.maxWidthProperty().bind(root.widthProperty().subtract(100));
        this.setAlignment(Pos.CENTER);
        //construit les containtes du gridPanel
        for(int i=0;i<carte.taille();i++) {
            this.getColumnConstraints().addAll(new ColumnConstraints(32));
            this.getRowConstraints().addAll(new RowConstraints(32));
        }
    }
    
    //AJOUT D'UN SPRITE --------------------------------------------------------
    //cas d'un sprite classique
    public IHM_Sprite setSprite(IHM_Sprite sprite,int ligne,int colonne) {
        //on mémorise le sprite (pour suppression et analyse)
        this.tableauCases[ligne][colonne] = sprite;
        //on l'ajoute comme noeud graphique
        this.add(sprite, colonne, ligne);
        //on le centre dans la case
        GridPane.setHalignment(sprite, HPos.CENTER);
        return sprite;
    }
    //cas d'un sprite de personnage
    public IHM_Sprite_Personnage setSprite(IHM_Sprite_Personnage sprite,int ligne,int colonne) {
        this.add(sprite, colonne, ligne);
        GridPane.setHalignment(sprite, HPos.CENTER);
        return sprite;
    }
    
    //SUPPRESSION d'UN SPRITE --------------------------------------------------
    public void removeSprite(int ligne,int colonne) {
        this.getChildren().remove(this.tableauCases[ligne][colonne]);
        this.tableauCases[ligne][colonne] = null;
    }
    
    //MODIFICATION D'UN SPRITE -------------------------------------------------
    //méthode pour modifier un modifieur d'un sprite existant
    public void setModifieur(int ligne,int colonne,int modifieur) {
        this.tableauCases[ligne][colonne].setModifieur(modifieur);
    }
    
    //GETTER -------------------------------------------------------------------
    //renvoie le modifieur du sprite
    public int getModifieur(int ligne,int colonne) {
        return this.tableauCases[ligne][colonne].modifieur();
    }
    //renvoie le type de sprite
    public Enum_IHM_Sprite getType(int ligne, int colonne) {
        Enum_IHM_Sprite type = IHM_Vide;
        if(ligne >= 0 && colonne >= 0 && ligne < carte.taille() && colonne < carte.taille()) {
            if(this.tableauCases[ligne][colonne] != null) {
                type = this.tableauCases[ligne][colonne].getType();
            }
        }
        return type;
    }
}

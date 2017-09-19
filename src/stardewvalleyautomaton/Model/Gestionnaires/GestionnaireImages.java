/*
 * GESTIONNAIRE DES IMAGES
 */
package stardewvalleyautomaton.Model.Gestionnaires;

import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author simonetma
 */
public class GestionnaireImages {
    
    //DESIGN PATTERN DE LA BIBLIOTHEQUE ----------------------------------------
    private static GestionnaireImages instance;
    private HashMap<String,Image> listeImage;
    
    public static GestionnaireImages get() {
        if(instance == null) {
            instance = new GestionnaireImages();
        }
        return instance;
    }
    
    //constructeur de la bibliothèque d'images
    private GestionnaireImages() {
        this.listeImage = new HashMap<>();
        this.addImage("TilesExt","tiles_exterieur.png");
        this.addImage("Fence","Fence1.png");
        this.addImage("Objets", "springobjects.png");
        this.addImage("Poule", "poule.png");
        this.addImage("Abigail", "Abigail.png");
        this.addImage("grosseBerta", "prince.png");
        this.addImage("Maison", "Maison.png");
        this.addImage("Craftables", "Craftables.png");
        this.addImage("Vache", "Vache.png");
    }
    
    //ajout d'une image dans la bibliothèque
    private void addImage(String nom, String nomFichier) {
        this.listeImage.put(nom, new Image("stardewvalleyautomaton/Ressources/Images/"+nomFichier));
    }
    
    //getter d'une image
    public static Image getImage(String nom) {
        return get().listeImage.get(nom);
    }
}

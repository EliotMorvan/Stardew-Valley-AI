/*
 * Abigail
 */
package stardewvalleyautomaton.Model.Personnages;

import java.util.ArrayList;
import stardewvalleyautomaton.Model.Carte;
import stardewvalleyautomaton.Model.Cases.Case;
import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Machine_Fromage;
import static stardewvalleyautomaton.Model.Objets.Enum_Objet.Oeuf;
import static stardewvalleyautomaton.Model.Personnages.Enum_Personnage.Abigail;
import static stardewvalleyautomaton.Model.Personnages.Enum_Personnage.Vache;
import stardewvalleyautomaton.Model.Personnages.IA.Enum_Action;
import stardewvalleyautomaton.Model.Personnages.IA.IA;
import stardewvalleyautomaton.Model.Personnages.IA.IA_Abigail;

/**
 *
 * @author simonetma
 */
public class Abigail extends Personnage {

    ArrayList<Personnage> listeDesVaches = new ArrayList<>();    
    
    private boolean fromage;
    private int ptsfaim = 0;
    private int ptssoif = 0;
    private IA_Abigail x;
    private boolean lait;
    
    //CONSTRUCTEUR -------------------------------------------------------------
    public Abigail(Case _saCase,IA _ia) {
        super(_saCase,_ia);
        this.lait = false;
        this.fromage = false;
    }

    @Override
    public Enum_Personnage getType() {
        return Abigail;
    }
    
    //ACTIONS POSSIBLES --------------------------------------------------------
    //traire les vaches voisines
     public void traire() {
        //récupère les vaches autour d'elle
        ArrayList<Personnage> listeDesVaches = new ArrayList<>();
        
        int ligne = this.getCase().getLigne();
        int colonne = this.getCase().getColonne();
        
       
        if(lait) {
            System.out.println(" - J'ai déjà du lait sur moi mamene...");
        }else{
            
            if(Carte.get().getPersonnage(ligne-1, colonne) != null) {
                if(Carte.get().getPersonnage(ligne-1, colonne).getType() == Vache) {
                    listeDesVaches.add(Carte.get().getPersonnage(ligne-1, colonne));
                }
            }
            if(Carte.get().getPersonnage(ligne+1, colonne) != null) {
                if(Carte.get().getPersonnage(ligne+1, colonne).getType() == Vache) {
                    listeDesVaches.add(Carte.get().getPersonnage(ligne+1, colonne));
                }
            }
            if(Carte.get().getPersonnage(ligne, colonne-1) != null) {
                if(Carte.get().getPersonnage(ligne, colonne-1).getType() == Vache) {
                    listeDesVaches.add(Carte.get().getPersonnage(ligne, colonne-1));
                }
            }
            if(Carte.get().getPersonnage(ligne, colonne+1) != null) {
                if(Carte.get().getPersonnage(ligne, colonne+1).getType() == Vache) {
                    listeDesVaches.add(Carte.get().getPersonnage(ligne, colonne+1));
                }
            }
            for(Personnage vache : listeDesVaches) {
                ((Vache) vache).traire();
            }
            this.lait = true;
            System.out.println("ABIGAIL (entrain de traire): 'spouik... spouik... spouik...'");
        }
        
    }
    
    

/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//                Cette fonction va produire du fromage mais pas que !                           //
//                elle teste si abigail a assez faim pour faire du fromage                       //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/
    public void produireFromage() {
        //repère si Abigail est à côté d'une machine
        
        int ligne = this.getCase().getLigne();
        int colonne = this.getCase().getColonne();
            
        if(Carte.get().getObjet(ligne-1, colonne) != null) {
            if(Carte.get().getObjet(ligne-1, colonne).getType() == Machine_Fromage) {
            
                }
            }
            if(Carte.get().getObjet(ligne+1, colonne) != null) {
                if(Carte.get().getObjet(ligne+1, colonne).getType() == Machine_Fromage) {

                }
            }
            if(Carte.get().getObjet(ligne, colonne-1) != null) {
                if(Carte.get().getObjet(ligne, colonne-1).getType() == Machine_Fromage) {

                }
            }
            if(Carte.get().getObjet(ligne, colonne+1) != null) {
                if(Carte.get().getObjet(ligne, colonne+1).getType() == Machine_Fromage) {

                }
            }
            
       
    }

   @Override
    public void actionSpeciale(Enum_Action action) {
        switch(action) {
            case traire: traire(); break;
            case produireFromage: produireFromage(); break;
            case collecterOeuf: collecterOeuf(); break;
        }
    }    
    
  
    
/*/////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                               //
//               Cette fonction va collecter un oeuf sur la case mais pas que !                  //
//               Elle possède une pseudo "IA" qui va gérer les points de fatigues                //
//               et de faims. Soiffe a venir...                                                  //
//                                                                                               //
/////////////////////////////////////////////////////////////////////////////////////////////////*/    
    public void collecterOeuf() {
        boolean success = false;
        if(this.getCase().getObjet() != null) {
            if(this.getCase().getObjet().getType() == Oeuf) {
                this.getCase().removeObjet();
                success = true;
            }
        }
        
        if(success) {
             this.ptsfaim = this.ptsfaim +5;
            
            for(Personnage vache : listeDesVaches) {
            
                if(((Vache) vache).produireLait() && ptsfaim +5 > 70 && !this.lait) {
                    traire();
                }
            }
            
            if(ptsfaim +5 > 70 && this.lait && !this.fromage){
                /*                System.err.println("LE VENTRE D'ABIGAIL :");
                System.out.println(" 'SCROooo... SCROoooo...' ");*/
                System.out.println("ABIGAIL : - J'ai faim une fois !");
                produireFromage();
                this.lait = false;
                this.fromage = true;
            }

            if(ptsfaim +5 > 70 && this.fromage){
                this.fromage = false;
                System.out.println("ABIGAIL (entrain de s'enpiffrer de fromage) : - C'était bîn bon lâ !");
                ptsfaim = 0;
            }

            if(ptssoif +5 > 70 && this.lait){
                    System.out.println("Abigail a soif et bois tout son lait !");
                    this.lait = false;
            }
        }
    }
    
    
    
}
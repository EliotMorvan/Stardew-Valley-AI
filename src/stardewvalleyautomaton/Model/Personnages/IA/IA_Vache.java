/*
 * IA d'une vache
 */
package stardewvalleyautomaton.Model.Personnages.IA;

import java.util.ArrayList;
import java.util.Random;
import stardewvalleyautomaton.Model.Carte;
import stardewvalleyautomaton.Model.Cases.Case;
import static stardewvalleyautomaton.Model.Personnages.Enum_Personnage.Vache;
import static stardewvalleyautomaton.Model.Personnages.IA.Enum_Action.*;

/**
 *
 * @author Matthieu
 */
public class IA_Vache extends IA {

    private boolean attente = false;

    @Override
    protected void setActionValide() {
        this.addActionValide(attendre);
        this.addActionValide(moveLeft);
        this.addActionValide(moveRight);
        this.addActionValide(moveTop);
        this.addActionValide(moveBottom);
        this.addActionValide(produireLait);
    }

    @Override
    public Enum_Action action() {
        Enum_Action resultat = attendre;

        if (this.attente == false) {
         
            ArrayList<Enum_Action> actionPossible = new ArrayList<>();
            actionPossible.add(attendre);

            Case positionActuelle = this.personnage().getCase();
            int ligne = positionActuelle.getLigne();
            int colonne = positionActuelle.getColonne();

            if (colonne - 1 >= 0) {
                if (Carte.get().getCase(ligne, colonne - 1).estLibre()) {
                    actionPossible.add(moveLeft);
                }
            }
            if (colonne + 2 < Carte.get().taille()) {
                if (Carte.get().getCase(ligne, colonne + 2).estLibre()) {
                    actionPossible.add(moveRight);
                }
            }
            if (ligne - 1 >= 0 && colonne + 1 < Carte.get().taille()) {
                if (Carte.get().getCase(ligne - 1, colonne).estLibre() && Carte.get().getCase(ligne - 1, colonne + 1).estLibre()) {
                    actionPossible.add(moveTop);
                }
            }
            if (ligne + 1 < Carte.get().taille() && colonne + 1 < Carte.get().taille()) {
                if (Carte.get().getCase(ligne + 1, colonne).estLibre() && Carte.get().getCase(ligne + 1, colonne + 1).estLibre()) {
                    actionPossible.add(moveBottom);
                }
            }

            //choisie une action au hasard
            Random random = new Random();
            int alea = random.nextInt(actionPossible.size());

            resultat = actionPossible.get(alea);

            //gestion de la production de lait
            if (resultat == attendre) {
                if (random.nextInt(6) == 0) {
                    resultat = produireLait;
                }
            }
            
            if (resultat == produireLait) {
                if (random.nextInt(6) == 0) {
                    resultat = attendre;
                }
            }

        }

        return resultat;
    }

    @Override
    public void faireAttendre() {
        this.attente = true;
    }

    @Override
    public void faireBouger() {
        this.attente = false;
    }

}

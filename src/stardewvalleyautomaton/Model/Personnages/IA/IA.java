/*
 * IA générale
 */
package stardewvalleyautomaton.Model.Personnages.IA;

import java.util.ArrayList;
import stardewvalleyautomaton.Model.Cases.Case;
import stardewvalleyautomaton.Model.Gestionnaires.GestionnaireDesPersonnages;
import stardewvalleyautomaton.Model.Personnages.Enum_Personnage;
import static stardewvalleyautomaton.Model.Personnages.IA.Enum_Action.attendre;
import stardewvalleyautomaton.Model.Personnages.Personnage;

/**
 *
 * @author Matthieu
 */
public abstract class IA {

    private Personnage personnage;                                              //propriétaire de l'IA
    private boolean estEnAttente;                                               //l'IA est elle en attente d'ordre
    private Enum_Action actionEnCours;                                          //action en cours de réalisation
    private ArrayList<Enum_Action> actionValide;                                //liste des actions que l'ia peut faire
    private int distanceAction;

    //CONSTRUCTEUR -------------------------------------------------------------
    public IA() {
        this.personnage = null;
        this.estEnAttente = true;
        this.actionValide = new ArrayList<>();
        this.setActionValide();
        this.distanceAction=0;
    }

    //GESTION DU PERSONNAGE ----------------------------------------------------
    //getter
    protected Personnage personnage() {
        return this.personnage;
    }

    //setter
    public void setPersonnage(Personnage _personnage) {
        this.personnage = _personnage;
    }

    //AJOUT D'UNE ACTION VALIDE ------------------------------------------------
    public void addActionValide(Enum_Action action) {
        this.actionValide.add(action);
    }

    //DEFINIT LA LISTE DES ACTIONS VALIDES -------------------------------------
    protected abstract void setActionValide();

    //ACTIVATION DE L'IA PAR LE TIMER ------------------------------------------
    public final void activerParTimer() {
        //si l'ia n'a rien à faire
        if (this.estEnAttente) {
            //création d'une nouvelle action
            this.actionEnCours = this.action();
            if (!this.actionValide.contains(this.actionEnCours)) {
                this.actionEnCours = attendre;
            }
            this.personnage().action(this.actionEnCours);
            this.estEnAttente = false;
        }
    }

    //GESTION DE L'OCCUPATION DE L'IA
    //l'ia est elle en attente d'ordre ?
    public final boolean estEnAttente() {
        return this.estEnAttente;
    }

    //changer l'occupation de l'ia
    public final void setAttente(boolean attente) {
        this.estEnAttente = attente;
    }

    //renvoie l'action en cours de réalisation
    public final Enum_Action getAction() {
        return this.actionEnCours;
    }

    //METHODE ABSTRAITE --------------------------------------------------------
    //méthode appelée quand l'IA n'a rien à faire, doit renvoyer la prochaine action
    public abstract Enum_Action action();

    public abstract void faireAttendre();

    public abstract void faireBouger();

}

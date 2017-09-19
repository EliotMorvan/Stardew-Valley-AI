/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stardewvalleyautomaton.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author simonetma
 */
public class Graphe {

    //Attributs
    private HashMap<Couple, Integer> matriceAdjacence;                           //un graphe est défini par sa matrice d'adjacence
    private int nombreDeSommets;

    private boolean mark[];
    private int distance[];
    private int predecesseur[];
    private int infini;

    private static ArrayList<Integer> sommetsParcourus;

    //Constructeur du graphe
    public Graphe(int _nombreDeSommets) {
        this.nombreDeSommets = _nombreDeSommets;
        this.matriceAdjacence = new HashMap<>();

        this.mark = new boolean[this.nombreDeSommets];
        this.distance = new int[this.nombreDeSommets];
        this.predecesseur = new int[this.nombreDeSommets];
        this.infini = 1;

        sommetsParcourus = new ArrayList();
    }

    //place "valeur" en position (i,j) de la matrice
    public void modifierMatrice(int i, int j, int valeur) {
        this.matriceAdjacence.put(new Couple(i, j), valeur);
    }

    //renvoie la valeur de la matrice en position (i,j)
    public int Matrice(int i, int j) {
        //valeur par défaut
        int res = 0;
        Couple c = new Couple(i, j);
        //si (i,j) est bien présent dans la matrice
        if (this.matriceAdjacence.containsKey(c)) {
            res = this.matriceAdjacence.get(c);
        }
        return res;
    }

    //renvoie le nombre de sommet du graphe
    public int NombreSommet() {
        return this.nombreDeSommets;
    }

    //renvoie la matrice d'adjacence
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i <= this.nombreDeSommets - 1; i++) {
            for (int j = 1; j <= this.nombreDeSommets - 1; j++) {
                res += this.Matrice(i, j);
                if (j != this.nombreDeSommets) {
                    res += " / ";
                }
            }
            if (i != this.nombreDeSommets) {
                res += "\n";
            }
        }
        return res;
    }

    public void ajouterArc(int debut, int fin) {
        this.modifierMatrice(debut, fin, 1);
    }

    public void ajouterArete(int debut, int fin) {
        this.modifierMatrice(debut, fin, 1);
        this.modifierMatrice(fin, debut, 1);
    }

    public void enleverArete(int debut, int fin) {
        this.modifierMatrice(debut, fin, 0);
        this.modifierMatrice(fin, debut, 0);
    }

    public boolean EstReflexif() {
        boolean res = true;
        for (int i = 1; i <= this.nombreDeSommets; i++) {
            if (Matrice(i, i) == 0) {
                res = false;
            }
        }
        return res;
    }

    public boolean EstSymetrique() {
        boolean res = true;
        for (int i = 1; i <= this.nombreDeSommets; i++) {
            for (int j = 1; j < this.nombreDeSommets; j++) {
                if (this.Matrice(i, j) != 1 && this.Matrice(j, i) == 0) {
                    res = false;
                }
            }
        }
        return res;
    }

    public boolean EstTransitif() {
        boolean res = false;
        for (int i = 1; i <= this.nombreDeSommets + 1; i++) {
            for (int j = 1; j <= this.nombreDeSommets; j++) {
                for (int k = 1; k <= this.nombreDeSommets; k++) {
                    if (this.Matrice(i, j) == 1 && Matrice(j, k) == 1 && Matrice(i, k) == 1) {
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    public boolean EstAntiSymetrique() {
        boolean res = false;
        return res;
    }

    public int numeroCase(int i, int j, int m) {
        return (i - 1) * m + j;
    }

    public void parcoursLargeur(int numS) {
        int s = numS;
        Stack file = new Stack();
        boolean mark[] = {};
        mark = new boolean[this.nombreDeSommets + 1];
        String str = new String();

        for (int i = 1; i <= this.NombreSommet(); i++) {
            mark[i] = false;
        }

        mark[s] = true;
        file.add(s);

        while (!file.empty()) {
            int x = (int) file.firstElement();
            file.remove(0);
            str += x + "-";
            for (int i = 1; i <= this.NombreSommet(); i++) {
                if ((!mark[i]) && (this.Matrice(i, x) != 0)) {
                    file.add(i);
                    mark[i] = true;
                }
            }
        }

        System.out.println(str);
    }

    public void connexité(int numS) {

        int s = numS;
        Stack file = new Stack();
        boolean mark[] = {};
        mark = new boolean[this.nombreDeSommets + 1];
        String str = new String();

        for (int i = 1; i <= this.NombreSommet(); i++) {
            mark[i] = false;
        }

        mark[s] = true;
        file.add(s);

        while (!file.empty()) {
            int x = (int) file.firstElement();
            file.remove(0);
            str += x + "-";
            for (int i = 1; i <= this.NombreSommet(); i++) {
                if ((!mark[i]) && (this.Matrice(i, x) != 0)) {
                    file.add(i);
                    mark[i] = true;
                }
            }
        }
        for (int k = 0; k < mark.length; k++) {
            if (mark[k]) {

            }
        }
        System.out.println(str);
    }

    /*  private void calculInfini() {
        this.infini = 0;

        for (int i = 0; i <= this.nombreDeSommets - 1; i++) {
            for (int j = 1; j <= this.nombreDeSommets - 1; j++) {
                this.infini += this.Matrice(i, j);
            }
        }

        this.infini += 1;
    }
     */
    private void calculInfini() {

        this.infini += this.nombreDeSommets * this.nombreDeSommets;
    }

    /* private void calculInfini() {
        this.infini = 0;

        for (int i = 0; i <= this.nombreDeSommets - 1; i++) {
            for (int j = 0; j <= this.nombreDeSommets - 1; j++) {
                this.infini += this.Matrice(i, j);
            }
        }

        this.infini += 1;
    }*/
    private void relachement(int a, int i) {

        if (this.Matrice(a, i) != 0) {
            if (distance[i] > (distance[a] + this.Matrice(a, i))) {
                distance[i] = distance[a] + this.Matrice(a, i);
                predecesseur[i] = a;

            }
        }
    }

    private int selectionSommet() {
        int indice = -1;
        int min = this.infini;
        for (int i = 0; i <= this.nombreDeSommets - 1; i++) {
            if (distance[i] < min && !mark[i]) {
                indice = i;
                min = distance[i];
            }
        }

        return indice;

    }

    private boolean existeUnSommetNonMarque() {
        boolean resultat = false;
        for (int i = 0; i <= this.nombreDeSommets - 1; i++) {
            if (!mark[i]) {

                resultat = true;
            }
        }
        return resultat;
    }

    private void initialisation(int s) {
        this.calculInfini();
        for (int i = 0; i <= this.nombreDeSommets - 1; i++) {
            mark[i] = false;
            distance[i] = this.infini;
            predecesseur[i] = -1;
        }

        distance[s] = 0;
    }

    public int distanceDijkstra(int depart, int arrivee) {

        this.initialisation(depart);
        this.calculInfini();
        while (this.existeUnSommetNonMarque()) {
            int a = this.selectionSommet();
            mark[a] = true;
            for (int i = 0; i <= this.nombreDeSommets - 1; i++) {

                this.relachement(a, i);
            }
        }

        // récupération du chemins de sommets entre départ et arrivée
        sommetsParcourus.clear();

        int arr = arrivee;                                      //Récopie du sommet de d'arrivéé pour manipulation(éventuel changement de sa valeur à venir)
        //contient les sommets parcouru dans l'ordre arrivée ---> départ
        boolean sortie = false;
        while (sortie == false) {                                                    // on retrace les sommets parcouru en partant du sommet d'arrivée pour atteindre le sommet départ

            sommetsParcourus.add(arr);
            arr = this.predecesseur[arr];
            if (arr == -1) {
                sortie = true;
            }

        }                                  //pas bésoin d'ajouter le prédécesseur du sommet de départ

        /*System.out.print("sommets parcourus :");
        for (int j = sommetsParcourus.size() - 1; j >= 0; j--) { //affichage du sommet de départ au sommet d'arrivée(le dernier sommet ajouté étant le sommet de départ)
            System.out.print(" --> " + sommetsParcourus.get(j));
        }
        System.out.println(" ");*/
        return distance[arrivee];
    }

    public static ArrayList<Integer> getParcours() {     // permet de récupérer l'itinéraire entre départ et arrivée du calcul de distance

        return sommetsParcourus;
    }

}

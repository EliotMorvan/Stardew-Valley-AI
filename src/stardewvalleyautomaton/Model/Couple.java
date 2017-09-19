/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stardewvalleyautomaton.Model;

/**
 *
 * @author simonetma
 * Classe couple qui représente un couple d'entier 
 */
public class Couple {

    private int X;
    private int Y;
    
   
    //constructeur
    public Couple(int _X,int _Y) {
        this.X = _X;
        this.Y = _Y;
    }
        
    //getteur
    public int getX() {
        return this.X;
    }
    
    public int getY() {
        return this.Y;
    }
    
    //gestion de l'egalité
    @Override
    public boolean equals(Object o) {
        if (o instanceof Couple) {
            Couple c = (Couple) o;
            return (this.X == c.X) && (this.Y == c.Y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.X;
        hash = 79 * hash + this.Y;
        return hash;
    }
}

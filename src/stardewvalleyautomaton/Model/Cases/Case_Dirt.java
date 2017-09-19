/*
 * case de dirt
 */
package stardewvalleyautomaton.Model.Cases;

/**
 *
 * @author simonetma
 */
public class Case_Dirt extends Case {

    public Case_Dirt(int _ligne, int _colonne) {
        super(_ligne, _colonne);
    }

    @Override
    public Enum_Case getType() {
        return Enum_Case.dirt;
    }
    
}

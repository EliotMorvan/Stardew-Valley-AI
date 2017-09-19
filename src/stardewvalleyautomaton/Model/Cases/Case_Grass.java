/*
 * case de grass
*/
package stardewvalleyautomaton.Model.Cases;

/**
 *
 * @author simonetma
 */
public class Case_Grass extends Case {

    public Case_Grass(int _ligne, int _colonne) {
        super(_ligne, _colonne);
    }

    @Override
    public Enum_Case getType() {
        return Enum_Case.grass;
    }
    
}

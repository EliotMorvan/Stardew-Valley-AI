/*
 * case de lightgrass
 */
package stardewvalleyautomaton.Model.Cases;

/**
 *
 * @author simonetma
 */
public class Case_LightGrass extends Case {

    public Case_LightGrass(int _ligne, int _colonne) {
        super(_ligne, _colonne);
    }

    @Override
    public Enum_Case getType() {
        return Enum_Case.lightgrass;
    }
    
}

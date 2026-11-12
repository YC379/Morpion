package application;

/**
 * Modèle pur du jeu (sans JavaFX) : grille 3x3, détection de victoire et de match nul.
 * Les cases sont numérotées de 0 à 8, de gauche à droite et de haut en bas.
 */
public class Plateau {

    /** Les 8 alignements gagnants : 3 lignes, 3 colonnes, 2 diagonales. */
    public static final int[][] LIGNES_GAGNANTES = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
        {0, 4, 8}, {2, 4, 6}
    };

    private final char[] cases = new char[9];

    public Plateau() {
        reinitialiser();
    }

    public void reinitialiser() {
        java.util.Arrays.fill(cases, ' ');
    }

    public boolean estLibre(int index) {
        return cases[index] == ' ';
    }

    /** Place le symbole si la case est libre. Retourne true si le coup a été joué. */
    public boolean jouer(int index, char symbole) {
        if (!estLibre(index)) {
            return false;
        }
        cases[index] = symbole;
        return true;
    }

    /** Retourne l'alignement gagnant (3 indices) ou null s'il n'y en a pas. */
    public int[] ligneGagnante() {
        for (int[] l : LIGNES_GAGNANTES) {
            char c = cases[l[0]];
            if (c != ' ' && c == cases[l[1]] && c == cases[l[2]]) {
                return l;
            }
        }
        return null;
    }

    public boolean estPlein() {
        for (char c : cases) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }
}

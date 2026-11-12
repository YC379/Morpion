package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Contrôleur de l'écran de jeu : gère les tours, les scores et l'affichage de la grille.
 * La logique de la grille (victoire, match nul) est déléguée à {@link Plateau}.
 */
public class JeuController {

    private static final String STYLE_BASE =
        "-fx-background-color: #e0e0e0; -fx-background-radius: 10; -fx-font-size: 40;";
    private static final String STYLE_X = "-fx-text-fill: #3498db; " + STYLE_BASE;
    private static final String STYLE_O = "-fx-text-fill: #7f8c8d; " + STYLE_BASE;
    private static final String STYLE_GAGNANT =
        "-fx-text-fill: white; -fx-background-color: #2ecc71; -fx-background-radius: 10; -fx-font-size: 40;";

    // --- État du jeu ---
    private final Plateau plateau = new Plateau();
    private String nomJoueur1;
    private String nomJoueur2;
    private boolean tourDuJoueur1;
    private boolean partieTerminee = false;
    private int scoreJ1 = 0;
    private int scoreJ2 = 0;
    private int matchsNuls = 0;

    // --- Éléments FXML ---
    @FXML private Label labelTour;
    @FXML private Label labelScores;
    @FXML private Button btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09;

    private Button[] boutons;

    @FXML
    private void initialize() {
        boutons = new Button[] {btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09};
    }

    /** Appelée par ConfigController juste après le chargement de l'écran. */
    public void initialiserDonnees(String nomJ1, String nomJ2, boolean joueur1Commence) {
        this.nomJoueur1 = nomJ1;
        this.nomJoueur2 = nomJ2;
        this.tourDuJoueur1 = joueur1Commence;
        mettreAJourAffichageTour();
        mettreAJourAffichageScores();
    }

    // --- Menu ---

    @FXML
    public void actionQuitter(ActionEvent event) {
        Platform.exit();
    }

    /** Relance une manche en conservant les scores ; le joueur qui n'a pas commencé commence. */
    @FXML
    public void actionReset(ActionEvent event) {
        plateau.reinitialiser();
        for (Button b : boutons) {
            b.setText("");
            b.setStyle(STYLE_BASE);
        }
        partieTerminee = false;
        tourDuJoueur1 = !tourDuJoueur1;
        mettreAJourAffichageTour();
    }

    /** Retour à l'écran de configuration (les scores sont remis à zéro). */
    @FXML
    public void actionNouvellePartie(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Config.fxml"));
        Parent root = loader.load();
        ConfigController config = loader.getController();
        config.preremplir(nomJoueur1, nomJoueur2);

        Stage fenetre = (Stage) labelTour.getScene().getWindow();
        fenetre.setTitle("Configuration de la partie - Morpion");
        fenetre.setScene(new Scene(root, 800, 540));
    }

    @FXML
    public void actionRegles(ActionEvent event) {
        Alert regles = new Alert(Alert.AlertType.INFORMATION);
        regles.setTitle("Règles du jeu");
        regles.setHeaderText("Règles du morpion");
        regles.setContentText(
            "Deux joueurs jouent à tour de rôle : le joueur 1 place des X, le joueur 2 des O.\n\n"
          + "Le premier à aligner 3 symboles (ligne, colonne ou diagonale) remporte la manche.\n"
          + "Si la grille est pleine sans alignement, la manche est nulle.\n\n"
          + "Menu Partie > Nouvelle manche : rejoue en gardant les scores.");
        regles.initOwner(labelTour.getScene().getWindow());
        regles.showAndWait();
    }

    // --- Clic sur une case de la grille ---

    @FXML
    public void caseClick(ActionEvent event) {
        if (partieTerminee) {
            return;
        }

        Button clique = (Button) event.getSource();
        int index = indexDe(clique);
        char symbole = tourDuJoueur1 ? 'X' : 'O';

        if (!plateau.jouer(index, symbole)) {
            return; // case déjà occupée
        }
        clique.setText(String.valueOf(symbole));
        clique.setStyle(tourDuJoueur1 ? STYLE_X : STYLE_O);

        int[] ligne = plateau.ligneGagnante();
        if (ligne != null) {
            partieTerminee = true;
            for (int i : ligne) {
                boutons[i].setStyle(STYLE_GAGNANT);
            }
            if (tourDuJoueur1) scoreJ1++; else scoreJ2++;
            mettreAJourAffichageScores();
            labelTour.setText("Fin de la manche ! Vainqueur : " + (tourDuJoueur1 ? nomJoueur1 : nomJoueur2));
            return;
        }

        if (plateau.estPlein()) {
            partieTerminee = true;
            matchsNuls++;
            mettreAJourAffichageScores();
            labelTour.setText("Match nul !");
            return;
        }

        tourDuJoueur1 = !tourDuJoueur1;
        mettreAJourAffichageTour();
    }

    // --- Utilitaires ---

    private int indexDe(Button bouton) {
        for (int i = 0; i < boutons.length; i++) {
            if (boutons[i] == bouton) {
                return i;
            }
        }
        throw new IllegalStateException("Bouton inconnu : " + bouton);
    }

    private void mettreAJourAffichageTour() {
        String nomEnCours = tourDuJoueur1 ? nomJoueur1 + " (X)" : nomJoueur2 + " (O)";
        labelTour.setText("C'est le tour de : " + nomEnCours);
    }

    private void mettreAJourAffichageScores() {
        labelScores.setText("Scores : " + nomJoueur1 + " (" + scoreJ1 + ") - "
                + nomJoueur2 + " (" + scoreJ2 + ")  |  Nuls : " + matchsNuls);
    }
}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Contrôleur de l'écran de configuration : pseudos des joueurs et choix du premier joueur.
 */
public class ConfigController {

    @FXML private TextField FieldJoueur1;
    @FXML private TextField FieldJoueur2;
    @FXML private RadioButton BoutonJoueur1;
    @FXML private RadioButton BoutonJoueur2;
    @FXML private RadioButton BoutonAleatoire;

    /** Pré-remplit les champs (utile quand on revient depuis l'écran de jeu). */
    public void preremplir(String nomJ1, String nomJ2) {
        FieldJoueur1.setText(nomJ1);
        FieldJoueur2.setText(nomJ2);
    }

    @FXML
    public void BoutonJouerClick(ActionEvent event) throws Exception {
        String nomJ1 = FieldJoueur1.getText().trim();
        String nomJ2 = FieldJoueur2.getText().trim();
        if (nomJ1.isEmpty()) nomJ1 = "Joueur 1";
        if (nomJ2.isEmpty()) nomJ2 = "Joueur 2";

        boolean joueur1Commence = true;
        if (BoutonJoueur2.isSelected()) {
            joueur1Commence = false;
        } else if (BoutonAleatoire.isSelected()) {
            joueur1Commence = Math.random() < 0.5;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeu.fxml"));
        Parent root = loader.load();
        JeuController jeu = loader.getController();
        jeu.initialiserDonnees(nomJ1, nomJ2, joueur1Commence);

        Stage fenetre = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fenetre.setTitle("Jeu - Morpion");
        fenetre.setScene(new Scene(root, 800, 540));
        fenetre.show();
    }
}

# ❌⭕ Morpion - Jeu de Tic-Tac-Toe en JavaFX

---

## 📝 Présentation

Morpion est une application de bureau développée en **JavaFX** permettant de jouer au tic-tac-toe à deux joueurs sur le même ordinateur. Ce projet académique a été réalisé en **6 heures** dans le cadre de ma première année de BUT Informatique (IUT de Laval), avec pour objectif de pratiquer la conception d'interfaces graphiques et la séparation entre logique de jeu et affichage.

---

## 🌟 Fonctionnalités Principales

* **Écran de configuration :** Saisie des pseudos des deux joueurs et choix du premier joueur (Joueur 1, Joueur 2 ou aléatoire).
* **Grille 3×3 interactive :** Jeu à la souris, le Joueur 1 joue les **X** et le Joueur 2 les **O**.
* **Détection de victoire :** Vérification des 3 lignes, 3 colonnes et 2 diagonales, avec mise en évidence de l'alignement gagnant.
* **Détection du match nul :** La manche s'arrête automatiquement lorsque la grille est pleine sans vainqueur.
* **Scores persistants :** Les victoires de chaque joueur et les matchs nuls sont conservés entre les manches.
* **Menu complet :** Nouvelle manche, changement de joueurs, règles du jeu et bouton quitter.

---

## 🛠️ Technologies & Outils

* **Langage utilisé :** Java 17
* **Interface graphique :** JavaFX 21 (fichiers FXML, conçus avec **Scene Builder**)
* **Gestion de projet et dépendances :** Maven (`pom.xml`, lancement via `javafx-maven-plugin`)
* **Architecture :** Contrôleurs JavaFX (`ConfigController`, `JeuController`) et modèle indépendant de l'interface (`Plateau`) pour la logique de la grille

---

## 🚀 Comment lancer le projet

**Prérequis :** Avoir Java (version 17 minimum) installé sur votre machine. JavaFX n'a pas besoin d'être installé, Maven le télécharge automatiquement.

1. Clonez ce dépôt : `git clone https://github.com/YC379/Morpion.git`
2. Ouvrez le dossier dans votre IDE (IntelliJ, Eclipse, etc.) en sélectionnant le fichier `pom.xml`, puis laissez Maven importer les dépendances.
3. Exécutez le fichier `Main.java` situé dans le dossier `src/main/java/application` pour lancer le programme.

Vous pouvez aussi lancer le jeu directement en ligne de commande avec : `mvn javafx:run`

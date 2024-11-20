/*
 * auteur khalil chafik
*/ 
public class Cellule {
    private Element contenu;
    private Cellule suivant;

    // Constructeur
    public Cellule(Element contenu) {
        this.contenu = contenu;
        this.suivant = null;
    }

    // Getters et Setters
    public Element getContenu() {
        return contenu;
    }

    public void setContenu(Element contenu) {
        this.contenu = contenu;
    }

    public Cellule getSuivant() {
        return suivant;
    }

    public void setSuivant(Cellule suivant) {
        this.suivant = suivant;
    }

    // Méthode toString pour afficher le contenu de la cellule
    @Override
    public String toString() {
        return contenu.toString(); // Appel de la méthode toString() de l'élément contenu
    }
}

/*
 * auteur khalil chafik
*/ 
public class Noeud {
    private Element element;
    private Noeud filsDroit;
    private Noeud filsGauche;

    public Noeud(Element element) {
        this.element = element;
        this.filsDroit = null;
        this.filsGauche = null;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Noeud getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(Noeud filsDroit) {
        this.filsDroit = filsDroit;
    }

    public Noeud getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(Noeud filsGauche) {
        this.filsGauche = filsGauche;
    }

    @Override
    public String toString() {
        return element.toString(); // Utilisation de la méthode toString() de la classe Element
    }
}
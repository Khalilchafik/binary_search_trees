/*
 * auteur khalil chafik
*/ 
import java.util.Random;
public class Element {
    private int cle;
    private double valeur;

    public Element(int cle, double valeur) {
        this.cle = cle;
        this.valeur = valeur;
    }

    public int getCle() {
        return cle;
    }

    public double getValeur() {
        return valeur;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
public static Element[] genererTableauAleatoire(int taille) {
        Element[] tableau = new Element[taille];
        Random rand = new Random();

        for (int i = 0; i < taille; i++) {
            int cle = rand.nextInt(1000001); // Clé aléatoire entre 0 et 1 000 000
            double valeur = rand.nextDouble() * 100; // Valeur numérique aléatoire entre 0.0 et 100.0
            tableau[i] = new Element(cle, valeur);
        }

        return tableau;
    }
    @Override
    public String toString() {
        return "(" + cle + ", " + valeur + ")";
    }
}
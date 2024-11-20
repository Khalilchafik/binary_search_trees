/*
 * auteur khalil chafik
*/ 
import java.util.Random;
public class ArbreBinaireRecherche {
    private Noeud racine;

    public ArbreBinaireRecherche() {
        this.racine = null;
    }

    public ArbreBinaireRecherche(Noeud racine) {
        this.racine = racine;
    }

    public Noeud getRacine() {
        return racine;
    }

    public void setRacine(Noeud racine) {
        this.racine = racine;
    }

    public boolean inserer(Element element) {
        if (racine == null) {
            racine = new Noeud(element);
            return true;
        } else {
            return insererRec(racine, element);
        }
    }

    private boolean insererRec(Noeud noeud, Element element) {
        if (element.getCle() == noeud.getElement().getCle()) {
            return false; // Élément de même clé trouvé, insertion impossible
        }

        if (element.getCle() < noeud.getElement().getCle()) {
            if (noeud.getFilsGauche() == null) {
                noeud.setFilsGauche(new Noeud(element));
                return true;
            } else {
                return insererRec(noeud.getFilsGauche(), element);
            }
        } else {
            if (noeud.getFilsDroit() == null) {
                noeud.setFilsDroit(new Noeud(element));
                return true;
            } else {
                return insererRec(noeud.getFilsDroit(), element);
            }
        }
    }

    public Noeud rechercher(int valeur) {
        return rechercherRec(racine, valeur);
    }

    private Noeud rechercherRec(Noeud noeud, int valeur) {
        if (noeud == null || valeur == noeud.getElement().getCle()) {
            return noeud;
        }

        if (valeur < noeud.getElement().getCle()) {
            return rechercherRec(noeud.getFilsGauche(), valeur);
        } else {
            return rechercherRec(noeud.getFilsDroit(), valeur);
        }
    }

   public boolean supprimer(Element e) {
    racine = supprimerRec(racine, e);
    return racine != null; // Vérifie si la racine a été mise à jour
}

private Noeud supprimerRec(Noeud n, Element e) {
    if (n == null) {
        return null; // L'élément n'est pas présent dans cet arbre, suppression impossible
    } else {
        if (e.getCle() < n.getElement().getCle()) {
            n.setFilsGauche(supprimerRec(n.getFilsGauche(), e));
        } else if (e.getCle() > n.getElement().getCle()) {
            n.setFilsDroit(supprimerRec(n.getFilsDroit(), e));
        } else {
            // Element found
            if (n.getFilsGauche() == null) {
                return n.getFilsDroit();
            } else if (n.getFilsDroit() == null) {
                return n.getFilsGauche();
            } else {
                Noeud successeurParent = n; // Parent du successeur
                Noeud successeur = n.getFilsDroit();
                while (successeur.getFilsGauche() != null) {
                    successeurParent = successeur;
                    successeur = successeur.getFilsGauche();
                }
                // Copie des valeurs du successeur dans le nœud à supprimer
                n.getElement().setCle(successeur.getElement().getCle());
                n.getElement().setValeur(successeur.getElement().getValeur());
                // Suppression du successeur
                if (successeurParent == n) {
                    // Le successeur est le fils droit du nœud à supprimer
                    n.setFilsDroit(successeur.getFilsDroit());
                } else {
                    // Le successeur est un descendant à gauche du nœud à supprimer
                    successeurParent.setFilsGauche(successeur.getFilsDroit());
                }
            }
        }
        return n;
    }
}


public void afficher() {
    int hauteur = afficher(racine, 0); 
    System.out.println("\nHauteur de l'ABR : " + hauteur);
}

private int afficher(Noeud noeud, int hauteur) {
    if(noeud == null){
        return hauteur;
    }else {
        int hauteurG = afficher(noeud.getFilsGauche(), hauteur + 1);
        if (noeud.getElement() != null) {
            System.out.println(noeud.getElement().getCle() + " : " + noeud.getElement().getValeur());
        }
        int hauteurD = afficher(noeud.getFilsDroit(), hauteur + 1);
        return  Math.max(hauteurG, hauteurD);
    }
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

  public static void main(String[] args) {
    // les tests donnés 
    // Création de l'arbre binaire de recherche
    // ArbreBinaireRecherche arbre = new ArbreBinaireRecherche();

    // // Insertion des éléments successifs
    // arbre.inserer(new Element(12, 1.3));
    // arbre.inserer(new Element(25, 2.1));
    // arbre.inserer(new Element(7, 3.6));
    // arbre.inserer(new Element(9, 4.3));
    // arbre.inserer(new Element(11, 5.2));
    // arbre.inserer(new Element(4, 6.8));
    // arbre.inserer(new Element(1, 7.4));

    // // Affichage des éléments de l'ABR et calcul de la hauteur
    // System.out.println("Affichage des éléments de l'ABR :");
    // arbre.afficher();

    // // Recherche des éléments
    // System.out.println("Recherche de l'élément 11 : " + arbre.rechercher(11).getElement());
    // System.out.println("Recherche de l'élément 4 : " + arbre.rechercher(4).getElement());
    // System.out.println("Recherche de l'élément 13 : " + arbre.rechercher(13));

    // // Suppression de l'élément (1,7.4)
    // arbre.supprimer(new Element(1, 7.4));

    // // Insertion des éléments (5,112), (20,123) et (22,320)
    // arbre.inserer(new Element(5, 112));
    // arbre.inserer(new Element(20, 123));
    // arbre.inserer(new Element(22, 320));

    // // Suppression de l'élément (12,1.3)
    // arbre.supprimer(new Element(12, 1.3));

    // // Affichage des éléments de l'ABR après les modifications et calcul de la hauteur
    // System.out.println("Affichage des éléments de l'ABR après les modifications :");
    // arbre.afficher();
    // ------------------------------------------------------------------- //
   // Remplir le tableau d'éléments avec des clés aléatoires
   ArbreBinaireRecherche arbre2 = new ArbreBinaireRecherche();
 // Générer et insérer 10 000 éléments aléatoires
    Element[] elements = genererTableauAleatoire(10000);
    for (Element element : elements) {
        arbre2.inserer(element);
    }
 // Insérer 1000 éléments supplémentaires
    Element[] elements_ajoutes = genererTableauAleatoire(1000);
    double tempsInsertion = 0;
    for (Element element : elements_ajoutes) {
        long debut = System.currentTimeMillis();
        arbre2.inserer(element);
        long fin = System.currentTimeMillis();
        tempsInsertion += (fin - debut);
    }

    // Afficher le temps d'insertion des 1000 éléments supplémentaires
    System.out.println("Temps d'insertion des  éléments supplémentaires : " + tempsInsertion + " ms");

    // Sélectionner aléatoirement 1000 éléments du premier tableau pour la suppression ultérieure
    Element[] ASUPPRIMER = new Element[1000];
    int ASUPPRIMERIndex = 0;
    Random rand = new Random();
    for (Element element : elements) {
        if (rand.nextDouble() < 0.15 && ASUPPRIMERIndex < 1000) {
            ASUPPRIMER[ASUPPRIMERIndex] = element;
            ASUPPRIMERIndex++;
        }
        if (ASUPPRIMERIndex == 1000) {
            break;
        }
    }
    // Suppression des 1000 éléments aléatoires (contenus dans ASUPPRIMER) et calcul du temps de suppression 
    double tempsSuppressionTotal = 0;
    for (int i = 0; i < 1000; i++) {
        long debut = System.currentTimeMillis();
        arbre2.supprimer(ASUPPRIMER[i]);
        long fin = System.currentTimeMillis();
        tempsSuppressionTotal += (fin - debut);
    }

    // Afficher le temps de suppression
    System.out.println("Temps de suppression : " + tempsSuppressionTotal + " ms");
}
}

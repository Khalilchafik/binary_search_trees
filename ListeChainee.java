/*
 * auteur khalil chafik
*/ 
import java.util.Random;
public class ListeChainee {
    // Attributs
    protected Cellule tete;
    protected Cellule queue;
    public ListeChainee() {
        tete = null;
        queue = null;
    }

    public ListeChainee(Cellule _tete) {
        tete = _tete;
        queue = tete;
        Cellule temp = tete;
        if (temp != null) {
            while (temp.getSuivant() != null) {
                temp = temp.getSuivant();
            }
            queue = temp;
        }
    }
    // Méthode pour obtenir la tête de la liste
    public Cellule getTete() {
        return tete;
    }

    // Ajoute un élément en tête de la liste
    public void ajouteTete(Element e) {
        Cellule nouvelle = new Cellule(e);
        if (estVide()) {
            tete = nouvelle;
            queue = nouvelle;
        } else {
            nouvelle.setSuivant(tete);
            tete = nouvelle;
        }
    }

    // Retire la tête de la liste
    public void retireTete() {
        if (!estVide()) {
            tete = tete.getSuivant();
            if (tete == null) {
                queue = null; // Si la liste est vide après le retrait de la tête, mettre à jour la queue
            }
        }
    }

    // Ajoute un élément en queue de la liste
    public void ajouteQueue(Element e) {
        Cellule nouvelle = new Cellule(e);
        if (estVide()) {
            tete = nouvelle;
            queue = nouvelle;
        } else {
            queue.setSuivant(nouvelle);
            queue = nouvelle;
        }
    }

    // Retire la queue de la liste
    public void retireQueue() {
        if (!estVide()) {
            if (tete == queue) {
                tete = queue = null;
            } else {
                Cellule avantQueue = tete;
                while (avantQueue.getSuivant() != queue) {
                    avantQueue = avantQueue.getSuivant();
                }
                avantQueue.setSuivant(null);
                queue = avantQueue;
            }
        }
    }

    // Autres méthodes de la classe ListeChainee (estVide, enTete, enQueue, etc.)

    // Méthode pour vérifier si la liste est vide
    public boolean estVide() {
        return (tete == null);
    }

    // Retourne l'élément en tête de la liste
    public Element enTete() {
        if (tete == null) {
            return null;
        }
        return tete.getContenu();
    }

    // Retourne l'élément en queue de la liste
    public Element enQueue() {
        if (queue == null) {
            return null;
        }
        return queue.getContenu();
    }

    // Méthodes d'insertion et de suppression

    // Insère un élément à une position spécifique dans la liste
    public void inserer(Element e, int position) {
        if (position <= 0) {
            System.out.println("Position non valide");
            return;
        }

        Cellule nouvelle = new Cellule(e);

        if (estVide() || position == 1) {
            ajouteTete(e);
            return;
        }

        Cellule precedente = tete;
        int currentPosition = 1;

        while (precedente.getSuivant() != null && currentPosition < position - 1) {
            precedente = precedente.getSuivant();
            currentPosition++;
        }

        if (currentPosition == position - 1) {
            nouvelle.setSuivant(precedente.getSuivant());
            precedente.setSuivant(nouvelle);
            if (nouvelle.getSuivant() == null) {
                queue = nouvelle;
            }
        } else {
            System.out.println("Position après la fin de la liste, pas d'insertion");
        }
    }

    // Supprime l'élément à une position spécifique dans la liste
    public void supprimer(int position) {
        if (position <= 0) {
            System.out.println("Position non valide");
            return;
        }
        if (position == 1) {
            retireTete();
            return;
        }

        Cellule precedente = null;
        Cellule courante = tete;
        int currentPosition = 1;

        while (courante != null && currentPosition < position) {
            precedente = courante;
            courante = courante.getSuivant();
            currentPosition++;
        }

        if (courante != null) {
            precedente.setSuivant(courante.getSuivant());
            if (courante == queue) {
                queue = precedente;
            }
            courante.setSuivant(null);
        } else {
            System.out.println("Position après la fin de la liste, pas de suppression");
        }
    }

    // Méthode pour afficher le contenu de la liste chaînée
   @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Cellule courante = tete;
    while (courante != null) {
        sb.append(courante.getContenu());
        if (courante.getSuivant() != null) {
            sb.append(" -> ");
        }
        courante = courante.getSuivant();
    }
    sb.append("]");
    return sb.toString();
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

    // Méthode de recherche d'un élément dans la liste par clé
    public Element rechercher(int cle) {
        return rechercherRec(cle, this.getTete());
    }

    // Méthode récursive de recherche d'un élément dans la liste par clé
    private Element rechercherRec(int cle, Cellule c) {
        if (c == null) {
            return null;
        } else {
            if (c.getContenu().getCle() == cle) {
                return c.getContenu();
            } else {
                return rechercherRec(cle, c.getSuivant());
            }
        }
    }
    
    // Méthode main pour tester la classe ListeChainee
    public static void main(String[] args) {
        //  Création d'une liste chaînée
        ListeChainee liste1 = new ListeChainee();

        // Insertion des éléments successifs
        liste1.inserer(new Element(12, 1.3), 1); // Insérer en tête
        liste1.inserer(new Element(25, 2.1), 2);
        liste1.inserer(new Element(7, 3.6), 3);
        liste1.inserer(new Element(9, 4.3), 4);
        liste1.inserer(new Element(11, 5.2), 5);
        liste1.inserer(new Element(4, 6.8), 6);
        liste1.inserer(new Element(1, 7.4), 7); // Insérer en queue

        // Affichage du contenu de la liste
        System.out.println("Contenu de la liste chaînée après insertion des éléments :");
        System.out.println(liste1);

        // Recherche d'éléments dans la liste
        System.out.println("Recherche de l'élément avec la clé 11 : " + liste1.rechercher(11));
        System.out.println("Recherche de l'élément avec la clé 4 : " + liste1.rechercher(4));
        System.out.println("Recherche de l'élément avec la clé 13 : " + liste1.rechercher(13));

        // Suppression et insertion d'éléments
        liste1.supprimer(7); 
        liste1.inserer(new Element(5, 112), 5);
        liste1.inserer(new Element(20, 123), 3);
        liste1.inserer(new Element(22, 320), 7);
        liste1.supprimer(1);

        

        // Affichage du contenu après les modifications
        System.out.println("Contenu après suppression et insertion :");
        System.out.println(liste1);
         // Génération d'un tableau d'éléments aléatoires
        
        // Génération des 10 000 éléments aléatoires
    Element[] elements = Element.genererTableauAleatoire(10000);
    
    // Création de la liste chaînée
    ListeChainee liste = new ListeChainee();

    // Insérer les 10 000 éléments sans mesurer le temps d'insertion
    for (Element element : elements) {
        liste.inserer(element, 1); // Insérer en tête pour éviter le tri
    }

    // Générer les 1000 éléments supplémentaires
    Element[] elements_ajoutes = Element.genererTableauAleatoire(1000);

    // Mesurer le temps d'insertion des 1000 éléments supplémentaires
    long debutInsertionSupplementaire = System.currentTimeMillis();
    for (Element element : elements_ajoutes) {
        liste.inserer(element, 1); // Insérer en tête pour éviter le tri
    }
    long finInsertionSupplementaire = System.currentTimeMillis();
    long tempsInsertionSupplementaire = finInsertionSupplementaire - debutInsertionSupplementaire;

    // Afficher le temps d'insertion des 1000 éléments supplémentaires
    System.out.println("Temps d'insertion des éléments supplémentaires : " + tempsInsertionSupplementaire + " ms");

    // Sélection aléatoire des 1000 éléments à supprimer
    Element[] aSupprimer = new Element[1000];
    Random random = new Random();
    for (int i = 0; i < 1000; i++) {
        if (random.nextDouble() < 0.15 && i < 1000) {
            aSupprimer[i] = elements[i];
        }
    }

    // Calcul du temps de suppression des 1000 éléments sélectionnés
    long debutSuppression = System.currentTimeMillis();
    for (Element element : aSupprimer) {
        if (element != null) {
            liste.supprimer(element.getCle());
        }
    }
    long finSuppression = System.currentTimeMillis();
    long tempsSuppression = finSuppression - debutSuppression;

    // Afficher le temps de suppression
    System.out.println("Temps de suppression : " + tempsSuppression + " ms");
}
}
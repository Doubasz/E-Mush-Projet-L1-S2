package tp;

import java.util.LinkedList;
import java.util.Random;



public class Equipement
{
    private final String nom;
    private int progression;
    private int quantite;
    private final Vaisseau vaisseau;
    private boolean isFixe;
    private boolean isPassif;

    /*
        Maya Mahboub
     */
    private static final String[] listEquipement = {"Mycoscan", "Gaz antispore", "Sérum de constispaspore", "Savon mushicide", "Sérum rétro-fongique", "Extracteur de spores", "Accélération du processeur", "Arroseurs automatiques", "Conduite oxygénées", "Bouclier plasma", "Réducteur de trainée", "Douche", "Lits", "Jet d'attaque", "NERON", "PILGRED", "Terminal planète", "Navigation", "Terminale de recherche"};
    private boolean isFonctionnel;

    /*
     *   Boudia Yanis Ferhat
     *    Constructeur de la classe affecte les valeurs selon l'equipement
     */
    public Equipement(String name,Vaisseau vaisseau)
    {
        this.nom = name;
        this.progression = 0;
        this.isFonctionnel = true;
        this.vaisseau = vaisseau;

        if(name.equals(listEquipement[0]) || name.equals(listEquipement[11]) || name.equals(listEquipement[14]) || name.equals(listEquipement[15]) || name.equals(listEquipement[16]) || name.equals(listEquipement[17]) || name.equals(listEquipement[18]))
        {
            this.isFixe = true;
            this.isPassif = false;
            this.quantite = 1;
        }
        else if(name.equals(listEquipement[3]) || name.equals(listEquipement[4]) || name.equals(listEquipement[5]))
        {
            this.isFixe = false;
            this.isPassif = false;
            this.quantite = 1;
        }
        else if(name.equals(listEquipement[6]) || name.equals(listEquipement[7]) || name.equals(listEquipement[8]) || name.equals(listEquipement[9]) || name.equals(listEquipement[10]) || name.equals(listEquipement[1]) || name.equals(listEquipement[2]))
        {
            this.isFixe = true;
            this.isPassif = true;
            this.quantite = 1;
        }
        else if(name.equals(listEquipement[12]) || name.equals(listEquipement[13]))
        {
            this.isFixe = true;
            this.isPassif = false;
            this.quantite = 3;
        }
        if(name.equals(listEquipement[11]) || name.equals(listEquipement[12]) || name.equals(listEquipement[13]) || name.equals(listEquipement[14]) || name.equals(listEquipement[16]) || name.equals(listEquipement[17]) || name.equals(listEquipement[18]))
        {
            this.progression = 100;
        }
    }

    public String getNom() {
        return nom;
    }


    public int getProgression() {return this.progression;}

    public void tentative(Joueur joueur)
    {
        Random random = new Random();

        if(this.progression != 100)
        {
            if(this.nom.equals("Savon mushicide"))
            {
                if(joueur.have("Savon")) this.progression += random.nextInt(3, 5);
                else System.out.println("Le savon est nécessaire pour effectuer cette recherche!");
            }
            if(this.nom.equals("Sérum rétro-fongique"))
            {
                if(joueur.getNom().equals("Zhong Chun") && joueur.have("Souche de test mush")) this.progression += random.nextInt(1, 5);
                else System.out.println("Nécessite Chun et la souche de test mush!");
            }
            switch (this.nom)
            {
                case "Mycoscan" -> this.progression += random.nextInt(3, 11);
                case "Gaz antispore", "Accélération du processeur" -> this.progression += random.nextInt(6, 10);
                case "Sérum de constispaspore", "Conduite oxygénées" -> this.progression += random.nextInt(10, 16);
                case "Extracteur de spores" -> this.progression += random.nextInt(3, 8);
                case "Arroseurs automatiques" -> this.progression += random.nextInt(10, 13);
                case "Bouclier plasma" -> this.progression += random.nextInt(20, 31);
                case "Réducteur de trainée" -> this.progression += random.nextInt(2, 4);
                case "PILGRED" -> this.progression += 10;
            }
        }

        if(joueur.aCompetence("Biologiste")) this.progression += 10;
        if (this.progression > 100)
        {
            this.progression = 100;
        }

        if(this.progression == 100)
        {
            if(this.nom.equals("Savon mushicide")) joueur.getInventaire().add(new Objets("Savon mushicide"));
            if(this.nom.equals("Sérum rétro-fongique")) joueur.getInventaire().add(new Objets("Sérum rétro-fongique"));
            if(this.nom.equals("Extracteur de spores")) joueur.getInventaire().add(new Objets("Extracteur de spores"));
        }

        System.out.println("La progression est: " + this.progression);
    }

    /*
     *   Boudia Yanis Ferhat
     *   Initialise les équipements dans la classe Jeu
     */
    public static LinkedList<Equipement> innitEquipement(Vaisseau vaisseau)
    {
        LinkedList<Equipement> list = new LinkedList<>();

        for (int i = 0; i < listEquipement.length; i++)
        {
            Equipement equipement = new Equipement(listEquipement[i], vaisseau);
            for (int j = 0; j < equipement.quantite; j++)
            {
                list.add(equipement);
                affecterEquipement(vaisseau, equipement);
            }
        }
        return list;
    }

    public boolean getIsFonctionnel() {return this.isFonctionnel;}
    public void setIsFonctionnel(boolean b) {this.isFonctionnel = b;}

    public String toString() {return this.nom;}

    /*
     *   Boudia Yanis Ferhat
     *   Affecte les équipement dans le vaisseau selon les salles
     */
    public static void affecterEquipement(Vaisseau vaisseau, Equipement equip)
    {
        if(equip.nom.equals(listEquipement[0]) || equip.nom.equals(listEquipement[1]) || equip.nom.equals(listEquipement[2]) || equip.nom.equals(listEquipement[3]) || equip.nom.equals(listEquipement[4]) || equip.nom.equals(listEquipement[5]) || equip.nom.equals(listEquipement[18]))
        {
            vaisseau.getSalle(vaisseau.salles[6]).ajoutEquipement(equip);
        }

        if(equip.nom.equals(listEquipement[6]) || equip.nom.equals(listEquipement[7]) || equip.nom.equals(listEquipement[8]) || equip.nom.equals(listEquipement[9]) || equip.nom.equals(listEquipement[10]))
            vaisseau.getSalle(vaisseau.salles[19]).ajoutEquipement(equip);


            if(equip.nom.equals(listEquipement[11]) || equip.nom.equals(listEquipement[12]))
                {
                    vaisseau.getSalle(vaisseau.salles[14]).ajoutEquipement(equip);
                    vaisseau.getSalle(vaisseau.salles[17]).ajoutEquipement(equip);
                }
                else if(equip.nom.equals(listEquipement[13]))
                {
                    vaisseau.getSalle(vaisseau.salles[11]).ajoutEquipement(equip);
                    vaisseau.getSalle(vaisseau.salles[12]).ajoutEquipement(equip);
                    vaisseau.getSalle(vaisseau.salles[20]).ajoutEquipement(equip);
                }
                else if(equip.nom.equals(listEquipement[14])) vaisseau.getSalle(vaisseau.salles[19]).ajoutEquipement(equip);
                else if(equip.nom.equals(listEquipement[16]) || equip.nom.equals(listEquipement[17])) vaisseau.getSalle(vaisseau.salles[1]).ajoutEquipement(equip);

        if(equip.nom.equals(listEquipement[15])) vaisseau.getSalle(vaisseau.salles[26]).ajoutEquipement(equip);
    }
}

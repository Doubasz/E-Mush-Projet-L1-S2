package tp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Vaisseau
{
    final int MAXARMOR = 200;
    final int MAXOXYGENE = 500;
    final int MAXFUEL = 50;
    private int armor;
    private int oxygene;
    private int fuel;

    final public String[] salles = {"Tourelle Alpha avant", "Pont", "Tourelle Beta avant", "Couloir avant", "Jardin Hydrponique", "Stockage Avant", "Laboratoire", "Infirmerie", "Tourelle Alpha centre", "Tourelle Beta centre", "Couloir Central", "Baie Alpha", "Baie Beta", "Stockage Alpha centre", "Dortoir Alpha", "Réfectoire", "Stockage Beta centre", "Dortoir Beta", "Couloir arrière", "Nexus", "Baie Alpha 2", "Baie Icarus", "Stockage Alpha arrière", "Stockage Beta arrière", "Tourelle Alpha arrière", "Tourelle Beta arrière", "Salle des moteurs"};
    private HashSet<Salle> plan = new HashSet<>();
    private LinkedList<Integer> listeVaisseauAlien = new LinkedList<>();
    private Planete planeteProximite;

    public Salle getSalle(int ID){
        for (Salle salle : this.plan){
            if (salle.getId() == ID)
                return salle;
        }
        return null;
    }
    public Salle getSalle(String s)
    {
        for(Salle salle: this.plan)
        {
            if(salle.getNom().equals(s))
            {
                return salle;
            }
        }
        return null;
    }

    public int getOxygene() {
        return oxygene;
    }

    public void setOxygene(int oxygene) {
        if(oxygene > MAXOXYGENE) this.oxygene = MAXOXYGENE;
        else if(oxygene < 0) this.oxygene = 0;
        else this.oxygene = oxygene;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getFuel() {return this.fuel;}

    public void setFuel(int fuel)
    {
        if(fuel > MAXFUEL) this.fuel = MAXFUEL;
        else if(fuel < 0) this.fuel = 0;
        else this.fuel = fuel;
    }

    public Planete getPlaneteProximite() {return this.planeteProximite;}

    public HashSet<Salle> getPlan() {
        return this.plan;
    }

    boolean[][] initPortes(){
        boolean[][] tab = new boolean[salles.length][salles.length];

        tab[0][1] = true;
        tab[0][3] = true;
        tab[2][1] = true;
        tab[2][3] = true;
        tab[1][3] = true;
        tab[3][4] = true;
        tab[3][5] = true;
        tab[3][6] = true;
        tab[3][7] = true;
        tab[3][10] = true;
        tab[4][5] = true;
        tab[5][8] = true;
        tab[6][7] = true;
        tab[7][9] = true;
        tab[8][11] = true;
        tab[9][12] = true;
        tab[10][11] = true;
        tab[10][12] = true;
        tab[10][15] = true;
        tab[11][13] = true;
        tab[11][14] = true;
        tab[11][20] = true;
        tab[12][16] = true;
        tab[12][17] = true;
        tab[12][18] = true;
        tab[14][18] = true;
        tab[17][18] = true;
        tab[18][19] = true;
        tab[18][20] = true;
        tab[18][22] = true;
        tab[18][23] = true;
        tab[18][21] = true;
        tab[20][24] = true;
        tab[20][26] = true;
        tab[21][26] = true;
        tab[21][25] = true;
        tab[22][26] = true;
        tab[23][26] = true;
        tab[24][26] = true;
        tab[25][26] = true;

        for (int i = 0; i < this.salles.length ; i++){
            for(int j = 0 ; j < this.salles.length; j++){
                if(tab[i][j])
                {
                    tab[j][i] = true;
                }
                else
                {
                    tab[i][j] = false;
                }
            }
        }
        return tab;
    }
    public Vaisseau(){
        this.armor = this.MAXARMOR;
        this.fuel = this.MAXFUEL;
        this.oxygene = this.MAXOXYGENE;
        this.planeteProximite = new Planete();
        boolean[][] portes = initPortes();

        for (int i = 0 ; i < this.salles.length ; i++) {
            Salle nouveau = new Salle(salles[i] , this , i);
            this.plan.add(nouveau);
        }
        for(int i = 0 ; i < this.salles.length ; i++){
            for(int j = 0 ; j < this.salles.length; j++){
                if( portes[i][j])
                    (getSalle(i)).ajoutSalle(getSalle(j));
            }
        }
    }


    public String toString(){
        String sortie = "Structure du vaisseau : \n";
        for (Salle salle : this.plan)
            sortie += salle.toString() + "\n";
        return sortie;


    }

    public void reparerPilgred(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 3);
        this.getSalle("Salle des moteurs").getEquipements().get(this.getSalle("Salle des moteurs").returnIndice("PILGRED")).tentative(joueur);
    }

    public LinkedList<Integer> getListeVaisseauAlien() {return listeVaisseauAlien;}

    public void scannerPlanete(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 3);
        Random random = new Random();
        int indice = -1;
        LinkedList<Integer> dejaTrue = new LinkedList<>();

        for(int i = 0; i < 3; i++)
        {
            if(this.planeteProximite.getScanRessources()[i]) dejaTrue.add(i);
        }

        this.planeteProximite.setScanRessources(true, getRandomExclusion(random, 0, 2, dejaTrue));

        if(this.planeteProximite.getScanRessources()[0]) System.out.println("Oxygènes disponible: " + this.planeteProximite.getRessources()[0]);
        if(this.planeteProximite.getScanRessources()[1]) System.out.println("Fuel disponible: " + this.planeteProximite.getRessources()[1]);
        if(this.planeteProximite.getScanRessources()[2]) System.out.println("Débris métaliques disponible: " + this.planeteProximite.getRessources()[2]);
    }

    public int getRandomExclusion(Random random, int origine, int fin, LinkedList<Integer> list)
    {
        int rand = origine + random.nextInt(fin - origine + 1 - list.size());
        for(int exclu : list)
        {
            if(rand < exclu) break;
            rand++;
        }
        return rand;
    }

    public void lancerExpedition(Joueur joueur)
    {
        if(joueur.have("Combinaisons")){
            joueur.setPa(joueur.getPa() - 3);
            if(this.planeteProximite.getScanRessources()[0]) this.setOxygene(this.oxygene + this.planeteProximite.getRessources()[0]);
            if(this.planeteProximite.getScanRessources()[1]) this.setFuel(this.fuel + this.planeteProximite.getRessources()[1]);
            if(this.planeteProximite.getScanRessources()[2])
            {
                for(int i = 0; i < this.planeteProximite.getRessources()[2]; i++)
                {
                    this.getSalle("Baie Icarus").setStockage(new Objets("Débris métalliques"));
                }
            }
            joueur.getSalleActuelle().ajouterActionsRecents(Joueur.getAction()[10], joueur);
        }else joueur.setPv(0);

    }

    public void deplacerDaedalus(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 3);
        this.setFuel(this.fuel - this.planeteProximite.getDistance());
        this.planeteProximite.setDistance(0);
    }
}
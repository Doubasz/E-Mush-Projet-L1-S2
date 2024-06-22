package tp;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Joueur
{
    final int MAXPM = 12;
    final int MAXPA = 12;
    final int MAXPV = 14;
    final int MAXPMO = 14;
    private int id ;
    private String nom;
    private int pm;
    private int pa;
    private int pv;
    private int pmo;
    private int nbSpores;
    private boolean isMush;
    private boolean isPropre;
    private boolean isOrdi;
    private boolean isAlive;
    private boolean isCouche;
    private Vaisseau vaisseau;
    private Salle salleActuelle;
    private static final String[] role = {"Wang Chao", "Zhong Chun", "Eleesha Williams", "Finola Keegan", "Frieda Bergmann", "Gioele Rinaldao", "Jiang Hua", "Ian Soulton", "Janice Kent", "Kim Jin Su", "Lai Kuan-Ti", "Paola Rinaldo", "Racula Tomescu", "Roland Zuccali", "Stephen Seagull", "Terrence Archer"};
    private String[] competence = new String[2];
    public static final String[] competancesJoueurs = initcompet();
    private boolean passerTour;
    private int apoiconner;
    private LinkedList<Objets> inventaire = new LinkedList<>();
    private LinkedList<String> actionPossible =  new LinkedList<>();
    private static final String[] action = {"Consulter le journal de bord", "Consulter le canal de communication", "Ecrire dans le canal de communication", "Consulter l'historique des 10 dernières actions effectués", "Consulter les entrées/sorties dans la salle", "Attaquer à main nue", "Attaquer avec un blaster/couteau", "Détecter une planète à proximité", "Scanner la planète", "Attaquer un vaisseau alien", "Lancer une expédition", "Déplacer le Daedalus", "Rentrer sur Terre", "Regarder dans l'unité de stockage", "Déposer un objet dans le stockage", "Prendre un objet dans le stockage", "Cacher un objet dans le stockage", "Fouiller le stockage", "Soigner", "Caresser le chat", "Se coucher dans un lit", "Se lever du lit", "Réparer un équipement", "Réparer PILGRED", "Se doucher", "Installer une caméra", "Désinstaller une caméra", "Faire une recherche", "Faire avancer un projet", "Changer de salle", "Terminer son tour", "Créer une spore", "Poiconner un joueur", "Poiconner le chat", "Infecter une ration", "Manger un spore", "Consulter le canal mush", "Ecrire dans le canal mush", "Saboter un équipement", "Utiliser Mycoscan", "Eteindre l'incendie", "Utiliser Extracteur de spores"};


    //Boudia Yanis Ferhat
    public Joueur(String name, Vaisseau vaisseau)
    {
        this.nom = name;
        this.pm = MAXPM;
        this.pa = MAXPA;
        this.pv = MAXPV;
        this.pmo = 7;
        this.nbSpores = 0;
        this.apoiconner = 0;
        this.isAlive = true;
        this.isCouche = false;
        this.isPropre = true;
        this.vaisseau = vaisseau;

        initcomp();

    }
    public Joueur(){}

    public String getNom() {
        return nom;
    }

    public void setSalleActuelle(Salle salleActuelle) {

        this.salleActuelle = salleActuelle;
        this.salleActuelle.addNbJoueur(1);
    }

    public void setPv(int pv) {
        if(pv < 0)
        {
            this.pv = 0;
            this.isAlive = false;
        }
        else this.pv = pv;
    }

    public int getPv() {
        return pv;
    }

    public int getId() {
        return id;
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        if(pa < 0) this.pa = 0;
        else this.pa = pa;
    }

    public int getPmo() {
        return pmo;
    }

    public void setPmo(int pmo) {
        if(pmo < 0) this.pmo = 0;
        else if (pmo > MAXPMO) this.pmo = MAXPMO;
        else this.pmo = pmo;
    }

    public int getNbSpores() {return this.nbSpores;}

    public void setNbSpores(int n) {this.nbSpores = n;}

    public void setId(int Id) {
        this.id = Id;
    }

    public boolean isCouche() {
        return isCouche;
    }

    public static String[] getAction() {return action;}
    public void ajouterobjet(Objets obj){
        if(this.inventaire.size() < 3){
            this.inventaire.add(obj);
            this.salleActuelle.getStockage().remove(obj);
        }else{
            System.out.println("Votre inventaire est complet");
        }
    }

    //Boudia Yanis Ferhat
    public static Joueur[] innitJoueur(Vaisseau vaisseau)
    {
        Joueur[] joueurs = new Joueur[role.length];

        for(int i = 0; i < role.length; i++)
        {
            Joueur play = new Joueur(Joueur.getRole()[i], vaisseau);
            joueurs[i] = play;
        }
        return joueurs;
    }
    public static String[] getRole() {
        return role;
    }

    public Salle getSalleActuelle() {
        return salleActuelle;
    }

    //Damiche Hanni
    boolean have(String obj){
        for (Objets objets : this.inventaire) {
            if (objets != null){
                if (objets.getNom().equals(obj))
                    return true;
            }
        }
        return false;
    }

    //Damiche Hanni
    public Objets getObjetByNom(String obj)
    {
        for (Objets objets : this.inventaire) {
            if (objets != null){
                if (objets.getNom().equals(obj))
                    return objets;
            }
        }
        return null;
    }



    public void initcomp()
    {
        if(this.nom.equals(role[0]))
        {
            this.competence[0] = "Tireur";
            this.competence[1] = "Bourreau";
        }
        else if(this.nom.equals(role[1]))
        {
            this.competence[0] = "Seul Espoir";
            this.competence[1] = "Infirmier";
        }
        else if(this.nom.equals(role[2]))
        {
            this.competence[0] = "Traqueur";
            this.competence[1] = "Observateur";
        }
        else if(this.nom.equals(role[3]))
        {
            this.competence[0] = "Biologiste";
            this.competence[1] = "Infirmier";
        }
        else if(this.nom.equals(role[4]))
        {
            this.competence[0] = "Astrophysicien";
            this.competence[1] = "Pilote";
        }
        else if(this.nom.equals(role[5]))
        {
            this.competence[0] = "Robuste";
            this.competence[1] = "Paranoiaque";
        }
        else if(this.nom.equals(role[6]))
        {
            this.competence[0] = "Pilote";
            this.competence[1] = "Technicien";
        }
        else if(this.nom.equals(role[7]))
        {
            this.competence[0] = "Biologiste";
            this.competence[1] = "Mycologiste";
        }
        else if(this.nom.equals(role[8]))
        {
            this.competence[0] = "Psy";
            this.competence[1] = "Informaticien";
        }
        else if(this.nom.equals(role[9]))
        {
            this.competence[0] = "Leadeur";
            this.competence[1] = "Pilote";
        }
        else if(this.nom.equals(role[10]))
        {
            this.competence[0] = "Concepteur";
            this.competence[1] = "Optimiste";
        }
        else if(this.nom.equals(role[11]))
        {
            this.competence[0] = "Logistique";
            this.competence[1] = "Tireur";
        }
        else if(this.nom.equals(role[12]))
        {
            this.competence[0] = "Physicien";
            this.competence[1] = "Détaché";
        }
        else if(this.nom.equals(role[13]))
        {
            this.competence[0] = "Sprinter";
            this.competence[1] = "Pilote";
        }
        else if(this.nom.equals(role[14]))
        {
            this.competence[0] = "Cuistot";
            this.competence[1] = "Robuste";
        }
        else if(this.nom.equals(role[15]))
        {
            this.competence[0] = "Technicien";
            this.competence[1] = "Tireur";
        }
    }
    static String[] initcompet(){
        String[] t = new String[16];
        t[0] =  "Tireur : peut tirer 2 fois par jour sans utiliser\n" + "de PA, s’il a une arme dans son inventaire\n" + "Bourreau : pour 1 PA, fait perdre 1 PV à la\n" + "cible puis révèle autant de ses dernières\n" +
                "actions qu’il lui manque de PV. ";
        t[1] =  "Seul espoir : elle ne peut pas être contaminée\n" +
                "par un mush ni être mush en début de partie\n" +
                "Infirmier : peut soigner gratuitement 1 joueur\n" +
                "chaque jour (+4 PV)";
        t[2] =  "Traqueur : permet d’accéder aux historiques\n" +
                "de déplacements\n" + "Observateur : l’action fouiller coûte 1 PA de\n" +
                "moins";
        t[3] = "Biologiste : +10% de bonus sur les projets de\n" +
                "recherche dans le laboratoire\n" + "Infirmier : peut soigner gratuitement 1 joueur\n" +
                "chaque jour (+4 PV)";
        t[4] = "Astrophysicien : l’action « scanner une\n" +
                "planète » coûte 1 PA de moins et permet de\n" +
                "découvrir un secteur de plus par rapport à un\n" +
                "autre joueur\n" + "Pilote : peut piloter un des jets d’attaque ou\n" +
                "le vaisseau d’exploration";
        t[5] = "Robuste : les attaques de corps-à-corps\n" +
                "infligent 1 point de dégât supplémentaire (-\n" +
                "1PV)\n" + "Paranoïaque : commence avec deux caméras\n" + "dans son inventaire, en plus de celles\n" +
                "présentes dans le vaisseau ";
        t[6] = "Pilote : peut piloter un des jets d’attaque ou\n" +
                "le vaisseau d’exploration\n" +
                "Technicien : peut réparer gratuitement 1\n" +
                "équipement par jour";
        t[7] = "Biologiste : +10% de bonus sur les projets de\n" +
                "recherche dans le laboratoire\n" +
                "Mycologiste : lorsque vous soignez un\n" +
                "équipier, il perd automatiquement un spore";
        t[8] = "Psy : peut réconforter un joueur pour 1 PA ce\n" +
                "qui lui rend 2 PMO\n" +
                "Informaticien : donne 2 actions gratuites\n" +
                "utilisables pour des projets NERON " ;
        t[9] = "Leader : pour 2 PA, prononce un discours qui\n" +
                "rend 2 PMO à tous les joueurs présents dans\n" +
                "la même salle que lui\n" +
                "Pilote : peut piloter un des jets d’attaque ou\n" +
                "le vaisseau d’exploration";
        t[10] = "Concepteur : +2 PA par jour\n" +
                "Optimiste : perd 1 PMO de moins par jour";
        t[11] = "Logistique : à chaque fin de cycle, distribue 1\n" +
                "PA supplémentaire à un joueur choisi au\n" +
                "hasard dans la salle\n" +
                "Tireur : peut tirer 2 fois par jour sans utiliser\n" +
                "de PA, s’il a une arme dans son inventaire";
        t[12] = "Physicien : 1 action gratuite par jour pour\n" +
                "réparer PILGRED\n" +
                "Détaché : pas de perte de PMO lorsqu’un\n" +
                "joueur meurt";
        t[13] = "Sprinter : 2 PM supplémentaires en cas de\n" +
                "conversion d’1 PA\n" +
                "Pilote : peut piloter un des jets d’attaque ou\n" +
                "le vaisseau d’exploration";
        t[14] = "Sprinter : 2 PM supplémentaires en cas de\n" +
                "conversion d’1 PA\n" +
                "Pilote : peut piloter un des jets d’attaque ou\n" +
                "le vaisseau d’exploration";
        t[15] = "Technicien : peut réparer gratuitement 1\n" +
                "équipement par jour\n" +
                "Tireur : peut tirer 2 fois par jour sans utiliser\n" +
                "de PA, s’il a une arme dans son inventaire";

        return t;

    }

    //Damiche Hanni
    void seDeplacer(){
        Scanner read = new Scanner(System.in);
        int nbSalle = 1;
        int choix;

        for(Salle s : this.salleActuelle.getSallesVoisines()){
            System.out.println(nbSalle + "-" + s);
            nbSalle++;
        }
        if(this.isOrdi)
        {
            Random random = new Random();
            choix = random.nextInt(0, nbSalle);
        }
        else
        {
            System.out.println("Choisissez le numero de la salle ");
            while(true) {
                try {
                    String temp = read.nextLine();
                    choix = Integer.parseInt(temp);
                    if (choix > nbSalle) {
                        System.out.println("Entrez un choix valide!");
                    } else {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("entrez un choix valide!");

                }
            }
        }


            this.salleActuelle.addNbJoueur(-1);
            this.salleActuelle.getEnso().add(this.nom + " est sortie de " + this.salleActuelle.getNom());
            this.salleActuelle = this.salleActuelle.getSallesVoisines().get(choix-1);
            this.salleActuelle.addNbJoueur(1);
            this.salleActuelle.getEnso().add(this.nom + " est entrée dans " + this.salleActuelle.getNom());
            this.salleActuelle.ajouterActionsRecents(action[29], this);
            this.innitAction();
    }

    //Boudia Yanis Ferhat
    public String etatJoueur()
    {
        String joueur ;
        if(this.isAlive){
            joueur = "\t<Etat du joueur>\nNom: " + this.nom + "\nPoint de vie: " + this.pv + "\n";
            joueur += "Point d'action: " + this.pa + "\nPoint de mouvement: " + this.pm + "\nPoint de moral: " + this.pmo;
            joueur += "\nEtat: ";
            if(this.isAlive) joueur += "Vivant\n"; else joueur += "Mort\n";
            joueur += "Etat Mush: ";
            if(this.isMush) joueur += "Mush\n"; else joueur += "Humain\n";
            joueur += "Salle actuelle: " + this.salleActuelle + "\n";
            joueur += "Ses compétences sont: " + this.competence[0] + " et " + this.competence[1] + "\n";
            joueur += "Votre Inventaire Contient : ";
            for(Objets objets : this.inventaire){
                joueur += objets.getNom() + ", ";
            }
        }else joueur = "Vous etes mort!! \nVous tour va passer";


        return joueur;
    }

    public String toString()
    {
        return this.nom;
    }

    public LinkedList<Objets> getInventaire() {
        return this.inventaire;
    }

    //Damiche Hanni
    boolean aCompetence(String competence){
        if(this.competence.length > 0){
            if (this.competence[0].equals(competence) || this.competence[1].equals(competence))
                return true;
        }

        return false;
    }

    public int getApoiconner() {
        return apoiconner;
    }

    public void setApoiconner(int apoiconner) {
        this.apoiconner = apoiconner;
    }

    public void setMush(boolean mush) {
        isMush = mush;
    }

    //Boudia Yanis Ferhat
    public void reparerEquipement()
    {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> stockageIndice = new LinkedList<>();
        int choix = 0;
        this.pa = this.pa - 1;
        if(!this.have("Paire de gants de protection")) this.isPropre = false;


        for(int i = 0; i < this.getSalleActuelle().getEquipements().size(); i++)
        {
            if(!this.getSalleActuelle().getEquipements().get(i).getIsFonctionnel())
            {
                System.out.println((i + 1) + "- " + this.getSalleActuelle().getEquipements().get(i));
                stockageIndice.add(i);
            }
        }
        if(this.isOrdi)
        {
            Random random = new Random();
            choix = random.nextInt(0, stockageIndice.size());
        }
        else
        {
            System.out.print("Choisissez l'équipement à réparer: ");
            choix = scanner.nextInt();
        }
        this.getSalleActuelle().getEquipements().get(stockageIndice.get(choix - 1)).setIsFonctionnel(true);
    }

    //Damiche Hanni
    void creerSpore(){
        if(this.vaisseau.getSalle("Laboratoire").getEquipements().get(this.vaisseau.getSalle("Laboratoire").returnIndice("Sérum de constipaspore")).getProgression() == 100) this.pa = this.pa - 4;
        else this.pa = this.pa - 2;
        this.nbSpores = nbSpores + 1;
        this.isPropre = false;

    }

    //Damiche Hanni
    void seDoucher(){
        if(this.have("Savon")){
            this.pa = this.pa - 1;
        }else{
            this.pa = this.pa -2;
        }
        if(this.have("Savon mushicide")) this.setNbSpores(this.nbSpores - 1);
        if(this.isMush)
            this.pv = this.pv - 3;
        this.isPropre = true;
        this.getSalleActuelle().ajouterActionsRecents(action[24], this );
    }

    //Damiche Hanni
    void installerCamera(){
        if(this.getSalleActuelle().isCamera()){
            this.pa = this.pa - 4;
            this.getSalleActuelle().setCamera(true);
            this.getSalleActuelle().ajouterActionsRecents(action[25], this);
        }else {
            System.out.println("Il ya deja une camera dans la salle ou vous vous trouvez");
        }

    }

    //Damiche Hanni
    void desinstallerCamera(){
        this.pa = this.pa - 4;
        this.getSalleActuelle().setCamera(false);
        this.getSalleActuelle().ajouterActionsRecents(action[26], this);
    }

    void eteindreFeu(){
        this.salleActuelle.setIncendie(false);
    }

    //Boudia Yanis Ferhat
    public void faireRecherche()
    {
        int choix = 0;
        this.pa = this.pa - 2;
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> stockageIndice = new LinkedList<>();


        for(int i = 0; i < this.getSalleActuelle().getEquipements().size(); i++)
        {
            if(this.getSalleActuelle().getEquipements().get(i).getProgression() != 100)
            {
                System.out.println((i + 1) + "- " + this.getSalleActuelle().getEquipements().get(i));
                stockageIndice.add(i);
            }
        }

        if(this.isOrdi)
        {
            Random random = new Random();
            choix = random.nextInt(0, stockageIndice.size());
        }
        else
        {
            System.out.println("Choisissez l'équipement à rechercher: ");
            choix = scanner.nextInt();
        }

        this.getSalleActuelle().getEquipements().get(stockageIndice.get(choix - 1)).tentative(this);
        if(!this.have("Tablier intachable")) this.isPropre = false;
    }

    public void utiliserMycoscan()
    {
        System.out.println("Le nombre de spore est: " + this.nbSpores);
    }

    public void utiliserExtraxteur()
    {
        this.setNbSpores(this.nbSpores - 1);
    }

    //Boudia Yanis Ferhat
    public void innitAction()
    {
        this.actionPossible.clear();

        //Les Actions gratuites
        if(!this.isOrdi)
        {
            this.actionPossible.add(action[0]);
            this.actionPossible.add(action[1]);
            this.actionPossible.add(action[2]);
            this.actionPossible.add(action[3]);
            this.actionPossible.add(action[13]);
        }


        this.actionPossible.add(action[14]);
        this.actionPossible.add(action[15]);
        this.actionPossible.add(action[30]);

        if(this.salleActuelle.getNom().contains("Dortoir"))
        {
            this.actionPossible.add(action[20]);
        }
        if(this.isCouche){
            this.actionPossible.clear();
            this.actionPossible.add(action[21]);
            this.actionPossible.add(action[30]);
            return;
        }

        if(this.pa == 0 && this.pm == 0)
        {
            this.actionPossible.clear();
            this.actionPossible.add(action[30]);
        }

        if(this.isMush)
        {
            this.actionPossible.add(action[36]);
            this.actionPossible.add(action[37]);
        }

        if(this.salleActuelle.isIncendie() && this.have("Extincteurs")) this.actionPossible.add(action[40]);

        if (this.vaisseau.getSalle("Laboratoire").getEquipements().get(this.vaisseau.getSalle("Laboratoire").returnIndice("Mycoscan")).getProgression() == 100) this.actionPossible.add(action[39]);

        if(this.have("Extracteur de spores")) this.actionPossible.add(action[41]);

        //Les actions qui coutent 1 PA
        if((this.pa - 1) >= 0)
        {
            if(this.aCompetence("Traqueur"))
            {
                this.actionPossible.add(action[4]);
            }

            if(this.salleActuelle.getNbJoueur() > 1)
            {
                if(this.have("Blasters") || this.have("Couteau"))
                    this.actionPossible.add(action[6]);
            }
            if ((this.salleActuelle.getNom().contains("Tourelle") || this.salleActuelle.getNom().contains("Baie")) && this.salleActuelle.fonctionne("Jet d'attaque"))
            {
                this.actionPossible.add(action[9]);
            }
            this.actionPossible.add(action[16]);
            if(this.have("Chat de Schrodinger")) this.actionPossible.add(action[19]);
            if(this.salleActuelle.nbEquipementNonFonctionel() > 0 && this.have("Clé à molette")) this.actionPossible.add(action[22]);
            if(this.isMush)
            {
                if(this.salleActuelle.have("Rations standards")) this.actionPossible.add(action[34]);
                if(this.have("Spore")) this.actionPossible.add(action[35]);
            }
        }


        //Les actions qui coutent 2 PA
        if((this.pa - 2) >= 0)
        {
            if(this.salleActuelle.getNbJoueur() > 1)
            {
                this.actionPossible.add(action[5]);
            }

            this.actionPossible.add(action[17]);
            if(this.have("Médikit")) this.actionPossible.add(action[18]);
            if(this.salleActuelle.getNom().contains("Dortoir"))
            {
                this.actionPossible.add(action[24]);
            }
            if(this.salleActuelle.getNom().equals("Laboratoire")) this.actionPossible.add(action[27]);
            if(this.salleActuelle.getNom().equals("Nexus")) this.actionPossible.add(action[28]);
            if(this.isMush)
            {
                if(this.salleActuelle.getNbJoueur() > 1 && this.nbSpores > 0) this.actionPossible.add(action[32]);
                if(this.have("Chat de Schrodinger") && this.nbSpores > 0) this.actionPossible.add(action[33]);
            }
        }


        //Les actions qui coutent 3 PA
        if((this.pa - 3) >= 0)
        {
            if(this.salleActuelle.getNom().equals("Pont"))
            {
                if (this.vaisseau.getPlaneteProximite().getIsScan())
                {
                    this.actionPossible.add(action[8]);
                }
                if (this.aCompetence("Pilote"))
                {
                    if (this.vaisseau.getFuel() >= this.vaisseau.getPlaneteProximite().getDistance())
                    {
                        this.actionPossible.add(action[11]);
                    }
                }
            }
            if(this.vaisseau.getPlaneteProximite().getDistance() == 0 && this.aCompetence("Pilote") && this.salleActuelle.getNom().equals("Baie Icarus"))
            {
                this.actionPossible.add(action[10]);
            }
            if(this.getSalleActuelle().getNom().equals("Salle des moteurs")) this.actionPossible.add(action[23]);
            if(this.isMush)
            {
                if(!this.salleActuelle.getEquipements().isEmpty() && !this.salleActuelle.tousNonFonctionel()) this.actionPossible.add(action[38]);
            }
        }

        //Les actions qui coutent 4 PA
        if((this.pa - 4) >= 0)
        {
            if(this.have("Caméras"))
            {
                this.actionPossible.add(action[25]);
                this.actionPossible.add(action[26]);
            }
        }


        //Les actions qui coutent 5 PA
        if((this.pa - 5) >= 0)
        {
            if(this.salleActuelle.getNom().equals("Pont")) {

                if (this.vaisseau.getSalle("Salle des moteurs").getEquipements().get(this.vaisseau.getSalle("Salle des moteurs").returnIndice("PILGRED")).getProgression() == 100) {
                    this.actionPossible.add(action[12]);
                }
            }
        }

        if((this.pm - 1) >= 0) this.actionPossible.add(action[29]);

        if(!(this.vaisseau.getSalle("Laboratoire").returnIndice("Sérum de constipaspore") == -1))
        {
            if(this.vaisseau.getSalle("Laboratoire").getEquipements().get(this.vaisseau.getSalle("Laboratoire").returnIndice("Sérum de constipaspore")).getProgression() == 100)
            {
                if(this.isMush)
                {
                    if(this.pa - 4 >= 0)
                    {
                        if(this.salleActuelle.getNbJoueur() == 1) this.actionPossible.add(action[31]);
                    }
                }
            }
            else
            {
                if(this.isMush)
                {
                    if(this.pa - 2 >= 0)
                    {
                        if(this.salleActuelle.getNbJoueur() == 1) this.actionPossible.add(action[31]);
                    }
                }
            }
        }
        

        if(this.vaisseau.getSalle("Nexus").getEquipements().get(this.vaisseau.getSalle("Nexus").returnIndice("Accélération du processeur")).getProgression() == 100)
        {
            if(this.pa - 2 >= 0)
            {
                if(this.salleActuelle.getNom().equals("Pont"))
                {
                    if (!this.vaisseau.getPlaneteProximite().getIsScan())
                    {
                        this.actionPossible.add(action[7]);
                    }
                }
            }
        }
        else
        {
            if(this.pa - 1 >= 0)
            {
                if(this.salleActuelle.getNom().equals("Pont"))
                {
                    if (!this.vaisseau.getPlaneteProximite().getIsScan())
                    {
                        this.actionPossible.add(action[7]);
                    }
                }
            }
        }
        if(this.have("Grenade") || this.have("Rations standards")) this.actionPossible.add(action[42]);
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public LinkedList<String> getActionPossible() {
        return actionPossible;
    }

    public boolean getPasserTour() {
        return passerTour;
    }

    //Damiche Hanni
    void voirEntreSorte(){
        for(String strnig : this.salleActuelle.getEnso())
            System.out.println(strnig);
        this.salleActuelle.ajouterActionsRecents(action[4], this);
    }

    public void setPasserTour(boolean passerTour) {this.passerTour = passerTour;}


    public void setIsCouche(boolean b) {this.isCouche = b;}

    void seCoucher() {this.isCouche = true;}

    void seLever() {this.isCouche = false;}

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    public void setIsPropre(boolean b) {this.isPropre = b;}

    public boolean isMush() {return this.isMush;}

    public boolean isOrdi() {return this.isOrdi;}

    public void setOrdi(boolean b) {this.isOrdi = b;
    }
}
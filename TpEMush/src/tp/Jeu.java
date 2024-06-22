package tp;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {

    private LinkedList<String> canalMsg = new LinkedList<>();
    private LinkedList<String> canalMsgMush = new LinkedList<>();
    private final Vaisseau vaisseau;
    private Joueur[] players = new Joueur[16];
    private int cycle;
    private int jour;
    private LinkedList<Objets> objetsJeu;
    private LinkedList<Equipement> equipementsJeu;
    private boolean victoire;
    private int nbmush;




    public Jeu() {
        this.cycle = 1;
        this.jour = 1;
        this.vaisseau = new Vaisseau();
        this.objetsJeu = Objets.innitObjet(this.vaisseau);
        this.players = Joueur.innitJoueur(this.vaisseau);
        this.equipementsJeu = Equipement.innitEquipement(this.vaisseau);
        this.victoire = false;
    }

    public int getCycle() {
        return cycle;
    }
    public Vaisseau getVaisseau() {
        return this.vaisseau;
    }

    public Joueur[] getPlayers() {
        return this.players;
    }

    public boolean isVictoire() {
        return victoire;
    }

    public int getJour() {
        return jour;
    }

    public void setVictoire(boolean victoire) {
        this.victoire = victoire;
    }

    Joueur getJoueur(String nom) {
        for (Joueur joueur : this.players) {
            if (joueur.getNom().equals(nom))
                return joueur;
        }
        return null;
    }

    /*
     * Boudia Yanis Ferhat
     */
    public void consulterCanal(Joueur joueur) {
        if(joueur.isOrdi())
        {
            return;
        }
        else
        {
            for (String msg : this.canalMsg) {
                System.out.println(msg);
            }
            //ajouter action recentes
        }
    }

    //Damiche Hanni
    public void consulterCanalMush() {
        for (String msg : this.canalMsgMush) {
            System.out.println(msg);
        }
    }

    public int getNbmush() {
        return nbmush;
    }

    public void setNbmush(int nbmush) {
        this.nbmush = nbmush;
    }

    //Damiche Hanni
    void ecrireMsg(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ecrire votre message: ");
        String msg = scanner.nextLine();
        this.canalMsg.add(joueur.getNom() + " : " + msg);
        //ajouter action recentes
    }

    //Damiche Hanni
    void ecrireMsgMush(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ecrire votre message: ");
        String msg = scanner.nextLine();
        this.canalMsgMush.add(joueur.getNom() + " : " + msg);

    }

    public LinkedList<Equipement> getEquipementsJeu() {
        return equipementsJeu;
    }

    //Damiche Hanni
    void afficherJournal(Joueur joueur) {
            String cam = "";
            String inc = " Il y'a un incendie dans les salles suivantes : {";
            String alien = "Il y'a ";
            for (Salle s : vaisseau.getPlan()) {
                if (s.isCamera()) {
                    cam += "Les joueurs présents dans la salle " + s.getNom() + "sont : {";
                    for (Joueur j : players) {
                        if (j.getSalleActuelle() == s) {
                            cam += Joueur.getRole()[j.getId()] + ", ";
                        }
                    }
                    cam += "} \n";

                }
                if (s.isIncendie()) {
                    inc += s.getNom() + ", ";
                }
            }
            inc += "}";
            alien += vaisseau.getListeVaisseauAlien().size() + " vaisseau(x) alien(s)";
            System.out.println(cam);
            System.out.println(inc);
            System.out.println(alien);
            //ajouter action recentes

    }

    //Damiche Hanni
    public LinkedList<Integer> afficherJoueur(Joueur joueur) {
        int j = 1;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < this.players.length; i++) {
            if(this.getPlayers()[i] == joueur)
                continue;
            if(this.players[i].getSalleActuelle() == joueur.getSalleActuelle()){
                System.out.println(j + " - " + this.players[i]);
                list.add(i);
                j++;
            }
        }
        return list;
    }

    //Damiche Hanni
    void attaquerVaisseauAlien(Joueur joueur){

        if (joueur.have("Combinaisons")) {
            Random random = new Random();
            this.getVaisseau().getListeVaisseauAlien().remove(random.nextInt(0, this.getVaisseau().getListeVaisseauAlien().size()));
            joueur.setPa(joueur.getPa() - 1);
        }else joueur.setPv(0);
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public void incJour() {
        this.jour++;
    }

    //Boudia Yanis Ferhat
    public void attaquerMains(Joueur joueur)
    {
            Scanner scanner = new Scanner(System.in);
            LinkedList<Integer> stockageIndice = new LinkedList<>();
            int j = 1;

            for (int i = 0; i < this.players.length; i++) {
                if (this.players[i].getSalleActuelle().equals(joueur.getSalleActuelle())) {
                    System.out.println((j) + "- " + this.players[i]);
                    stockageIndice.add(i);
                    j++;
                }
            }
            if(joueur.isOrdi())
            {
                Random random = new Random();
                int choix = random.nextInt(0, stockageIndice.size());
                this.players[stockageIndice.get(choix - 1)].setPv(this.players[stockageIndice.get(choix - 1)].getPv() - 1);
                joueur.setPa(joueur.getPa() - 2);
            }
            else
            {
                System.out.print("Choisissez le Joueur à attaquer: ");
                int choix = scanner.nextInt();
                this.players[stockageIndice.get(choix - 1)].setPv(this.players[stockageIndice.get(choix - 1)].getPv() - 1);
                joueur.setPa(joueur.getPa() - 2);

                System.out.println("vous avez perdu 2PA et votre cible 1PV!");
            }
            //ajouter action recentes

    }


    //Boudia Yanis Ferhat
    public void attaqueobjets(Joueur joueur)
    {
            Scanner scanner = new Scanner(System.in);
            LinkedList<Integer> stockageIndice = new LinkedList<>();
            int j = 1;

            for (int i = 0; i < this.players.length; i++) {
                if (this.players[i].getSalleActuelle().equals(joueur.getSalleActuelle())) {
                    System.out.println((j) + "- " + this.players[i]);
                    stockageIndice.add(i);
                    j++;
                }
            }
            if(joueur.isOrdi())
            {
                Random random = new Random();
                int choix1 = random.nextInt(0, stockageIndice.size());
                int choix2 = random.nextInt(1, 3);
                if(choix2 == 1)
                {
                    this.players[stockageIndice.get(choix1 - 1)].setPv(this.players[stockageIndice.get(choix1 - 1)].getPv() - 4);
                }else this.players[stockageIndice.get(choix1 - 1)].setPv(this.players[stockageIndice.get(choix1 - 1)].getPv() - 2);
            }
            else
            {
                System.out.print("Choisissez le Joueur à attaquer: ");
                int choix = scanner.nextInt();
                scanner.nextLine();



                if (joueur.have("Blasters") && joueur.have("Couteau")) {
                    System.out.println("voulez vous attaquer avec  : \n1- Blasters \n2- Couteau");
                    while (true) {
                        try {

                            String temp = scanner.nextLine();
                            int reponse = Integer.parseInt(temp);
                            if(reponse == 1){
                                this.players[stockageIndice.get(choix - 1)].setPv(this.players[stockageIndice.get(choix - 1)].getPv() - 4);
                                break;
                            } else if(reponse == 2){
                                this.players[stockageIndice.get(choix - 1)].setPv(this.players[stockageIndice.get(choix - 1)].getPv() - 2);
                                break;
                            }

                            else System.out.println("Entrez une valeure valide!!!!!!!!!!!!");


                        } catch (Exception e) {
                            System.out.println("Entrez une valeure valide!!!!!!!!!!!!");
                        }
                    }


                } else if (joueur.have("Blasters") && !joueur.have("Couteau")) {
                    this.players[stockageIndice.get(choix - 1)].setPv(this.players[stockageIndice.get(choix - 1)].getPv() - 4);
                    System.out.println("Votre adversaire a perdu 4PV");

                } else {
                    this.players[stockageIndice.get(choix - 1)].setPv(this.players[stockageIndice.get(choix - 1)].getPv() - 2);
                    System.out.println("Votre adversaire a perdu 2PV");
                }
            }

        joueur.setPa(joueur.getPa() - 1);
    }

    //Damiche Hanni
    void rentrerTerre(Joueur joueur){
        joueur.setPa(joueur.getPa() - 5);
        this.setVictoire(true);
        System.out.println("Bravo ! vous etes rentré sur terre \n Les humains ont gagné !!");
    }

    //Damiche Hanni
    void regarder(Joueur joueur){
        System.out.println("Les objets presents dans le stockage de la salle sont : ");
        for(Objets objets : joueur.getSalleActuelle().getStockage()){
            if (!objets.getIsCache()){
                System.out.print(objets.getNom() + ", ");
            }
        }
    }

    //Damiche Hanni
    void deposerObjets(Joueur joueur){
        if(!joueur.getInventaire().isEmpty())
        {
            Scanner scanner = new Scanner(System.in);
            int choix = -1;

            for(int i = 0; i < joueur.getInventaire().size(); i++)
            {
                System.out.println((i + 1) + "- " + joueur.getInventaire().get(i));
            }
            if(joueur.isOrdi())
            {
                Random random = new Random();
                choix = random.nextInt(0, joueur.getInventaire().size());
            }
            else
            {
                System.out.print("Quel objet de votre inventaire voulez vous poser: ");
                choix = scanner.nextInt();

            }
            joueur.getSalleActuelle().getStockage().add(joueur.getInventaire().get(choix - 1));
            joueur.getInventaire().remove(joueur.getInventaire().get(choix - 1));
        }else{
            System.out.println("Votre inventaire est vide");
        }
    }

    //Damiche Hanni
    public void prendreObjet(Joueur joueur){
        if(joueur.getInventaire().size() < 3)
        {
            Scanner scanner = new Scanner(System.in);
            int choix = -1;

            for(int i = 0; i < joueur.getSalleActuelle().getStockage().size(); i++)
            {
                System.out.println((i + 1) + "- " + joueur.getSalleActuelle().getStockage().get(i));
            }

            if(joueur.isOrdi())
            {
                Random random = new Random();
                choix = random.nextInt(0, joueur.getSalleActuelle().getStockage().size());
            }
            else
            {
                System.out.print("Quel objet de votre inventaire voulez vous prendre: ");
                choix = scanner.nextInt();

            }
            joueur.getInventaire().add(joueur.getSalleActuelle().getStockage().get(choix - 1));
            joueur.getSalleActuelle().getStockage().remove(joueur.getSalleActuelle().getStockage().get(choix - 1));

        }else{
            System.out.println("Votre inventaire est complet");
        }
    }

    //Boudia Yanis Ferhat
    void cacherObjets(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 1);
        if(!joueur.getInventaire().isEmpty())
        {
            Scanner scanner = new Scanner(System.in);
            int choix = -1;

            for(int i = 0; i < joueur.getInventaire().size(); i++)
            {
                System.out.println((i + 1) + "- " + joueur.getInventaire().get(i));
            }
            if(joueur.isOrdi())
            {
                Random random = new Random();
                choix = random.nextInt(0, joueur.getInventaire().size());
            }
            else
            {
                System.out.print("Quel objet de votre inventaire voulez vous cacher: ");
                choix = scanner.nextInt();
            }

            joueur.getInventaire().get(choix - 1).setIsCache(true);
            joueur.getInventaire().remove(joueur.getInventaire().get(choix - 1));
            joueur.getSalleActuelle().getStockage().add(joueur.getInventaire().get(choix - 1));
        }else{
            System.out.println("Votre inventaire est vide");
        }
    }

    //Boudia Yanis Ferhat
    void fouiller(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 2);
        System.out.println("Les objets presents dans le stockage de la salle sont : ");
        for(Objets obj : joueur.getSalleActuelle().getStockage())
        {
            if(obj.getIsCache()) obj.setIsCache(false);
            System.out.println("- " + obj.getNom());
        }
    }

    //Boudia Yanis Ferhat
    public void detecterPlanete(Joueur joueur)
    {
        if(this.vaisseau.getSalle("Nexus").getEquipements().get(this.vaisseau.getSalle("Nexus").returnIndice("Accélération du processeur")).getProgression() == 100) joueur.setPa(joueur.getPa() - 1);
        else joueur.setPa(joueur.getPa() - 2);
        this.vaisseau.getPlaneteProximite().setIsScan(true);
        System.out.println("La distance entre le vaisseau et la planète est: " + this.vaisseau.getPlaneteProximite().getDistance());
    }

    //Boudia Yanis Ferhat
    void soigner(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 2);
        Scanner scanner = new Scanner(System.in);
        int choix = -1;
        System.out.println("Choisissez entre:\n 1- Vous soigner\n 2- Soigner un joueur");

            if(joueur.isOrdi())
            {
                Random random = new Random();
                choix = random.nextInt(1, 3);
            }
            else
            {
                while(true) {
                    try
                    {
                        String temp = scanner.nextLine();
                        choix = Integer.parseInt(temp);
                        break;
                    }catch (Exception e)
                    {
                        System.out.println("Entrer une valeur valide!");
                    }
                }
            }

            if(choix == 2)
            {
                LinkedList<Integer> stockageIndice = new LinkedList<>();

                for (int i = 0; i < this.players.length; i++) {
                    if (this.players[i].getSalleActuelle().equals(joueur.getSalleActuelle())) {
                        System.out.println((i + 1) + "- " + this.players[i]);
                        stockageIndice.add(i);
                    }
                }
                if(joueur.isOrdi())
                {
                    Random random = new Random();
                    choix =  random.nextInt(0, stockageIndice.size());
                }
                else
                {
                    System.out.print("Choisissez le Joueur à soigner: ");
                    choix = scanner.nextInt();
                }
                this.players[stockageIndice.get(choix - 1)].setPv(joueur.MAXPV);
            }
            else joueur.setPv(joueur.MAXPV);
    }

    //Damiche Hanni
    void caresserChat(Joueur joueur)
    {
        joueur.setPa(joueur.getPa() - 1);
        if(joueur.getObjetByNom("Chat de Schrodinger").getUsed() < this.getCycle() - 1){
            if(!joueur.getObjetByNom("Chat de Schrodinger").getIsContamine())
                joueur.setPmo(joueur.getPmo() + 1);
            else{
                joueur.setNbSpores(joueur.getNbSpores() + 1);
            }
        }
    }


    //Damiche Hanni
    void poiconnerUnJoueur(Joueur joueur){
        int choix = 0;
        if(joueur.getApoiconner() < jour){
            LinkedList<Integer> stockageindice = afficherJoueur(joueur);

            if(joueur.isOrdi())
            {
                Random random = new Random();
                choix = random.nextInt(0, stockageindice.size());
            }
            else
            {
                System.out.println("quel joueur voulez vous poiconer? ");
                Scanner read = new Scanner(System.in);
                choix = read.nextInt();
            }

            if( this.players[stockageindice.get(choix -1)].isMush()){
                System.out.println("Vous ne pouvez pas ce joueur est deja mush" );
            }else{
                this.players[stockageindice.get(choix -1)].setNbSpores(this.players[stockageindice.get(choix -1)].getNbSpores() + 1);
                joueur.setNbSpores(joueur.getNbSpores() -1);
                if ( this.players[stockageindice.get(choix -1)].getNbSpores() >= 3){
                    this.players[stockageindice.get(choix -1)].setMush(true);
                    this.players[stockageindice.get(choix -1)].setNbSpores(0);
                }
                joueur.setApoiconner(jour);
                joueur.setPa(joueur.getPa() - 2);
                if (joueur.getSalleActuelle().isCamera())
                    joueur.getSalleActuelle().ajouterActionsRecents(Joueur.getAction()[32],joueur);
            }

        }else
            System.out.println("Vous avez deja poissoner");
    }

    //Damiche Hanni
    void utiliserObjet(Joueur joueur){
        int choix;
        for(int i = 0; i < joueur.getInventaire().size(); i++){
            System.out.println(i + 1 + " - " + joueur.getInventaire().get(i));
        }
        if(joueur.isOrdi())
        {
            Random random = new Random();
            choix = random.nextInt();
        }
        else
        {
            Scanner read = new Scanner(System.in);
            System.out.println("Quel objet voulez vous utiliser ? ");
            choix = read.nextInt() ;
        }

        switch (joueur.getInventaire().get(choix - 1).getNom()){
            case "Bombe" :
                System.out.println("BOOOMMM!!");
                LinkedList<Integer> list = afficherJoueur(joueur);
                for (int i = 0;i < list.size(); i++){
                    this.players[i].setPv(this.players[i].getPv() - 6);
                }
                joueur.setPv(joueur.getPv() - 6);
                break;
            case "Rations standards" :
                if(joueur.getInventaire().get(choix - 1).isContamine())
                    joueur.setNbSpores(joueur.getNbSpores() + 1);
                if(joueur.getInventaire().get(choix - 1).isIscuisine()){
                    joueur.setPv(joueur.getPa() + 4);
                    joueur.setPmo(joueur.getPmo() + 2);

                }else{
                    if(joueur.getPmo() > 0){
                        joueur.setPv(joueur.getPa() + 2);
                        joueur.setPmo(joueur.getPmo() - 1);
                    }

                }
                break;
        }
    }

    //Damiche Hanni
    void poiconnerchat(Joueur joueur){
        if(joueur.getApoiconner() < jour){
            joueur.setNbSpores(joueur.getNbSpores() -1);
            joueur.getObjetByNom("Chat de Schrodinger").setContamine(true);
            joueur.setApoiconner(jour);
            joueur.setPa(joueur.getPa() - 2);
            if (joueur.getSalleActuelle().isCamera())
                joueur.getSalleActuelle().ajouterActionsRecents(Joueur.getAction()[33],joueur);
        }else
            System.out.println("Vous avez deja poissoner");
    }

    //Damiche Hanni
    void infecterRation(Joueur joueur){
        if(joueur.getApoiconner() < jour){
            joueur.getObjetByNom("Rations standards").setContamine(true);
            joueur.setApoiconner(jour);
            joueur.setPa(joueur.getPa() - 1);
            if (joueur.getSalleActuelle().isCamera())
                joueur.getSalleActuelle().ajouterActionsRecents(Joueur.getAction()[34],joueur);
        }else
            System.out.println("Vous avez deja poissoner");
    }

    //Damiche Hanni
    void mangerSpore(Joueur joueur){
        joueur.setPa(joueur.getPa() - 1);
        joueur.setPa(joueur.getPa() + 3);
        joueur.setPm(joueur.getPm() + 2);
        joueur.setIsPropre(false);
    }

    //Damiche Hanni
    void saboterEquipement(Joueur joueur){
        int choix = 0;
        joueur.setPa(joueur.getPa() - 3);
        for(int i = 0; i < joueur.getSalleActuelle().getEquipements().size(); i++){
            System.out.println(i + 1 + " - " + joueur.getSalleActuelle().getEquipements().get(i).getNom());
        }
        if(joueur.isOrdi())
        {
            Random random = new Random();
            choix = random.nextInt(0, joueur.getSalleActuelle().getEquipements().size());
        }
        else
        {
            System.out.println("Quels equipement voulez vous saboter? ");
            Scanner read = new Scanner(System.in);
            choix = read.nextInt();
        }


        joueur.getSalleActuelle().getEquipements().get(choix - 1).setIsFonctionnel(false);
    }

    //Boudia Yanis Ferhat
    public void choixAction(Joueur joueur, String choix) {
        if (choix.equals(Joueur.getAction()[0])) this.afficherJournal(joueur);
        else if (choix.equals(Joueur.getAction()[1])) this.consulterCanal(joueur);
        else if (choix.equals(Joueur.getAction()[2])) this.ecrireMsg(joueur);
        else if (choix.equals(Joueur.getAction()[3])) joueur.getSalleActuelle().afficherActionsRecents();
        else if (choix.equals(Joueur.getAction()[4])) joueur.voirEntreSorte();
        else if (choix.equals(Joueur.getAction()[5])) this.attaquerMains(joueur);
        else if (choix.equals(Joueur.getAction()[6])) this.attaqueobjets(joueur);
        else if (choix.equals(Joueur.getAction()[7])) this.detecterPlanete(joueur);
        else if (choix.equals(Joueur.getAction()[8])) this.vaisseau.scannerPlanete(joueur);
        else if (choix.equals(Joueur.getAction()[9])) this.attaquerVaisseauAlien(joueur);
        else if (choix.equals(Joueur.getAction()[10])) this.vaisseau.lancerExpedition(joueur);
        else if (choix.equals(Joueur.getAction()[11])) this.vaisseau.deplacerDaedalus(joueur);
        else if (choix.equals(Joueur.getAction()[12])) this.rentrerTerre(joueur);
        else if (choix.equals(Joueur.getAction()[13])) this.regarder(joueur);
        else if (choix.equals(Joueur.getAction()[14])) this.deposerObjets(joueur);
        else if (choix.equals(Joueur.getAction()[15])) this.prendreObjet(joueur);
        else if (choix.equals(Joueur.getAction()[16])) this.cacherObjets(joueur);
        else if (choix.equals(Joueur.getAction()[17])) this.fouiller(joueur);
        else if (choix.equals(Joueur.getAction()[18])) this.soigner(joueur);
        else if (choix.equals(Joueur.getAction()[19])) this.caresserChat(joueur);
        else if (choix.equals(Joueur.getAction()[20])) joueur.seCoucher();
        else if (choix.equals(Joueur.getAction()[21])) joueur.seLever();
        else if (choix.equals(Joueur.getAction()[22])) joueur.reparerEquipement();
        else if (choix.equals(Joueur.getAction()[23])) this.vaisseau.reparerPilgred(joueur);
        else if (choix.equals(Joueur.getAction()[24])) joueur.seDoucher();
        else if (choix.equals(Joueur.getAction()[25])) joueur.installerCamera();
        else if (choix.equals(Joueur.getAction()[26])) joueur.desinstallerCamera();
        else if (choix.equals(Joueur.getAction()[27])) joueur.faireRecherche();
        else if (choix.equals(Joueur.getAction()[28])) joueur.faireRecherche();
        else if (choix.equals(Joueur.getAction()[29])) joueur.seDeplacer();
        else if (choix.equals(Joueur.getAction()[30])) joueur.setPasserTour(true);
        else if (choix.equals(Joueur.getAction()[31])) joueur.creerSpore();
        else if (choix.equals(Joueur.getAction()[32])) this.poiconnerUnJoueur(joueur);
        else if (choix.equals(Joueur.getAction()[33])) poiconnerchat(joueur);
        else if (choix.equals(Joueur.getAction()[34])) infecterRation(joueur);
        else if (choix.equals(Joueur.getAction()[35])) mangerSpore(joueur);
        else if (choix.equals(Joueur.getAction()[36])) consulterCanalMush();
        else if (choix.equals(Joueur.getAction()[37])) ecrireMsgMush(joueur);
        else if (choix.equals(Joueur.getAction()[38])) this.saboterEquipement(joueur);
        else if (choix.equals(Joueur.getAction()[39])) joueur.utiliserMycoscan();
        else if (choix.equals(Joueur.getAction()[40])) joueur.eteindreFeu();
        else if (choix.equals(Joueur.getAction()[41])) joueur.utiliserExtraxteur();
        else if (choix.equals(Joueur.getAction()[42])) utiliserObjet(joueur);
    }


}

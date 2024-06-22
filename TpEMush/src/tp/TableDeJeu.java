package tp;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TableDeJeu {
    static Jeu jeu = new Jeu();
    static Random random = new Random();
    public static void main(String[] Args) {


        Evenement evenement = new Evenement(jeu);
        String temp;
        Scanner read = new Scanner(System.in);

        int j1 = 0;
        int j2 = 0;
        int j3 = 0;
        boolean j1Valide= false;
        boolean j2Valide = false;
        boolean j3Valide = false;
        System.out.print("\t******** Bienvenue dans E-Mush! ********\n");
        for(int i = 0; i < Joueur.getRole().length ; i++){
            System.out.println(i + 1 + " - " + Joueur.getRole()[i]);
        }
        System.out.print("Voulez vous une description des competences de chaque joueur ?(oui/non) : ");
        String details = read.nextLine();
        switch (details.toLowerCase()){
            case "oui" :
                for(int i = 0; i < Joueur.competancesJoueurs.length ; i++){
                    System.out.println(i + 1 + " - " + Joueur.competancesJoueurs[i]);
                }
            case "non" :

            default:

        }


        while (true) {
            try {
                if (!j1Valide) {
                    System.out.println("C'est au tour du Joueur 1 de choisir");
                    System.out.print("Veuillez choisir votre perso: ");
                    temp = read.nextLine();
                    j1 = Integer.parseInt(temp ) - 1;
                    if(!jeu.getPlayers()[j2].aCompetence("Seul espoir")){
                        System.out.print("Voulez vous etre mush? (oui/non) : ");
                        String j1mush = read.nextLine();
                        if(j1mush.equalsIgnoreCase("oui")){
                            jeu.getPlayers()[j1].setMush(true);
                            jeu.setNbmush(jeu.getNbmush() + 1);
                        }

                    }
                    if (j1 < 0 || j1 > 15) {
                        System.out.println("Veuillez entrer un nombre entre 1 et 16!");
                    } else {
                        j1Valide = true;
                    }


                }

                if (j1Valide && !j2Valide) {
                    System.out.println("C'est au tour du Joueur 2 de choisir");
                    System.out.print("Veuillez choisir votre perso: ");
                    temp = read.nextLine();
                    j2 = Integer.parseInt(temp) - 1;
                    if(!jeu.getPlayers()[j2].aCompetence("Seul espoir")){
                        System.out.print("Voulez vous etre mush? (oui/non) : ");
                        String j2mush = read.nextLine();
                        if(j2mush.equalsIgnoreCase("oui")){
                            jeu.getPlayers()[j2].setMush(true);
                            jeu.setNbmush(jeu.getNbmush() + 1);
                        }

                    }
                    if (j2 == j1) {
                        System.out.println("Le joueur est déja pris!\n");
                    } else if (j2 < 0 || j2 > 15) {
                        System.out.println("Veuillez entrer un nombre entre 1 et 16!\n");
                    } else {
                        j2Valide = true;
                    }


                }

                if (j1Valide && j2Valide && !j3Valide) {
                    System.out.println("C'est au tour du Joueur 3 de choisir");
                    System.out.print("Veuillez choisir votre perso: ");
                    temp = read.nextLine();
                    j3 = Integer.parseInt(temp) - 1;
                    if(!jeu.getPlayers()[j2].aCompetence("Seul espoir") && (!jeu.getPlayers()[j1].isMush() || !jeu.getPlayers()[j2].isMush())){
                        System.out.print("Voulez vous etre mush? (oui/non) : ");
                        String j3mush = read.nextLine();
                        if(j3mush.equalsIgnoreCase("oui")){
                            jeu.getPlayers()[j3].setMush(true);
                            jeu.setNbmush(jeu.getNbmush() + 1);
                        }

                    }
                    if (j3 == j1 || j3 == j2) {
                        System.out.println("Le joueur est déja pris!\n");
                    } else if (j3 < 0 || j3 > 15) {
                        System.out.println("Veuillez entrer un nombre entre 1 et 16!\n");
                    } else {
                        j3Valide = true;
                        break;
                    }

                }

            } catch (Exception e) {
                System.out.println("Veuillez entrer une valeur valide !\n");
            }
        }
        //affectation des mushs
        for(int i = jeu.getNbmush(); i < 2 ; i++){
            while(true){
                int mush = random.nextInt(0,16);
                if(!jeu.getPlayers()[mush].isMush()){
                    jeu.getPlayers()[mush].setMush(true);
                    break;
                }
            }

        }
        //Mettre les utilisateurs dans des salles aléatoires
        System.out.println("Distribution des joueurs dans des salles aléatoires...");
        //jeu.getPlayers()[j1].setSalleActuelle(jeu.getVaisseau().getSalle(random.nextInt(1, 28)));
        //jeu.getPlayers()[j2].setSalleActuelle(jeu.getVaisseau().getSalle(random.nextInt(1, 28)));

        jeu.getPlayers()[j1].getInventaire().add(new Objets("Chat de Schrodinger"));
        jeu.getPlayers()[j1].getInventaire().add(new Objets("Chat de Schrodinger"));
        jeu.getPlayers()[j1].getInventaire().get(0).setContamine(true);
        //initialisation des actions des joueurs
        repartitionSalles(j1,j2,j3);
        for (int i = 0;i < 16 ; i++){
            jeu.getPlayers()[i].innitAction();
        }


        int choix1;
        int choix2;
        int choix3;
        System.out.print("Le jeu est pret.\nLancement dans : \n1 \n2 \n3 \nDEBUT : ");

        while(!jeu.isVictoire()  ){
            System.out.println( "Cycle : " + jeu.getCycle() + " Jour : " + jeu.getJour());
            evenement.evenement();

            while (!jeu.getPlayers()[j1].getPasserTour() && !jeu.isVictoire()){
                jeu.getPlayers()[j1].innitAction();
                System.out.println("C'est au tour du joueur 1 de jouer : ");
                System.out.println(jeu.getPlayers()[j1].etatJoueur());
                for(int action = 0; action < jeu.getPlayers()[j1].getActionPossible().size() ; action++){
                    System.out.println(action + 1 + " - " + jeu.getPlayers()[j1].getActionPossible().get(action));
                }


                while (true){


                    try{
                        System.out.print("Faites votre choix : ");

                        String tamp = read.next();

                        choix1 = Integer.parseInt(tamp);
                        if (choix1 < 1 || choix1 > jeu.getPlayers()[j1].getActionPossible().size() ){
                            System.out.println("entrez une valeure valide !!!");
                        }else{
                            break;
                        }

                    } catch (Exception e){
                        System.out.println("entrez une valeure valide !!!");
                    }
                }
                jeu.choixAction(jeu.getPlayers()[j1],jeu.getPlayers()[j1].getActionPossible().get(choix1 - 1));
            }


            while(!jeu.getPlayers()[j2].getPasserTour() && !jeu.isVictoire()) {
                jeu.getPlayers()[j2].innitAction();
                System.out.println("C'est au tour du joueur 2 de jouer : ");
                System.out.println(jeu.getPlayers()[j2].etatJoueur());
                for(int action = 0; action < jeu.getPlayers()[j2].getActionPossible().size() ; action++) {
                    System.out.println(action + 1 + " - " + jeu.getPlayers()[j2].getActionPossible().get(action));
                }


                while (true){


                    try{
                        System.out.print("Faites votre choix : ");
                        String tamp = read.next();
                        choix2 = Integer.parseInt(tamp);
                        if (choix2 < 1 || choix2 > jeu.getPlayers()[j2].getActionPossible().size() ){
                            System.out.println("entrez une valeur valide !!!");
                        }else{
                            break;
                        }

                    }catch (Exception e){
                        System.out.println("entrez une valeur valide !!!");
                    }
                }
                jeu.choixAction(jeu.getPlayers()[j2],jeu.getPlayers()[j2].getActionPossible().get(choix2 - 1));
            }

            while (!jeu.getPlayers()[j3].getPasserTour() && !jeu.isVictoire()) {
                jeu.getPlayers()[j3].innitAction();
                System.out.println("C'est au tour du joueur 3 de jouer : ");
                System.out.println(jeu.getPlayers()[j3].etatJoueur());
                for(int action = 0; action < jeu.getPlayers()[j3].getActionPossible().size() ; action++) {
                    System.out.println(action + 1 + " - " + jeu.getPlayers()[j3].getActionPossible().get(action));
                }


                while (true){

                    try{
                        System.out.print("Faites votre choix : ");
                        String tamp = read.next();
                        choix3 = Integer.parseInt(tamp);
                        if (choix3 < 1 || choix3 > jeu.getPlayers()[j3].getActionPossible().size() ){
                            System.out.println("entrez une valeure valide !!!");
                        }else{
                            break;
                        }

                    }catch (Exception e){
                        System.out.println("entrez une valeure valide !!!");
                    }
                }
                jeu.choixAction(jeu.getPlayers()[j3],jeu.getPlayers()[j3].getActionPossible().get(choix3 - 1));
            }
            //le robot joue pour les autres joueur
            System.out.println("C'est au tour des ordinateurs de jouer");


            jeu.setCycle(jeu.getCycle() + 1);
            if (jeu.getCycle() > 8){
                jeu.setCycle(1);
                jeu.incJour();
            }
        }
        playOrdi(j1,j2,j3);

    }
    public static void playOrdi(int j1, int j2, int j3){

        for (int i = 0; i < jeu.getPlayers().length; i++) {
            if (i == j1 || i == j2 || i == j3) {
                continue;
            }
            jeu.getPlayers()[i].setOrdi(true);
            int choixOrdi = random.nextInt(3,jeu.getPlayers()[i].getActionPossible().size());
            jeu.choixAction(jeu.getPlayers()[i],jeu.getPlayers()[i].getActionPossible().get(choixOrdi));
        }
    }
    static void repartitionSalles(int j1, int j2, int j3){
        jeu.getPlayers()[j1].setSalleActuelle(jeu.getVaisseau().getSalle(6));
        jeu.getPlayers()[j2].setSalleActuelle(jeu.getVaisseau().getSalle(1));
        jeu.getPlayers()[j3].setSalleActuelle(jeu.getVaisseau().getSalle(1));
        // jeu.getPlayers()[j3].setSalleActuelle(jeu.getVaisseau().getSalle(random.nextInt(1, 28)));


        //Mettre tout les joueurs restant dans le labo
        System.out.println("Entrain de mettre les joueurs joué par l'ordinateur dans le labo...");
        for (int i = 0; i < jeu.getPlayers().length; i++) {
            if (i == j1 || i == j2 || i == j3) {
                continue;
            }
            jeu.getPlayers()[i].setSalleActuelle(jeu.getVaisseau().getSalle(6));
        }
    }



}
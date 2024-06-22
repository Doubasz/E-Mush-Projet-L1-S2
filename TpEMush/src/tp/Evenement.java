package tp;

import java.util.Random;

public class Evenement {
    private  Vaisseau vaisseau ;
    private  Jeu jeu ;
    Random random = new Random();
    public Evenement(Jeu jeu){
        this.jeu = jeu;
        this.vaisseau = jeu.getVaisseau();
    }
    public void evenement(){
        for(Joueur joueur : jeu.getPlayers()){
            if(joueur.getIsAlive()){
                vaisseau.setOxygene(vaisseau.getOxygene() - 1);
                joueur.setPasserTour(false);
            }
            if(joueur.isCouche())
                joueur.setPa(joueur.getPa() + 1);

        }
        int inc = random.nextInt(1,21);
        if (inc == 1){
            eveninc();
        }
        int plafond = random.nextInt(1,51);
        if (plafond == 1){
            int numsalle = random.nextInt(0,28);
            System.out.println("Le plafond s'est effondre dans la salle " + vaisseau.getSalle(numsalle).getNom());
            for (Joueur joueur : jeu.getPlayers()){
                if(joueur.getSalleActuelle().getId() == numsalle)
                    joueur.setPv(joueur.getPv()-6);
                //le joueur prend le plafond dans sa tranche

            }
        }
        if(jeu.getJour() != 1 || jeu.getCycle() > 5)
            attaqueAlien();
        int sabotage = random.nextInt(1,101);
        if((sabotage == 1 )|| (sabotage == 2 )||( sabotage == 3)){
            jeu.getEquipementsJeu().get(random.nextInt(0,jeu.getEquipementsJeu().size() + 1)).setIsFonctionnel(false);
        }
    }
    public void eveninc(){
        int ID = random.nextInt(0,28);
        vaisseau.getSalle(ID).setIncendie(true);
    }
    public void attaqueAlien(){
        int alianspawn = random.nextInt(0,6);
        for(int i = 1; i <= alianspawn ; i++){
            vaisseau.getListeVaisseauAlien().add(jeu.getCycle());
        }
        for (int alien : vaisseau.getListeVaisseauAlien()){
            if (jeu.getCycle() - alien >= 2){
                vaisseau.setArmor(vaisseau.getArmor() - 1);
            }
        }
    }


}

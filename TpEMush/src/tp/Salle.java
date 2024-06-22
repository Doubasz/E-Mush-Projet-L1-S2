package tp;

import java.util.LinkedList;

public class Salle {
    private String  nom;
    private Vaisseau vaisseau;
    private int id;
    private int nbJoueur;
    private LinkedList<Salle> sallesVoisines = new LinkedList<>();
    private boolean camera = false;
    private boolean incendie = false;
    private LinkedList<String> enso = new LinkedList<>();

    private LinkedList<String> actionsRecents = new LinkedList<>();
    private LinkedList<Objets> stockage = new LinkedList<>();
    private LinkedList<Equipement> equipements = new LinkedList<>();
    public Salle(String Nom, Vaisseau Vaisseau, int Id) {
        this.nom = Nom;
        this.vaisseau = Vaisseau;
        this.id = Id;
        this.nbJoueur = 0;
    }
    public void ajoutSalle(Salle s){
        this.sallesVoisines.add(s);
    }

    public String getNom() {
        return this.nom;
    }

    public int getId() {
        return this.id;
    }

    public Vaisseau getVaisseau() {return this.vaisseau;}

    public int getNbJoueur() {return this.nbJoueur;}

    public void addNbJoueur(int n)
    {
        this.nbJoueur += n;
    }

    public LinkedList<String> getEnso() {
        return enso;
    }

    public boolean isCamera() {
        return camera;
    }

    public void setCamera(boolean camera) {
        this.camera = camera;
    }

    public boolean isIncendie() {
        return incendie;
    }

    public void setIncendie(boolean incendie) {
        this.incendie = incendie;
    }

    public LinkedList<Salle> getSallesVoisines() {
        return this.sallesVoisines;
    }
    public boolean estVoisineDe(Salle s){
        for (Salle x : this.sallesVoisines){
            if ( x.getNom().equals(s.getNom()) ){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        String temp = this.getNom() + ", ID : " + this.getId() + ", salles voisines [";
        for (Salle s : this.sallesVoisines)
            temp += s.getNom() + ", ";
        return temp + "]";
    }
    public void ajouterActionsRecents(String action,Joueur joueur){
        //rajoute laction faite dans la liste
        this.actionsRecents.add(joueur.getNom() + " : " + action);

    }
    public void afficherActionsRecents(){
        System.out.print("Les actions récentes dans cette salle sont : {");
        int numaction = 0;
        while(numaction < 10 && !this.actionsRecents.get(numaction).isBlank()){
            System.out.println(this.actionsRecents.get(numaction) + ", ");
            numaction ++;
        }
        System.out.println("}");
    }
    public LinkedList<Objets> getStockage() {return this.stockage;}

    public void setStockage(Objets o) {this.stockage.add(o);}

    public LinkedList<Equipement> getEquipements() {
        return equipements;
    }
    public void ajoutEquipement(Equipement e) {this.equipements.add(e);}

    public boolean fonctionne(String equipement){
        for(Equipement equipement1 : this.equipements){
            if(equipement1.getNom().equals(equipement))
                return equipement1.getIsFonctionnel();
        }
        return false;
    }



    public int returnIndice(String nom)
    {
        int indice = -1;
        for(int i = 0; i < this.equipements.size(); i++)
        {
            if(this.equipements.get(i).getNom().equals(nom))
            {
                indice = i;
            }
        }
        return indice;
    }


    boolean have(String obj){
        for (Objets objets : this.stockage) {
            if (objets != null){
                if (objets.getNom().equals(obj))
                    return true;
            }
        }
        return false;
    }

    public boolean tousNonFonctionel()
    {
        for(Equipement equip : this.equipements)
        {
            if(equip.getIsFonctionnel()) return false;
        }
        return true;
    }

    public int nbEquipementNonFonctionel()
    {
        int num = 0;
        for(Equipement equip : this.equipements)
        {
            if(!equip.getIsFonctionnel()) num++;
        }
        return num;
    }
}

package tp;

import java.util.LinkedList;
import java.util.Random;

public class Objets
{
    private String nom;
    private int quantiteMax;
    private boolean isCache;
    private boolean isCuisine;
    private boolean isContamine;
    private int used;
    private final  static String[] listeObjets = {"Armure", "Clé à molette", "Combinaisons", "Paire de gants de protection", "Savon", "Tablier intachable", "Trottinette", "Extincteurs", "Couteau", "Blasters", "Grenade", "Médikit", "Caméras", "Souche de test mush", "Débris métalliques", "Chat de Schrodinger", "Rations standards", "Extracteur de spores", "Sérum rétro-fongique"};

    public Objets(String s)
    {
        this.nom = s;
        this.isCache = false;
        this.isCuisine = false;
        this.isContamine = false;
        this.used = 0;

        if(s.equals(listeObjets[2])) this.quantiteMax = 4;
        else if(s.equals(listeObjets[7])) this.quantiteMax = 3;
        else if(s.equals(listeObjets[9]) || s.equals(listeObjets[12])) this.quantiteMax = 2;
        else if(s.equals(listeObjets[14]) || s.equals(listeObjets[16])) this.quantiteMax = 30;
        else this.quantiteMax = 1;
    }

    public String getNom() {return this.nom;}
    public static String[] getListeObjets() {return listeObjets;}

    public int getQuantiteMax() {return this.quantiteMax;}

    public int getUsed() {
        return this.used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void setContamine(boolean contamine) {
        isContamine = contamine;
    }

    public boolean getIsContamine() {return isContamine;}

    public static LinkedList<Objets> innitObjet(Vaisseau vaisseau)
    {
        LinkedList<Objets> list = new LinkedList<>();
        for(int i = 0; i < Objets.getListeObjets().length; i++)
        {
            Objets obj = new Objets(Objets.getListeObjets()[i]);
            for(int j = 0; j < obj.getQuantiteMax(); j++)
            {
                list.add(obj);
            }
        }

        //Il existe une autre methode qui est de cree une nouvelle liste et stocke les indices du random
        //Distribution aléatoire des objets dans le stockage des salles
        int tailleObjets = list.size();
        int k = 0;

        while(list.size() > 54)
        {
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i).getNom().equals("Rations standards"))
                {
                    vaisseau.getSalle(15).setStockage(list.get(i));
                    list.remove(list.get(i));
                }
            }
        }

        for(int i = 0; i < vaisseau.getPlan().size(); i++)
        {
            for(int j = 0; j < (tailleObjets - 30) / vaisseau.getPlan().size(); j++)
            {
                Random random = new Random();
                int temp = random.nextInt(0, 54 - k);
                vaisseau.getSalle(i).getStockage().add(list.get(temp));
                list.remove(temp);
                k++;
            }
        }
        return list;
    }

    public boolean getIsCache() {return isCache;}

    public void setIsCache(boolean cache) {isCache = cache;}

    public boolean isContamine() {return this.isContamine;}

    public boolean isIscuisine() {return this.isCuisine;}
}
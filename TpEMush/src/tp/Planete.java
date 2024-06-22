package tp;

import java.util.Random;

public class Planete
{
    private int distance;
    private int[] ressources = new int[3];
    private boolean isScan;
    private boolean[] scanRessources = new boolean[3];

    public Planete()
    {
        Random random = new Random();

        this.isScan = false;
        this.distance = random.nextInt(2, 11);
        for(int i = 0; i < 3; i++)
        {
            this.ressources[i] = random.nextInt(5, 31);
            this.scanRessources[i] = false;
        }
    }

    public boolean getIsScan() {return this.isScan;}
    public boolean[] getScanRessources() {return this.scanRessources;}
    public void setScanRessources(boolean b, int indice)
    {
        if(indice >= 0 && indice <= 2) this.scanRessources[indice] = b;
        else System.out.println("Check l'indice dans ScannerPlanete");
    }
    public int[] getRessources() {return this.ressources;}
    public int getDistance() {return this.distance;}
    public void setDistance(int distance) {this.distance = distance;}
    public void setIsScan(boolean scan) {this.isScan = scan;}
}

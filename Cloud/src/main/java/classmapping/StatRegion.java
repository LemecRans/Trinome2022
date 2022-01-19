package classmapping;

import java.util.ArrayList;
import java.util.List;

public class StatRegion {
	String regionName;
    String probleme;
    double stat;

    public StatRegion() {
    }

    public StatRegion(String regionName, String probleme, double stat) {
        this.regionName = regionName;
        this.probleme = probleme;
        this.stat = stat;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public double getStat() {
        return stat;
    }

    public void setStat(double stat) {
        this.stat = stat;
    }
    
    public List<StatRegion> listeStat(double coordonneX, double coordonneY){
        List<StatRegion> allStat = new ArrayList();
        
        return allStat;
    }
}

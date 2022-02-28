package classmapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Region {
	int idRegion;
    String designationRegion;
    double coordonneX;
    double coordonneY;
    double coordonneX1;
    double coordonneY1;
    String probleme;
    
    public Region() {
    }

    public Region(int idRegion, String designationRegion, double coordonneX, double coordonneY, double coordonneX1, double coordonneY1) {
        this.idRegion = idRegion;
        this.designationRegion = designationRegion;
        this.coordonneX = coordonneX;
        this.coordonneY = coordonneY;
        this.coordonneX1 = coordonneX1;
        this.coordonneY1 = coordonneY1;
    }

    public Region(String designationRegion, String probleme) {
        this.designationRegion = designationRegion;
        this.probleme = probleme;
    }

    
    
    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getDesignationRegion() {
        return designationRegion;
    }

    public void setDesignationRegion(String designationRegion) {
        this.designationRegion = designationRegion;
    }

    public double getCoordonneX() {
        return coordonneX;
    }

    public void setCoordonneX(double coordonneX) {
        this.coordonneX = coordonneX;
    }

    public double getCoordonneY() {
        return coordonneY;
    }

    public void setCoordonneY(double coordonneY) {
        this.coordonneY = coordonneY;
    }

    public double getCoordonneX1() {
        return coordonneX1;
    }

    public void setCoordonneX1(double coordonneX1) {
        this.coordonneX1 = coordonneX1;
    }

    public double getCoordonneY1() {
        return coordonneY1;
    }

    public void setCoordonneY1(double coordonneY1) {
        this.coordonneY1 = coordonneY1;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }
    
    
    
    public List<Region> allRegion(){
        List<Region> liste = new ArrayList();
        String request = "select * from Region";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            // System.out.println("Requete all region : "+request);
            while (res.next()){
                int idRegion  = res.getInt(1);                
                String designationRegion  = res.getString(2);
                double coordonneX  = res.getDouble(3);
                double coordonneY  = res.getDouble(4);
                double coordonneX1  = res.getDouble(5);
                double coordonneY1  = res.getDouble(6);
                liste.add(new Region(idRegion, designationRegion, coordonneX, coordonneY, coordonneX1, coordonneY1));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    
    public String getRegionByCoordonne(double coordonneeX, double coordonneeY){
        String anaranaRegion = null;
        Region region = new Region();
        List<Region> lesRegions = region.allRegion();
        for(int i=0; i<lesRegions.size(); i++){
            if(coordonneeX >= lesRegions.get(i).getCoordonneX() && coordonneeX <= lesRegions.get(i).getCoordonneX1() && coordonneeY >= lesRegions.get(i).getCoordonneY() && coordonneeY <= lesRegions.get(i).getCoordonneY1()){
                anaranaRegion = lesRegions.get(i).getDesignationRegion();
                break;
            } 
            else{
                anaranaRegion = "Coordonnee invalide";
            }
        }
        return anaranaRegion;
    }
    
    public List<String> rechercheRegion(String mot){
        List<String> liste = new ArrayList();
        String request = "SELECT designationRegion FROM Region WHERE designationRegion like '%%"+mot.trim()+"%'";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete recherche region : "+request);
            while (res.next()){                        
                String designationProbleme  = res.getString(1);               
                liste.add(designationProbleme);               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    
    public List<Region> rechercheProblemeParRegion(String mot){
        List<Region> liste = new ArrayList();
        String request = "SELECT designationProbleme, designationRegion FROM signalement JOIN Probleme ON signalement.idProbleme = Probleme.idProbleme JOIN utilisateur ON utilisateur.idUtilisateur = Signalement.idUtilisateur JOIN region ON region.idRegion = utilisateur.idRegion WHERE designationProbleme like '%%"+mot.trim()+"%' GROUP BY designationRegion,designationProbleme";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete recherche region : "+request);
            while (res.next()){                        
                String designationProbleme  = res.getString(1);
                String designationRegion  = res.getString(2);
                liste.add(new Region(designationRegion,designationProbleme));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public static void delete(String id) {
    	String request = "DELETE FROM region WHERE idregion = "+id;
        Statement stmt;
        Connection connex;
        try {
            System.out.println("Delete signalement "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            connex.close();
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
    }

    public void insertRegion(String designationRegion, double coordonneX, double coordonneY, double coordonneX1, double coordonneY1){
        String request = "INSERT INTO Region(designationRegion, coordonneX, coordonneY, coordonneX1, coordonneY1) VALUES ('"+designationRegion.trim()+"',"+coordonneX+","+coordonneY+","+coordonneX1+","+coordonneY1+")";
        Statement stmt;
        Connection connex;
        try {
            System.out.println("Insert region : "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            connex.close();
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
    }

    public int getIdRegionByName(String designationRegion){
        String request = "select idRegion from Region WHERE designationRegion = '" + designationRegion.trim()+"'";
        Statement stmt;
        Connection connex;
        int idRegion = 0;
        try {
            // System.out.println("Requete get Id Region By Name : "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next()){
                idRegion  = res.getInt(1);                
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return idRegion;
    }

    public static Region RegionbyId(String s){
        List<Region> liste = new ArrayList();
        String request = "select * from Region where idRegion="+s;
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            // System.out.println("Requete all region : "+request);
            while (res.next()){
                int idRegion  = res.getInt(1);                
                String designationRegion  = res.getString(2);
                double coordonneX  = res.getDouble(3);
                double coordonneY  = res.getDouble(4);
                double coordonneX1  = res.getDouble(5);
                double coordonneY1  = res.getDouble(6);
                liste.add(new Region(idRegion, designationRegion, coordonneX, coordonneY, coordonneX1, coordonneY1));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Region valiny=liste.get(0);
        return valiny;
    }
    public Region RegionbyName(String name){
        Region liste = null;
        String request = "select * from Region where designationRegion='"+name+"'";
        Statement stmt;
        Connection connex;
        int i = 0;
        Region valiny=new Region();
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            // System.out.println("Requete all region : "+request);
            while (res.next()){
                int idRegion  = res.getInt(1);                
                String designationRegion  = res.getString(2);
                double coordonneX  = res.getDouble(3);
                double coordonneY  = res.getDouble(4);
                double coordonneX1  = res.getDouble(5);
                double coordonneY1  = res.getDouble(6);
                liste=new Region(idRegion, designationRegion, coordonneX, coordonneY, coordonneX1, coordonneY1);               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public static List<Region> proche(double x,double y){
        List<Region> liste = new ArrayList();
        String request = "SELECT * FROM region";
        Statement stmt;
        //System.out.println(x+"-"+y);
        Connection connex;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next()){
            	/*int i=Region.estproche(x,res.getDouble("coordonneX"),y,res.getDouble("coordonneY"));
            	if(i==1) {*/
            		String designationProbleme  = res.getString(1);
                    String designationRegion  = res.getString(2);
                    liste.add(new Region(designationRegion,designationProbleme));
            	//}
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    //atoooooooooooooooooooooooo
    public static double calculdistance(double y2,double x2,double e,double d){
		double valiny=0.0;
		double reps;
		Double vali=new Double(valiny);
		double x=0;
		double y=0;
		x=Math.abs(x2-y2);
		y=Math.abs(e-d);
		reps=Math.pow(x,2)+Math.pow(y,2);
		valiny=Math.sqrt(reps);
		//System.out.println("distance:"+valiny);
		return valiny;
	}
	public static int estproche(double x,double d,double y,double e){
		int valiny=0;
		double distance=calculdistance(y,x,e,d);
		if(distance<=30 ){
			valiny=1;
		}
		return valiny;
	}
    public static void main(String[] args){
        Region region = new Region();
        int getId = region.getIdRegionByName("Melaky");
        //System.out.println(getId);

    }
}

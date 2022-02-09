package classmapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Probleme {
	int idProbleme;
    String designationProbleme;
    double pourcentage;
    String region;
    String statut;

    public Probleme() {
    }

    public Probleme(int idProbleme, String designationProbleme) {
        this.idProbleme = idProbleme;
        this.designationProbleme = designationProbleme;
    }

    public Probleme(int idProbleme, String designationProbleme, String region,String stat) {
        this.idProbleme = idProbleme;
        this.designationProbleme = designationProbleme;
        this.region = region;
        this.statut=stat;
    }
    public Probleme(int idProbleme, String designationProbleme, String region) {
        this.idProbleme = idProbleme;
        this.designationProbleme = designationProbleme;
        this.region = region;
    }
    
    

    public Probleme(String designationProbleme, double pourcentage) {
        this.designationProbleme = designationProbleme;
        this.pourcentage = pourcentage;
    }

    public Probleme(String designationProbleme, double pourcentage, String region) {
        this.designationProbleme = designationProbleme;
        this.pourcentage = pourcentage;
        this.region = region;
    }
    
    
    

    public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(int idProbleme) {
        this.idProbleme = idProbleme;
    }

    public String getDesignationProbleme() {
        return designationProbleme;
    }

    public void setDesignationProbleme(String designationProbleme) {
        this.designationProbleme = designationProbleme;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
    
    public int countProbleme(){
        String request = "select count(*) from signalement";
        java.sql.Statement stmt;
        Connection connex;
        int ans = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete count probleme : "+request);
            while (res.next()){
                ans  = res.getInt(1);                
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ans;
    }
    
    public List<Probleme> CountProblemeExist(){
        List<Probleme> listeCount = new ArrayList();
        Region region = new Region();
        String request = "select designationProbleme,count(*),CoordonneX,CoordonneY from probleme join signalement on probleme.idProbleme = signalement.idProbleme group by probleme.idProbleme";
        java.sql.Statement stmt;
        Connection connex;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete probleme exist: "+request);
            while (res.next()){
                String designation  = res.getString(1);
                int valeure = res.getInt(2);
                double coordonneeX = res.getDouble(3);
                double coordonneeY = res.getDouble(4);
                String getPointRegion = region.getRegionByCoordonne(coordonneeX, coordonneeY);
                listeCount.add(new Probleme(designation, valeure,getPointRegion));
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listeCount;
    }
    
    public List<Probleme> CountProblemeNotExist(){
        List<Probleme> liste = new ArrayList();
        String request = "select * from probleme where idProbleme not in (select idProbleme from signalement)";
        java.sql.Statement stmt;
        Connection connex;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete probleme not exist : "+request);
            while (res.next()){
                int idProbleme  = res.getInt(1);                
                String designationProbleme  = res.getString(2);               
                liste.add(new Probleme(idProbleme, designationProbleme));               
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    
    public List<Probleme> calculPourcentage(){
        List<Probleme> listeFinal = new ArrayList();
        Probleme probleme = new Probleme();
        int global = probleme.countProbleme();
        List<Probleme> problemeExist = probleme.CountProblemeExist();
        List<Probleme> problemeNotExist = probleme.CountProblemeNotExist();
        
        for(int i=0; i<problemeExist.size(); i++){
            double[] listeValeure = new double[problemeExist.size()];
            listeValeure[i] = (problemeExist.get(i).getPourcentage() * 100)/global;
            listeFinal.add(new Probleme(problemeExist.get(i).getDesignationProbleme(), listeValeure[i],problemeExist.get(i).getRegion()));
        }
        
        for(int i=0; i<problemeNotExist.size(); i++){
            listeFinal.add(new Probleme(problemeNotExist.get(i).getDesignationProbleme(), 0, "None"));
        }
        return listeFinal;
    }
    
    // public static void main(String[] args) {
    //     Probleme probleme = new Probleme();
    //     List<Probleme> liste = probleme.calculPourcentage();
    //     for (int i = 0; i < liste.size(); i++) {
    //         System.out.println(liste);   
    //     }
    // }
    
    public List<Probleme> rechercheProbleme(String mot){
    	Region reg= new Region();
    	
        List<Probleme> liste = new ArrayList();
        String request = "SELECT signalement.idProbleme,designationProbleme,coordonneX, coordonneY ,etatStatut FROM signalement JOIN Probleme ON signalement.idProbleme = Probleme.idProbleme join statut on signalement.idStatut=statut.idStatut WHERE designationProbleme like '%%"+mot.trim()+"%'";
        java.sql.Statement stmt;
        Connection connex;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete recherche probleme : "+request);
            while (res.next()){
            	int id = res.getInt(1);  
                String designationProbleme  = res.getString(2);
                double coordonneX  = res.getDouble(3);
                double coordonneY  = res.getDouble(4);
                String statut=res.getString(5);
                String anaranaRegion = reg.getRegionByCoordonne(coordonneX, coordonneY);
                System.out.println("stat"+ statut);
                liste.add(new Probleme(id,designationProbleme,anaranaRegion,statut));               
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public List<Probleme> recherchePro(String prob,String region,String statut){
    	Region reg= new Region();
    	
        List<Probleme> liste = new ArrayList();
        String request = "SELECT signalement.idProbleme, designationProbleme, coordonneX, coordonneY, etatStatut\r\n"
        		+ "FROM signalement\r\n"
        		+ "JOIN Probleme ON signalement.idProbleme = Probleme.idProbleme\r\n"
        		+ "JOIN statut ON signalement.idStatut = statut.idStatut\r\n"
        		+ "WHERE designationProbleme LIKE '%%"+prob.trim()+"%'\r\n"
        		+ "AND etatStatut LIKE '%%"+statut.trim()+"%'";
        java.sql.Statement stmt;
        Connection connex;
        int i = 0;
        
        System.out.println("blem: "+prob);
        System.out.println("region: "+region);
        System.out.println("statut : "+ statut);
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            
            while (res.next()){
            	int id = res.getInt(1);  
                String designationProbleme  = res.getString(2);
                double coordonneX  = res.getDouble(3);
                double coordonneY  = res.getDouble(4);
                String sta=res.getString(5);
                String anaranaRegion = reg.getRegionByCoordonne(coordonneX, coordonneY);
                if(region.equalsIgnoreCase("")==false) {
                	if(anaranaRegion.equalsIgnoreCase(region.trim())==true) {                        
                		liste.add(new Probleme(id,designationProbleme,anaranaRegion,sta));               
                        System.out.println("Probleme : "+liste.get(i).getDesignationProbleme());
                        i++;
                    }
                }
                else {
                    System.out.println("Requete recherche probleme aaaa: "+request);
                	liste.add(new Probleme(id,designationProbleme,anaranaRegion,sta));               
                    i++;
                }
                
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public List<Probleme> allProbleme(){
    	Region reg= new Region();
    	
        List<Probleme> liste = new ArrayList();
        String request = "SELECT signalement.idProbleme, designationProbleme, coordonneX, coordonneY, etatStatut\r\n"
        		+ "FROM signalement\r\n"
        		+ "JOIN Probleme ON signalement.idProbleme = Probleme.idProbleme\r\n"
        		+ "JOIN statut ON signalement.idStatut = statut.idStatut\r\n";
        java.sql.Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete recherche probleme : "+request);
            while (res.next()){
            	int id = res.getInt(1);  
                String designationProbleme  = res.getString(2);
                double coordonneX  = res.getDouble(3);
                double coordonneY  = res.getDouble(4);
                String sta=res.getString(5);
                liste.add(new Probleme(id,designationProbleme,sta));               
                i++;
                
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public List<Probleme> allBleme(){
        List<Probleme> liste = new ArrayList();
        String request = "select * from Probleme";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all bleme : "+request);
            while (res.next()){
                int id = res.getInt(1);  
                String designationProbleme  = res.getString(2);
                liste.add(new Probleme(id,designationProbleme));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public static void main(String[] args){
        Probleme probleme = new Probleme();
        List<Probleme> recherchePro = probleme.recherchePro("Tapaka rano", "Vatovavy-Fitovinany", "Neutre");
        System.out.println(recherchePro.get(0).getDesignationProbleme());
    }
}

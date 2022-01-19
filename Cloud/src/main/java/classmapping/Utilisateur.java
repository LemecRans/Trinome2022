package classmapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	int idUtilisateur;
    String loginUtilisateur;
    String mdpUtlisateur;
    int idRegion;
    String region;

    public Utilisateur() {
    }

    public Utilisateur(int idUtilisateur, String loginUtilisateur, String mdpUtlisateur, int idRegion) {
        this.idUtilisateur = idUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mdpUtlisateur = mdpUtlisateur;
        this.idRegion = idRegion;
    }

    public Utilisateur(int idUtilisateur, String loginUtilisateur, String mdpUtlisateur, int idRegion, String region) {
        this.idUtilisateur = idUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mdpUtlisateur = mdpUtlisateur;
        this.idRegion = idRegion;
        this.region = region;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getMdpUtlisateur() {
        return mdpUtlisateur;
    }

    public void setMdpUtlisateur(String mdpUtlisateur) {
        this.mdpUtlisateur = mdpUtlisateur;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public List<Utilisateur> allUtilisateur(){
        List<Utilisateur> liste = new ArrayList();
        String request = "select Utilisateur.*, region.designationRegion from Utilisateur JOIN Region ON Utilisateur.idRegion = Region.idRegion";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Utilisateur : "+request);
            while (res.next()){
            	int idUtilisateur = res.getInt(1);
            	String loginUtilisateur = res.getString(2);
                String mdpUtlisateur = res.getString(3);
                int idRegion = res.getInt(4);        
                String region = res.getString(5);
                liste.add(new Utilisateur(idUtilisateur, loginUtilisateur, mdpUtlisateur, idRegion,region));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public static Utilisateur connexion(String login,String mdp){
    	Utilisateur a=new Utilisateur(0,null, null,0);
        String request = "select idUtilisateur,idregion from Utilisateur where loginutilisateur=\""+login+"\" and mdputilisateur=\""+mdp+"\"";
        Statement stmt;
        Connection connex;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Utilisateur : "+request);
            while (res.next()){
            	int idUtilisateur = res.getInt(1);
                int idRegion = res.getInt(2);
                a=new Utilisateur(idUtilisateur,login, mdp,idRegion);
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return a;
    }
}

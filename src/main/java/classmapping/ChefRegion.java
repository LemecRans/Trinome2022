package classmapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChefRegion {
	int idChefRegion;
	String nom;
	String prenom;
	int idRegion;
	String loginChefRegion;
	String mdpChefRegion;
	String designationRegion;
	
	public ChefRegion() {
		super();
	}

	public ChefRegion(int idChefRegion, String nom, String prenom, int idRegion, String loginChefRegion,String mdpChefRegion) {
		super();
		this.idChefRegion = idChefRegion;
		this.nom = nom;
		this.prenom = prenom;
		this.idRegion = idRegion;
		this.loginChefRegion = loginChefRegion;
		this.mdpChefRegion = mdpChefRegion;
	}
	
	

	public ChefRegion(String nom, String prenom, String designationRegion) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.designationRegion = designationRegion;
	}

	public int getIdChefRegion() {
		return idChefRegion;
	}

	public void setIdChefRegion(int idChefRegion) {
		this.idChefRegion = idChefRegion;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getLoginChefRegion() {
		return loginChefRegion;
	}

	public void setLoginChefRegion(String loginChefRegion) {
		this.loginChefRegion = loginChefRegion;
	}

	public String getMdpChefRegion() {
		return mdpChefRegion;
	}

	public void setMdpChefRegion(String mdpChefRegion) {
		this.mdpChefRegion = mdpChefRegion;
	}
	
	public String getDesignationRegion() {
		return designationRegion;
	}

	public void setDesignationRegion(String designationRegion) {
		this.designationRegion = designationRegion;
	}

	public List<ChefRegion> listeChef() {
        List<ChefRegion> liste = new ArrayList<ChefRegion>();
        Statement stmt;
        Connection connex;
        try {
            String request ="SELECT * FROM chefregion";
            connex = Connexion.con();
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next())
            {
                int idChefRegion = res.getInt(1);
                String nom = res.getString(2);
                String prenom = res.getString(3);
                int idRegion = res.getInt(4);
                String loginChefRegion = res.getString(5);
                String mdpChefRegion = res.getString(6);
                liste.add(new ChefRegion(idChefRegion, nom, prenom, idRegion, loginChefRegion, mdpChefRegion));       
            }
            Connexion.con().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return liste;
	}
	
	 public List<ChefRegion> infoChefRegion(){
         List<ChefRegion> liste = new ArrayList();
         String request = "select ChefRegion.nom, chefRegion.prenom, region.designationRegion from ChefRegion JOIN Region ON ChefRegion.idRegion = Region.idRegion";
         Statement stmt;
         Connection connex;
         int i = 0;
         try {
             connex = Connexion.con(); 
             stmt = connex.createStatement();
             ResultSet res = stmt.executeQuery(request);
             System.out.println("Requete all Utilisateur : "+request);
             while (res.next()){
         		 String nom = res.getString(1);
	             String prenom = res.getString(2);     
	             String designationRegion = res.getString(3);
	             liste.add(new ChefRegion(nom, prenom, designationRegion));               
	             i++;
             }
             connex.close();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
         return liste;
     }
	 public static ChefRegion chefbylogin(String login) {
		 ChefRegion valiny=null;
		 String request = "select * from ChefRegion WHERE loginChefRegion = '"+login+"'";
         Statement stmt;
         Connection connex;
         try {
			connex = Connexion.con(); 
			stmt = connex.createStatement();
			ResultSet res = stmt.executeQuery(request);
			System.out.println("Requete Chef : "+request);
			while (res.next()){
				int id = res.getInt("idChefRegion");
				String nom = res.getString(2);    
				String prenom = res.getString(3);
				valiny=new ChefRegion(id,nom, prenom, 0,login,null);   
			}
			connex.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 return valiny;
	 }
	 
	 public static ChefRegion infoChefRegionById(int id){
         ChefRegion liste = new ChefRegion();
         String request = "select ChefRegion.nom, chefRegion.prenom, region.designationRegion from ChefRegion JOIN Region ON ChefRegion.idRegion = Region.idRegion WHERE idChefRegion = "+id;
         Statement stmt;
         Connection connex;
         int i = 0;
         try {
			connex = Connexion.con(); 
			stmt = connex.createStatement();
			ResultSet res = stmt.executeQuery(request);
			System.out.println("Requete all Utilisateur : "+request);
			while (res.next()){
				String nom = res.getString(1);
				String prenom = res.getString(2);     
				String designationRegion = res.getString(3);
				liste = new ChefRegion(nom, prenom, designationRegion);               
				i++;
			}
			connex.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return liste;
     }
	 
 	public static Region validConnex(String login, String mdp){         
 		Region retour = null;
         ChefRegion chefRegion = new ChefRegion();
         List<ChefRegion> liste = chefRegion.listeChef();
         for(int i=0; i<liste.size(); i++) {
             if(liste.get(i).getLoginChefRegion().equals(login.trim()) == true && liste.get(i).getMdpChefRegion().equals(mdp.trim()) == true){
                 retour = Region.RegionbyId(""+liste.get(i).getIdRegion());
                 break;
             }
         }
         return retour;
     }
	 
	
	public static void main(String[] args) {
		ChefRegion regionChef = new ChefRegion();
		List<ChefRegion> liste = regionChef.infoChefRegion();
		ChefRegion chefById = regionChef.infoChefRegionById(8);
		//int connect = regionChef.validConnex("region5", "region5");
		//System.out.println(connect);
//		System.out.println(chefById.getNom()+" - "+chefById.getPrenom()+" - "+chefById.getDesignationRegion());
//		for(int i=0; i<liste.size(); i++) {
//			System.out.println(liste.get(i).getNom()+" - "+liste.get(i).getPrenom()+" - "+liste.get(i).getDesignationRegion());
//		}
	}
	
}

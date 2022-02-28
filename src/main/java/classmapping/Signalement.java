package classmapping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Signalement {
	int idSignalement;
    int idUtilisateur;
    double coordonneX;
    double coordonneY;
    String descriptionProbleme;
    Date datySignalement;
    String photo;
    int idProbleme;
    int idStatut;
    String designationStatut;
    String designationProbleme;
    String utilisateur;
    String designationRegion;
    String etatStatut;
    int compteur;
    double pourcentage;
    int regionId;


    public Signalement() {
    }

    public Signalement(int idSignalement, int idUtilisateur,String designationRegion , String descriptionProbleme, Date datySignalement, String photo, String designationProbleme, String designationStatut, int idProbleme, int regionId, double coordonneX, double coordonneY) {
        this.idSignalement = idSignalement;
        this.idUtilisateur = idUtilisateur;
        this.designationRegion=designationRegion;
        this.descriptionProbleme = descriptionProbleme;
        this.datySignalement = datySignalement;
        this.photo = photo;
        this.designationProbleme = designationProbleme;
        this.designationStatut= designationStatut;
        this.idProbleme = idProbleme;
        this.regionId = regionId;
        this.coordonneX = coordonneX;
        this.coordonneY = coordonneY;
    }
    public Signalement(int idSignalement, int idUtilisateur, double coordonneX, double coordonneY, String descriptionProbleme, Date datySignalement, String photo, int idProbleme, int idStatut) {
        this.idSignalement = idSignalement;
        this.idUtilisateur = idUtilisateur;
        this.coordonneX = coordonneX;
        this.coordonneY = coordonneY;
        this.descriptionProbleme = descriptionProbleme;
        this.datySignalement = datySignalement;
        this.photo = photo;
        this.idProbleme = idProbleme;
        this.idStatut = idStatut;
    }

    public Signalement(int idSignalement, int idUtilisateur, double coordonneX, double coordonneY, String descriptionProbleme, Date datySignalement, String photo, int idProbleme, int idStatut, String designationStatut, String designationProbleme) {
        this.idSignalement = idSignalement;
        this.idUtilisateur = idUtilisateur;
        this.coordonneX = coordonneX;
        this.coordonneY = coordonneY;
        this.descriptionProbleme = descriptionProbleme;
        this.datySignalement = datySignalement;
        this.photo = photo;
        this.idProbleme = idProbleme;
        this.idStatut = idStatut;
        this.designationStatut = designationStatut;
        this.designationProbleme = designationProbleme;
    }
    
    public Signalement(int idSignalement, int idUtilisateur, double coordonneX, double coordonneY, String descriptionProbleme, Date datySignalement, String photo, int idProbleme, int idStatut, String designationStatut, String designationProbleme, String utilisateur) {
        this.idSignalement = idSignalement;
        this.idUtilisateur = idUtilisateur;
        this.coordonneX = coordonneX;
        this.coordonneY = coordonneY;
        this.descriptionProbleme = descriptionProbleme;
        this.datySignalement = datySignalement;
        this.photo = photo;
        this.idProbleme = idProbleme;
        this.idStatut = idStatut;
        this.designationStatut = designationStatut;
        this.designationProbleme = designationProbleme;
        this.utilisateur = utilisateur;
    }

    public Signalement(String designationProbleme, String designationStatut){
        this.designationProbleme = designationProbleme;
        this.designationStatut = designationStatut;
    }

    public Signalement(int idSignalement, double coordonneeX, double coordonneeY, String designationRegion, String designationProbleme, String etatStatut){
        this.idSignalement = idSignalement;
        this.coordonneX = coordonneeX;
        this.coordonneY = coordonneeY;
        this.designationRegion = designationRegion;
        this.designationProbleme = designationProbleme;
        this.etatStatut = etatStatut;
    }


    public Signalement(String etatStatut, int compteur){
        this.etatStatut = etatStatut;
        this.compteur = compteur;
    }


    public Signalement(String designationRegion, String etatStatut, double pourcentage){
        this.designationRegion = designationRegion;
        this.etatStatut = etatStatut;
        this.pourcentage = pourcentage;
    }


    public int getIdSignalement() {
        return idSignalement;
    }

    public void setIdSignalement(int idSignalement) {
        this.idSignalement = idSignalement;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public String getDescriptionProbleme() {
        return descriptionProbleme;
    }

    public void setDescriptionProbleme(String descriptionProbleme) {
        this.descriptionProbleme = descriptionProbleme;
    }

    public Date getDatySignalement() {
        return datySignalement;
    }

    public void setDatySignalement(Date datySignalement) {
        this.datySignalement = datySignalement;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(int idProbleme) {
        this.idProbleme = idProbleme;
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }
    
    public String getDesignationStatut() {
        return designationStatut;
    }

    public void setDesignationStatut(String designationStatu) {
        this.designationStatut = designationStatu;
    }

    public String getDesignationProbleme() {
        return designationProbleme;
    }

    public void setDesignationProbleme(String designationProbleme) {
        this.designationProbleme = designationProbleme;
    }
    
    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDesignationRegion() {
        return designationRegion;
    }

    public void setDesignationRegion(String designationRegion) {
        this.designationRegion = designationRegion;
    }

    public String getEtatStatut() {
        return etatStatut;
    }

    public void setEtatStatut(String etatStatut) {
        this.etatStatut = etatStatut;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public List<Signalement> allSignalement(){
        List<Signalement> liste = new ArrayList();
        String request = "SELECT * FROM Signalement";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Signalement : "+request);
            while (res.next()){                        
                int idSignalement = res.getInt(1);
                int idUtilisateur = res.getInt(2);
                double coordonneX = res.getDouble(3);
                double coordonneY = res.getDouble(4);
                String descriptionProbleme = res.getString(5);
                Date datySignalement = res.getDate(6);
                String photo = res.getString(7);
                int idProbleme = res.getInt(8);
                int idStatut = res.getInt(9);
            
                liste.add(new Signalement(idSignalement,idUtilisateur,coordonneX,coordonneY,descriptionProbleme,datySignalement,photo,idProbleme,idStatut));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public List<Signalement> allSignalementEtat(){
        List<Signalement> liste = new ArrayList();
        String request = "SELECT signalement.*,statut.etatStatut,probleme.designationProbleme,utilisateur.loginUtilisateur FROM signalement JOIN probleme ON signalement.idProbleme = probleme.idProbleme JOIN statut ON statut.idStatut = signalement.idStatut JOIN Utilisateur ON Signalement.idUtilisateur = Utilisateur.idUtilisateur";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            Region a=new Region();
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Signalement Etat : "+request);
            while (res.next()){                        
                int idSignalement = res.getInt("idSignalement");
                int idUtilisateur = res.getInt("idUtilisateur");
                double coordonneX = res.getDouble("coordonneX");
                double coordonneY = res.getDouble("coordonneY");
                String descriptionProbleme = res.getString("descriptionProbleme");
                Date datySignalement = res.getDate("datySignalement");
                String photo = res.getString("photo");
                int idProbleme = res.getInt("idProbleme");
                String designationStatut = res.getString("statut.etatStatut");
                String designationProbleme = res.getString("probleme.designationProbleme");
                liste.add(new Signalement(idSignalement,idUtilisateur,null,descriptionProbleme,datySignalement,photo,designationProbleme,designationStatut, idProbleme,regionId,coordonneX,coordonneY));
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public void deleteSignalement(int idSignalement){
        String request = "DELETE FROM Signalement WHERE idSignalement = "+idSignalement;
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

    public void updateSignalement(int idSignalement, int idStatu){
        String request = "UPDATE Signalement SET idStatut = "+idStatu+" WHERE idSignalement = "+idSignalement;
        Statement stmt;
        Connection connex;
        try {
            System.out.println("Update signalement : "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            connex.close();
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
    }
    public static void updateAffecte(int idSignalement,int idRegion){
        String request = "UPDATE Signalement SET isAffecter = 1 WHERE idSignalement = "+idSignalement;
        Statement stmt;
        Connection connex;
        try {
            System.out.println("Update signalement : "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            request = "UPDATE Signalement SET idRegion ="+1+" WHERE idSignalement = "+idSignalement;
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            connex.close();
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
    }

    public int insertSignalement(int idUtilisateur, double coordonneX, double coordonneY, String descriptionProbleme, int idProbleme, String photo){
        String request = "INSERT INTO Signalement(idUtilisateur, coordonneX, coordonneY, descriptionProbleme, datySignalement, photo, idProbleme, idStatut) VALUES ("+idUtilisateur+","+coordonneX+","+coordonneY+",'"+descriptionProbleme+"', now(),'"+photo.trim()+"',"+idProbleme+",3)";
        Statement stmt;
        int retour = 0;
        Connection connex;
        try {
            System.out.println("Insert signalement : "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            retour = 1;
            connex.close();
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
        return retour;
    }

    public List<Signalement> signalementByEtat(int id){
        List<Signalement> liste = new ArrayList();
        String request = "SELECT signalement.*,statut.etatStatut,probleme.designationProbleme,utilisateur.loginUtilisateur FROM signalement JOIN probleme ON signalement.idProbleme = probleme.idProbleme JOIN statut ON statut.idStatut = signalement.idStatut JOIN Utilisateur ON Signalement.idUtilisateur = Utilisateur.idUtilisateur where Statut.idStatut="+id;
        Statement stmt;
        Connection connex;
        int i = 0;
        Region region = new Region();
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Signalement Etat : "+request);
            while (res.next()){                        
                int idSignalement = res.getInt(1);
                // int idUtilisateur = res.getInt(2);
                double coordonneX = res.getDouble(3);
                double coordonneY = res.getDouble(4);
                // String descriptionProbleme = res.getString(5);
                // Date datySignalement = res.getDate(6);
                // String photo = res.getString(7);
                // int idProbleme = res.getInt(8);
                // int idStatut = res.getInt(9);
                String etatStatut = res.getString(10);
                String designationProbleme = res.getString(11);
                // String utilisateur = res.getString(12);
                String designationRegion = region.getRegionByCoordonne(coordonneX,coordonneY);
                liste.add(new Signalement(idSignalement,coordonneX,coordonneY,designationRegion,designationProbleme,etatStatut));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public List<Signalement> countByStatut(String designationRegion) {
        List<Signalement> liste = new ArrayList<Signalement>();
        Statement stmt;
        Connection connex;
        Statut statut = new Statut();
        List<Statut> allIdStat= statut.allStatut();
        Region region = new Region();
        int compte = 0;
        for (int i = 0; i < allIdStat.size(); i++) {
            String request = "SELECT coordonneX,coordonneY,count(idSignalement) FROM Signalement JOIN Probleme ON Signalement.idProbleme = Probleme.idProbleme JOIN Statut ON Statut.idStatut = Signalement.idStatut WHERE Signalement.idStatut = "+allIdStat.get(i).getIdStatut()+" GROUP BY Probleme.idProbleme,coordonneX,coordonneY";
            try {
                connex = Connexion.con(); 
                stmt = connex.createStatement();
                ResultSet res = stmt.executeQuery(request);
                System.out.println("Requete all Signalement Etat : "+request);
                while (res.next()){   
                    double cX = res.getDouble(3);
                    double cY = res.getDouble(4);
                    String regionParCoodonne = region.getRegionByCoordonne(cX,cY);
                    String etatStatut = res.getString(13);
                    if(designationRegion.trim().equals(regionParCoodonne)){
                        compte++;
                        if(compte != 0){
                            liste.add(new Signalement(etatStatut, compte)); 
                            break;
                        }                         
                    } 
                    else{
                        liste.add(new Signalement(etatStatut, 0)); 
                    }                                                    
                }
                connex.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return liste;
    } 

    public Signalement countByStatut1(String designationRegion) {
        Signalement liste = new Signalement();
        Statement stmt;
        Connection connex;
        Statut statut = new Statut();
        List<Statut> allIdStat= statut.allStatut();
        Region region = new Region();
        int compte = 0;
        String etatStatut = new String();
            String request = "SELECT coordonneX,coordonneY,count(idSignalement) FROM Signalement JOIN Probleme ON Signalement.idProbleme = Probleme.idProbleme JOIN Statut ON Statut.idStatut = Signalement.idStatut WHERE Signalement.idStatut = 1 GROUP BY Probleme.idProbleme,coordonneX, coordonneY";
            try {
                connex = Connexion.con(); 
                stmt = connex.createStatement();
                ResultSet res = stmt.executeQuery(request);
                System.out.println("Requete all Signalement Etat : "+request);
                while (res.next()){   
                    double cX = res.getDouble(1);
                    double cY = res.getDouble(2);
                    String regionParCoodonne = region.getRegionByCoordonne(cX,cY);
                    etatStatut = res.getString(13);
                    if(designationRegion.trim().equals(regionParCoodonne)){
                        compte++;                           
                    } 
                                                                       
                }
                connex.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        liste =new Signalement(etatStatut, compte); 
        return liste;
    } 


    public Signalement countByStatut2(String designationRegion) {
        Signalement liste = new Signalement();
        Statement stmt;
        Connection connex;
        Statut statut = new Statut();
        List<Statut> allIdStat= statut.allStatut();
        Region region = new Region();
        int compte = 0;
        String etatStatut = new String();
            String request = "SELECT coordonneX,coordonneY,count(idSignalement) FROM Signalement JOIN Probleme ON Signalement.idProbleme = Probleme.idProbleme JOIN Statut ON Statut.idStatut = Signalement.idStatut WHERE Signalement.idStatut = 2 GROUP BY Probleme.idProbleme,coordonneX, coordonneY";
            try {
                connex = Connexion.con(); 
                stmt = connex.createStatement();
                ResultSet res = stmt.executeQuery(request);
                System.out.println("Requete all Signalement Etat : "+request);
                while (res.next()){   
                    double cX = res.getDouble(1);
                    double cY = res.getDouble(2);
                    String regionParCoodonne = region.getRegionByCoordonne(cX,cY);
                    etatStatut = res.getString(13);
                    if(designationRegion.trim().equals(regionParCoodonne)){
                        compte++;                           
                    } 
                                                                       
                }
                connex.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        liste =new Signalement(etatStatut, compte); 
        return liste;
    } 


    public Signalement countByStatut3(String designationRegion) {
        Signalement liste = new Signalement();
        Statement stmt;
        Connection connex;
        Statut statut = new Statut();
        List<Statut> allIdStat= statut.allStatut();
        Region region = new Region();
        int compte = 0;
        String etatStatut = new String();
            String request = "SELECT coordonneX,coordonneY,count(idSignalement) FROM Signalement JOIN Probleme ON Signalement.idProbleme = Probleme.idProbleme JOIN Statut ON Statut.idStatut = Signalement.idStatut WHERE Signalement.idStatut = 3 GROUP BY Probleme.idProbleme,coordonneX, coordonneY";
            try {
                connex = Connexion.con(); 
                stmt = connex.createStatement();
                ResultSet res = stmt.executeQuery(request);
                System.out.println("Requete all Signalement Etat : "+request);
                while (res.next()){   
                    double cX = res.getDouble(1);
                    double cY = res.getDouble(2);
                    String regionParCoodonne = region.getRegionByCoordonne(cX,cY);
                    etatStatut = res.getString(13);
                    if(designationRegion.trim().equals(regionParCoodonne)){
                        compte++;                           
                    } 
                                                                       
                }
                connex.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        liste =new Signalement(etatStatut, compte); 
        return liste;
    } 

    public List<Signalement> statByStatut(String designationRegion) {
        List<Signalement> liste = new ArrayList();
        Signalement signalement = new Signalement();
        Signalement getListe1 = signalement.countByStatut1(designationRegion);
        Signalement getListe2 = signalement.countByStatut2(designationRegion);
        Signalement getListe3 = signalement.countByStatut3(designationRegion);
        liste.add(new Signalement(getListe1.getEtatStatut(), getListe1.getCompteur()));
        liste.add(new Signalement(getListe2.getEtatStatut(), getListe2.getCompteur()));
        liste.add(new Signalement(getListe3.getEtatStatut(), getListe3.getCompteur()));
        return liste;
    }

    public List<Signalement> pourcentageByStat(String designationRegion){
        List<Signalement> liste = new ArrayList();
        int somme = 0;
        Signalement signalement = new Signalement();
        List<Signalement> listeStat = signalement.statByStatut(designationRegion);
        
        for (int i = 0; i < listeStat.size(); i++) {
            somme = somme + listeStat.get(i).getCompteur();
        }
        
        double[] calclul = new double[listeStat.size()];
    	for (int i = 0; i < listeStat.size(); i++) {
    		if(somme == 0) {
    			liste.add(new Signalement(designationRegion, listeStat.get(i).getEtatStatut(),0));
            }
    		else {
    			calclul[i] = ((listeStat.get(i).getCompteur() * 100 )/somme);
    			liste.add(new Signalement(designationRegion, listeStat.get(i).getEtatStatut(),calclul[i]));
    		} 
    	}
        return liste;
    }
    public static String login(int id){
        String liste = null;
        String request = "SELECT signalement.idSignalement ,Utilisateur.loginUtilisateur FROM signalement JOIN Utilisateur ON signalement.idUtilisateur = Utilisateur.idUtilisateur where signalement.idSignalement ="+id;
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next()){
                liste=res.getString(2);
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public List<Signalement> signalementByIdUtilisateur(int idUtil){
        List<Signalement> liste = new ArrayList();
        String request = "SELECT signalement.*,statut.etatStatut,probleme.designationProbleme FROM signalement JOIN probleme ON signalement.idProbleme = probleme.idProbleme JOIN statut ON statut.idStatut = signalement.idStatut where signalement.idUtilisateur="+idUtil;
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next()){                        
            	int idSignalement = res.getInt(1);
                int idUtilisateur = res.getInt(2);
                double coordonneX = res.getDouble(3);
                double coordonneY = res.getDouble(4);
                String descriptionProbleme = res.getString(5);
                Date datySignalement = res.getDate(6);
                String photo = res.getString(7);
                int idProbleme = res.getInt(8);
                int idStatut = res.getInt(9);
                String designationStatut = res.getString(10);
                String designationProbleme = res.getString(11);
            
                liste.add(new Signalement(idSignalement,idUtilisateur,coordonneX,coordonneY,descriptionProbleme,datySignalement,photo,idProbleme,idStatut,designationStatut,designationProbleme));
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public List<Signalement> fonctionTest() {
        List<Signalement> liste = new ArrayList();
        String request = "SELECT * FROM Signalement";
        Statement stmt;
        Connection connex;
        int i = 1;
        try {
            Region a=new Region();
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Signalement Etat : "+request);
            while (res.next()){                        
                double coordonneX = res.getDouble(3);
                double coordonneY = res.getDouble(4);
                String region=a.getRegionByCoordonne(coordonneX, coordonneY);
                System.out.println(i+"- "+"X= "+coordonneX+" Y= "+coordonneY +" -> "+region);
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    public List<Signalement> signalementParRegion(String id){
    	List<Signalement> liste = new ArrayList();
    	Region regionById = Region.RegionbyId(id);
    	String request = "select * from signalement JOIN Probleme ON Signalement.idProbleme = Probleme.idProbleme JOIN Statut ON Statut.idStatut = Signalement.idStatut where isAffecter =1 and idRegion = "+regionById.idRegion;
    	Statement stmt;
        Connection connex;
        int i = 1;
        try {
            Region a=new Region();
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete signalemant Par Region : "+request);
            while (res.next()){
            	int idSignalement = res.getInt("idSignalement");
                int idUtilisateur = res.getInt("idUtilisateur");
                double coordonneX = res.getDouble("coordonneX");
                double coordonneY = res.getDouble("coordonneY");
                String descriptionProbleme = res.getString("descriptionProbleme");
                Date datySignalement = res.getDate("datySignalement");
                String photo = res.getString("photo");
                int idProbleme = res.getInt("idProbleme");
                String designationStatut = res.getString("statut.etatStatut");
                String designationProbleme = res.getString("probleme.designationProbleme");
                liste.add(new Signalement(idSignalement,idUtilisateur,null,descriptionProbleme,datySignalement,photo,designationProbleme,designationStatut, idProbleme,regionId,coordonneX,coordonneY));
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return liste;
    }
    
    public List<Signalement>/*double*/ signalementRegion(String name){
    	List<Signalement> liste = new ArrayList();
    	Region region=new Region();
    	String request = "select * from signalement JOIN Probleme ON Signalement.idProbleme = Probleme.idProbleme JOIN Statut ON Statut.idStatut = Signalement.idStatut where isAffecter =1 and idRegion = "+name;
    	Statement stmt;
        Connection connex;
        int i = 1;
        try {
            Region a=new Region();
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete signalemant Par Region : "+request);
            while (res.next()){
            	int idSignalement = res.getInt("idSignalement");
                int idUtilisateur = res.getInt("idUtilisateur");
                double coordonneX = res.getDouble("coordonneX");
                double coordonneY = res.getDouble("coordonneY");
                String descriptionProbleme = res.getString("descriptionProbleme");
                Date datySignalement = res.getDate("datySignalement");
                String photo = res.getString("photo");
                int idProbleme = res.getInt("idProbleme");
                String designationStatut = res.getString("statut.etatStatut");
                String designationProbleme = res.getString("probleme.designationProbleme");
                liste.add(new Signalement(idSignalement,idUtilisateur,null,descriptionProbleme,datySignalement,photo,designationProbleme,designationStatut, idProbleme,regionId,coordonneX,coordonneY));
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return liste;
    }

    // public static void main(String[] args){
    //     Signalement signalement = new Signalement();
    //     List<Signalement> liste = signalement.fonctionTest();
        // System.out.println(liste.size());
        // for (int i = 0; i < liste.size(); i++) {
        //     System.out.println(liste.get(i).getIdProbleme());
            
        // }
    // }
}

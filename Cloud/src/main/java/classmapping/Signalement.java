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


    public Signalement() {
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
        String request = "SELECT signalement.*,statut.etatStatut,probleme.designationProbleme,utilisateur.loginUtilisateur FROM `signalement` JOIN probleme ON signalement.idProbleme = probleme.idProbleme JOIN statut ON statut.idStatut = signalement.idStatut JOIN Utilisateur ON Signalement.idUtilisateur = Utilisateur.idUtilisateur";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Signalement Etat : "+request);
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
                String utilisateur = res.getString(12);
            
                liste.add(new Signalement(idSignalement,idUtilisateur,coordonneX,coordonneY,descriptionProbleme,datySignalement,photo,idProbleme,idStatut,designationStatut,designationProbleme,utilisateur));               
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
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
    }

    public void insertSignalement(int idUtilisateur, double coordonneX, double coordonneY, String descriptionProbleme, String datySignalement, String photo){
        int idProbleme = 0;
        String request = "INSERT INTO Signalement(idUtilisateur, coordonneX, coordonneY, descriptionProbleme, datySignalement, photo, idProbleme, idStatut) VALUES ("+idUtilisateur+","+coordonneX+","+coordonneY+",'"+descriptionProbleme+"', DATE'"+datySignalement+"',"+photo.trim()+","+idProbleme+",3)";
        Statement stmt;
        Connection connex;
        try {
            System.out.println("Insert signalement : "+request);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
        } catch (SQLException ex) {           
            ex.printStackTrace();
        }
    }

    public List<Signalement> signalementByEtat(int id){
        List<Signalement> liste = new ArrayList();
        String request = "SELECT signalement.*,statut.etatStatut,probleme.designationProbleme,utilisateur.loginUtilisateur FROM `signalement` JOIN probleme ON signalement.idProbleme = probleme.idProbleme JOIN statut ON statut.idStatut = signalement.idStatut JOIN Utilisateur ON Signalement.idUtilisateur = Utilisateur.idUtilisateur where Statut.idStatut="+id;
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
                int idUtilisateur = res.getInt(2);
                double coordonneX = res.getDouble(3);
                double coordonneY = res.getDouble(4);
                String descriptionProbleme = res.getString(5);
                Date datySignalement = res.getDate(6);
                String photo = res.getString(7);
                int idProbleme = res.getInt(8);
                int idStatut = res.getInt(9);
                String etatStatut = res.getString(10);
                String designationProbleme = res.getString(11);
                String utilisateur = res.getString(12);
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

    public static void main(String[] args){
        Signalement signalement = new Signalement();
        List<Signalement> liste = signalement.signalementByEtat(3);
        for (int i = 0; i < liste.size(); i++) {
            System.out.println("Id- "+liste.get(i).getIdSignalement()+" Region: "+liste.get(i).getDesignationRegion()+" Probleme: "+liste.get(i).getDesignationProbleme()+" Staut: "+liste.get(i).getEtatStatut());
        }
    }
}

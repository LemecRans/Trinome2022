package classmapping;

import java.util.ArrayList;
import java.util.List;

public class ProblemeRegion {
	int idProblemeRegion;
    int idProbleme;
    int idRegion;
    int compteur;
    String designationProbleme;
    String designationRegion;
    double pourcentage;

    public ProblemeRegion() {
    }

    public ProblemeRegion(int idProblemeRegion, int idProbleme, int idRegion, int compteur) {
        this.idProblemeRegion = idProblemeRegion;
        this.idProbleme = idProbleme;
        this.idRegion = idRegion;
        this.compteur = compteur;
    }

    public ProblemeRegion(int idProblemeRegion, String designationProbleme, String designationRegion, int compteur) {
        this.idProblemeRegion = idProblemeRegion;
        this.designationProbleme = designationProbleme;
        this.designationRegion = designationRegion;
        this.compteur = compteur;
    }

    public ProblemeRegion(int idProblemeRegion, String designationProbleme, String designationRegion, int compteur, double pourcentage) {
        this.idProblemeRegion = idProblemeRegion;
        this.designationProbleme = designationProbleme;
        this.designationRegion = designationRegion;
        this.compteur = compteur;
        this.pourcentage = pourcentage;
    }

    public int getIdProblemeRegion() {
        return idProblemeRegion;
    }

    public void setIdProblemeRegion(int idProblemeRegion) {
        this.idProblemeRegion = idProblemeRegion;
    }

    public int getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(int idProbleme) {
        this.idProbleme = idProbleme;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    public String getDesignationProbleme() {
        return designationProbleme;
    }

    public void setDesignationProbleme(String designationProbleme) {
        this.designationProbleme = designationProbleme;
    }


    public String getDesignationRegion() {
        return designationRegion;
    }

    public void setDesignationRegion(String designationRegion) {
        this.designationRegion = designationRegion;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public List<ProblemeRegion> getProblemeRegion(int id){
        List<ProblemeRegion> liste = new ArrayList();
        String request = "select ProblemeRegion.idProblemeRegion, Probleme.designationProbleme, region.designationRegion, ProblemeRegion.compteur from ProblemeRegion JOIN Probleme ON ProblemeRegion.idProbleme = Probleme.idProbleme JOIN Region ON ProblemeRegion.idRegion = region.idRegion WHERE region.idRegion="+id;
        java.sql.Statement stmt;
        java.sql.Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            java.sql.ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete get Probleme By Region : "+request);
            while (res.next()){
                int idProblemeRegion = res.getInt(1);
                String designationProbleme = res.getString(2);
                String designationRegion = res.getString(3);
                int compteur = res.getInt(4);
                liste.add(new ProblemeRegion(idProblemeRegion, designationProbleme, designationRegion, compteur));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public int sommeCompteur(int id){
        String request = "select sum(ProblemeRegion.compteur) from ProblemeRegion JOIN Probleme ON ProblemeRegion.idProbleme = Probleme.idProbleme JOIN Region ON ProblemeRegion.idRegion = region.idRegion WHERE region.idRegion="+id;
        java.sql.Statement stmt;
        java.sql.Connection connex;
        int somme = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            java.sql.ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete get Probleme By Region : "+request);
            while (res.next()){
                somme=res.getInt(1);
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return somme;
    }


    public List<ProblemeRegion> getPourcentageParRegion(int id){
        List<ProblemeRegion> liste = new ArrayList();
        ProblemeRegion problemeRegion = new ProblemeRegion();
        int countAllProbleme = problemeRegion.sommeCompteur(id);
        List<ProblemeRegion> listeProbleme = problemeRegion.getProblemeRegion(id);
        for (int i = 0; i < listeProbleme.size(); i++) {
            double pourcentage = (listeProbleme.get(i).getCompteur() * (double)100)/(double)countAllProbleme;
            liste.add(new ProblemeRegion(listeProbleme.get(i).getIdProblemeRegion(),listeProbleme.get(i).getDesignationProbleme(), listeProbleme.get(i).getDesignationRegion(), listeProbleme.get(i).getCompteur(), pourcentage));
        }
        return liste;
    }

    // public static void main(String[] args){
    //     ProblemeRegion problemeRegion = new ProblemeRegion();
    //     List<ProblemeRegion> liste = problemeRegion.getPourcentageParRegion(1);
    //     for (int i = 0; i < liste.size(); i++){
    //         System.out.println("Region: " + liste.get(i).getDesignationRegion()+"- Probleme: "+ liste.get(i).getDesignationProbleme()+"- Pourcentage: "+ liste.get(i).getPourcentage()+"%");
    //     }
    // }
}

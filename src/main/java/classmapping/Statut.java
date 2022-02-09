package classmapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Statut {
	int idStatut;
    String etatStatut;

    public Statut() {
    }

    public Statut(int idStatut, String etatStatut) {
        this.idStatut = idStatut;
        this.etatStatut = etatStatut;
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public String getEtatStatut() {
        return etatStatut;
    }

    public void setEtatStatut(String etatStatut) {
        this.etatStatut = etatStatut;
    }
    
    public List<Statut> allStatut(){
        List<Statut> liste = new ArrayList();
        String request = "select * from Statut";
        Statement stmt;
        Connection connex;
        int i = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all Statut : "+request);
            while (res.next()){
                int idStatut  = res.getInt(1);                
                String etatStatut  = res.getString(2);             
                liste.add(new Statut(idStatut, etatStatut));               
                i++;
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }
    
    public static void main(String[] args) {
    	Statut statut = new Statut();
		List<Statut> liste = statut.allStatut();
		for(int i=0; i<liste.size(); i++) {
			System.out.println(liste.get(i).getEtatStatut());
		}
    }
}

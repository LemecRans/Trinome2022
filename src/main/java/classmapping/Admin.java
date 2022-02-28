package classmapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    int idAdmin;
    String loginAdmin;
    String mdpAdmin;
    
    public Admin() {
    }

    public Admin(int idAdmin, String loginAdmin, String mdpAdmin) {
        this.idAdmin = idAdmin;
        this.loginAdmin = loginAdmin;
        this.mdpAdmin = mdpAdmin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public String getMdpAdmin() {
        return mdpAdmin;
    }

    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }
    
    
    public List<Admin> allAdmin(){
        List<Admin> liste = new ArrayList();
        String request = "select * from Admin";
        java.sql.Statement stmt;
        Connection connex;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            System.out.println("Requete all admin : "+request);
            while (res.next()){                        
                int idAdmin = res.getInt(1);
                String loginAdmin = res.getString(2);
                String mdpAdmin = res.getString(3);
                liste.add(new Admin(idAdmin,loginAdmin,mdpAdmin)); 
            }
            connex.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public int validConnex(String login, String mdp){
        int retour = 0;
        Admin admin = new Admin();
        List<Admin> liste = admin.allAdmin();
        for(int i=0; i<liste.size(); i++) {
            if(liste.get(i).getLoginAdmin().equals(login.trim()) == true && liste.get(i).getMdpAdmin().equals(mdp.trim()) == true){
                retour = 1;
            }
            else retour=0;
        }
        return retour;
    }
}

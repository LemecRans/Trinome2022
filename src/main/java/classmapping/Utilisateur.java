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

    public String inscrir(String login,String password,int idRegion)throws Exception
    {
        String req="INSERT INTO Utilisateur(loginUtilisateur,mdpUtilisateur,idRegion) values ('"+login+"','"+password+"',"+idRegion+")";
        Statement stmt;
        Connection connex;
        String valiny = null;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(req);
            valiny = "SUCCESS";
        } catch (Exception ex) {
            valiny = "ERROR";
            ex.printStackTrace();
            throw ex;

        }finally{
            Connexion.con().close();
        }
        return valiny;
    }
	
//    public int seConnecter(String login,String password)
//    {
//        Utilisateur utilisateurs = new Utilisateur();
//        List<Utilisateur> listeUtilisateur = utilisateurs.allUtilisateur();
//        int reponse=0;
//        Utilisateur[] utilisateur=new Utilisateur[listeUtilisateur.size()];
//        for (int i=0; i< listeUtilisateur.size() ;i++ ) {
//            utilisateur[i]=listeUtilisateur.get(i);
//            if(utilisateur[i].getLoginUtilisateur().compareTo(login)==0)
//            {
//                if(utilisateur[i].getMdpUtlisateur().compareTo(password)==0)
//                {
//                    reponse=utilisateur[i].getIdUtilisateur();
//                    break;
//                }
//                else{
//                    reponse=0;   
//                }
//            }
//            else{
//                reponse=0;
//            }
//        }
//        return reponse;
//    }
	
    public int updateUti(int idRegion,int idUtilisateur)throws Exception
    {
        String req="UPDATE Utilisateur SET idRegion="+idRegion+" where idUtilisateur="+idUtilisateur;
        Statement stmt;
        Connection connex;
        int retour = 0;
        try {
            System.out.println(req);
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(req);
            retour = 1;
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            throw ex;
        }finally{
            Connexion.con().close();
        }
        return retour;
    }
	
    public int deleteUti(int idUtilisateur)throws Exception
    {
        String request="DELETE FROM Utilisateur where idUtilisateur="+idUtilisateur;
        Statement stmt;
        Connection connex;
        int retour = 0;
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(request);
            retour = 1;
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            throw ex;
        }finally{
            Connexion.con().close();
        }
        return retour;
    }

    public List<String> listToken(String code,int idUtilisateur) {
        List<String> liste = new ArrayList<String>();
        boolean test=false;
        Statement stmt;
        Connection connex;
        String cody="";
        try {
            String request ="SELECT * FROM Token";
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next())
            {
                cody = res.getString("Token.code");  
                liste.add(cody);       
            }
            Connexion.con().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return liste;
	}

    public static boolean testToken(String code,int idUtilisateur) {
        boolean test=false;
        Statement stmt;
        Connection connex;
        String cody="";
        try {
            String request ="SELECT Token.code,Token.idUtilisateur FROM Token WHERE idUtilisateur ="+idUtilisateur;
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            ResultSet res = stmt.executeQuery(request);
            while (res.next())
            {
                cody = res.getString("Token.code");            
            }
            Connexion.con().close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(cody.equalsIgnoreCase(code)==false) {
            test=true;
        }
		return test;
    }

    public static void donnerToken(int idUtilisateur,int fonction) throws Exception {
    	String code="sign"+idUtilisateur;
        String valiny = "";
    	if(fonction==1) {
    		code=code+"Admin";
    	}
    	else {
    		code=code+"Utili";
    	}
    	String req="INSERT INTO token values (0,'"+code+"',"+idUtilisateur+")";
        Statement stmt;
        Connection connex;
        
        try {
            connex = Connexion.con(); 
            stmt = connex.createStatement();
            stmt.executeUpdate(req);
            valiny = "SUCCESS";
        } 
        catch (Exception ex) 
        {
            valiny = "ERROR";
            ex.printStackTrace();
            throw ex;

        }finally{
            Connexion.con().close();
        }
    }
    
    public String inscrirN(String login,String password,int idRegion)throws Exception
    {
        String req="INSERT INTO Utilisateur(loginUtilisateur,mdpUtilisateur,idRegion) values ('"+login+"','"+password+"',"+idRegion+")";
        Connection connex;
        Statement stmt;
        Function fonc=new Function();
        String valinyLogin=fonc.testLogin(login);
        String valinyMdp=fonc.test2(password);
        String rep=null;
        try {
        	if(valinyLogin.equals("Valide") && valinyMdp.equals("Valide"))
            {
                rep="Valide";
                connex = Connexion.con();
                stmt = connex.createStatement();
                stmt.executeUpdate(req);
            }
            if(valinyLogin.equals("Valide")==false && valinyMdp.equals("Valide"))
            {
                rep=valinyLogin;
            }
            if(valinyLogin.equals("Valide") && valinyMdp.equals("Valide")==false)
            {
                rep=valinyMdp;
            }
            if(valinyLogin.equals("Valide")==false && valinyMdp.equals("Valide")==false)
            {
                rep="No Valide";
            }
            return rep;
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            throw ex;
        }finally{
        	Connexion.con().close();
        }
    }

    public int seConnecter(String login,String password)
    {
        Utilisateur utilisateurs = new Utilisateur();
        List<Utilisateur> listeUtilisateur = utilisateurs.allUtilisateur();
        int reponse=0;
        Utilisateur[] utilisateur=new Utilisateur[listeUtilisateur.size()];
        for (int i=0; i< listeUtilisateur.size() ;i++ ) {
            utilisateur[i]=listeUtilisateur.get(i);
            if(utilisateur[i].getLoginUtilisateur().compareTo(login)==0)
            {
                if(utilisateur[i].getMdpUtlisateur().compareTo(password)==0)
                {
                    reponse=utilisateur[i].getIdUtilisateur();
                    break;
                }
                else{
                    reponse=0;   
                }
            }
            else{
                reponse=0;
            }
        }
        return reponse;
    }


    public static void  main(String[] args) {
        Utilisateur a = new Utilisateur();
        List<String> liste = a.listToken("",1);
        for (int index = 0; index < liste.size(); index++) {
            System.out.println(liste.get(index));
        }
    }
}

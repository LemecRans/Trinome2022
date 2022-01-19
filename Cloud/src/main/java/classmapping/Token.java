package classmapping;

import java.util.Date;


public class Token {
	int id;
	String code;
	public Token(int id, String code) {
		super();
		this.id = id;
		this.code = code;
	}
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Token generateToken(String login,int fonction) {
		char temp='a';
		char[] decomposition=login.toCharArray();
		temp=decomposition[0];
		decomposition[0]=decomposition[1];
		decomposition[1]=temp;
		String fonc="";
		if(fonction==1) {
			fonc="admin";
		}
		if(fonction==2) {
			fonc="utili";
		}
		login=new String(decomposition);
		login=login.repeat(2);
		login=login.substring(2);
		login=login.concat(fonc);
		Token valiny=new Token();
		valiny.code=login;
		return valiny;
	}
	public boolean testToken(String code,int fonction,int login) {
		char temp='a';
		char[] decomposition=code.toCharArray();
		int taille=decomposition.length;
		char[] fonc=new char[5];
		boolean test=false;
		int j=0;
		for(int i=taille-6;i<taille;i++) {
			fonc[j]=decomposition[i];
			j++;
		}
		if(fonction==1) {
			test=new String(fonc).equalsIgnoreCase("admin");
		}
		if(fonction==2) {
			test=new String(fonc).equalsIgnoreCase("utili");
		}
		if(test==true) {
			Connexion connexion= new Connexion();
			String cody="";
	        try {
	        	/*String request ="SELECT Token.code,Token.idUtilisateur,Utilisiateur.id,Utilisiateur.login FROM Token join Utiliasteur on Utilisiateur.id=token.idUtilisateur where Utilisiateur.login="+login;
	            java.sql.Statement stmt;
	            stmt = connexion.conn.createStatement();
	            ResultSet res = stmt.executeQuery(request);
	            while (res.next())
	            {
	                cody = res.getString("Token.code");            
	            }
	            connexion.conn.close();*/
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        if(cody.equalsIgnoreCase(code)==false) {
	        	test=false;
	        }
		}
		return test;
	}
}

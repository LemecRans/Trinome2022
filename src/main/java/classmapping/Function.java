package classmapping;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
	public String testLogin(String email)
    {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String rep=null;
        if(matcher.matches()==true)
        {
            rep="Valide";
        }
        else{
            rep="login No Valide";
        }
        return rep;
        //System.out.println(email +" : "+ matcher.matches());
    }
    public int testMdp(String mdp){
        //String rep=null;
        int rep=0;
        char[] mdpch=new char[mdp.length()];
        char[] contraint={'à','è','é','ê','â','ô','^','î','û','ù'};
        if(mdp.length()<8)
        {
            rep=-1;
        }
        else{
            for(int i=0;i<contraint.length;i++)
            {
                for(int a=0;a<mdp.length();a++)
                {
                    mdpch[a] = mdp.charAt(a);
                    if(mdpch[a]==contraint[i]){
                        rep=1;
                        break;
                    }
                }
            }
        }
        return rep;
    }
    public String test2(String mdp){
        int repInt=testMdp(mdp);
        String rep=null;
        if(repInt==-1){
            rep=" Veuiller entrer 8 caractere";
        }
        if(repInt==1){
            rep="Veuiller entrer un caractere sans accent";
        }
        if(repInt==0)
        {
            rep="Valide";
        }
        return rep;
    }
    
    public static void main(String[] args) {
    	Function function = new Function();
    	String testVoloany = function.test2("Aroniaina");
    	System.out.println(testVoloany);
    }
}

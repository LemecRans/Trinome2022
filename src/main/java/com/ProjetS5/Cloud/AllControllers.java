package com.ProjetS5.Cloud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import classmapping.*;

@RestController
public class AllControllers {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
	public Utilisateur welcome(@PathVariable("login")String login){
		Utilisateur a= Utilisateur.connexion(login.split("°")[0], login.split("°")[1]);
		return a;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeSignalement")
	public List <Signalement> listeSignalement(){
		Signalement signalement = new Signalement();
		List<Signalement> liste = signalement.allSignalementEtat();
		return liste;
	}

    @CrossOrigin(origins = "*")
	@RequestMapping("/listeRegion")
	public List <Region> listeRegion(){
		Region region = new Region();
		List<Region> liste = region.allRegion();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/listeStat")
	public List<Probleme> listeStat(){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.calculPourcentage();
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeProbleme")
	public List<Probleme> listeProbleme(){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.allProbleme();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRechercheRegion/{motAChercher}", method = RequestMethod.GET)
	public List<String> listeRechercheRegion(@PathVariable("motAChercher") String motAChercher){
		Region region = new Region();
		List<String> liste = region.rechercheRegion(motAChercher);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRechercheProbleme/{motAChercher}", method = RequestMethod.GET)
	public List<Probleme> listeRechercheProbleme(@PathVariable("motAChercher") String motAChercher){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.rechercheProbleme(motAChercher);
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/insertRegion/{designationRegion}/{coordonneX}/{coordonneY}/{coordonneX1}/{coordonneY1}", method = RequestMethod.GET)
	public void insertRegion(@PathVariable("designationRegion")String designationRegion, @PathVariable("coordonneX")String coordonneX, @PathVariable("coordonneY")String coordonneY, @PathVariable("coordonneX1")String coordonneX1, @PathVariable("coordonneY1")String coordonneY1){
		Region region = new Region();
		double cx = Double.parseDouble(coordonneX);
		double cy = Double.parseDouble(coordonneY);
		double cx1 = Double.parseDouble(coordonneX1);
		double cy1 = Double.parseDouble(coordonneY1); 
		region.insertRegion(designationRegion, cx, cy, cx1, cy1);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeliste/{id}", method = RequestMethod.GET)
	public List<ProblemeRegion> listeliste(@PathVariable("id")int id){
		ProblemeRegion problemeRegion = new ProblemeRegion();
        List<ProblemeRegion> liste = problemeRegion.getPourcentageParRegion(id);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/statByStatut/{id}", method = RequestMethod.GET)
	public List<Signalement> statByStatut(@PathVariable("id")int id){
		Signalement signalement = new Signalement();
        List<Signalement> liste = signalement.signalementByEtat(id);
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRecherchePro/{motAChercher}", method = RequestMethod.GET)
	public List<Probleme> listeRecherchePro(@PathVariable("motAChercher") String motAChercher){
		String blem="";
		String region="";
		String statut="";
		if(motAChercher.equalsIgnoreCase("")==false) {
			if(motAChercher.split("!").length>1) {
				if(motAChercher.split("=")[0].equalsIgnoreCase("")==false) {
					blem=motAChercher.split("=")[0];
					System.out.println(blem);
				}
			}
			if(motAChercher.split("=").length>1) {
				if(motAChercher.split("=")[1].equalsIgnoreCase("")==false) {
					region=motAChercher.split("=")[1];
					if(region.split("!")[0].equalsIgnoreCase("")==false) {
						region=region.split("!")[0];
						System.out.println(region);
					}
				}
			}
			if(motAChercher.split("!").length>1) {
				if(motAChercher.split("!")[1].equalsIgnoreCase("")==false) {
					statut=motAChercher.split("!")[1];
					System.out.println(statut);
				}
			}
		}
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.recherchePro(blem,region,statut);
		for(int i=0; i< liste.size();i++){
			System.out.println("ty le izy : "+liste.get(i).getDesignationProbleme());
		}
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeRechercheProblemeParRegion/{motAChercher}", method = RequestMethod.GET)
	public List<Region> listeRechercheProblemeParRegion(@PathVariable("motAChercher") String motAChercher){
		Region region = new Region();
		List<Region> liste = region.rechercheProblemeParRegion(motAChercher);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/listeAdmin")
	public List<Admin> listeAdmin(){
		Admin admin = new Admin();
		List<Admin> liste = admin.allAdmin();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteSignalement/{idSignalement}", method = RequestMethod.GET)
	public String deleteSignalement(@PathVariable("idSignalement") int idSignalement){
		Signalement signalement = new Signalement();
		signalement.deleteSignalement(idSignalement);
		return "succes";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteRegion/{id}", method = RequestMethod.GET)
	public String deleteRegion(@PathVariable("id")String  id){
		Region.delete(id);
		return "succes";
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateSignalement/{idSignalement}/{idStatut}", method = RequestMethod.GET)
	public void updateSignalement(@PathVariable("idSignalement") int idSignalement, @PathVariable("idStatut")int idStatut){
		Signalement signalement = new Signalement();
		signalement.updateSignalement(idSignalement, idStatut);
		System.out.println("idSignalemet : "+idSignalement);
		System.out.println("idStatut : "+idStatut);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeStatut")
	public List<Statut> listeStatut(){
		Statut statut = new Statut();
		List<Statut> liste = statut.allStatut();
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/listeUtilisateur")
	public List<Utilisateur> listeUtilisateur(){
		Utilisateur utilisateur = new Utilisateur();
		List<Utilisateur> liste = utilisateur.allUtilisateur();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping("/lista")
	public List<Probleme> lista(){
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.allBleme();
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/valideConnex/{loginAdmin}/{mdpAdmin}" , method = RequestMethod.GET)
	public int valideConnex(@PathVariable("loginAdmin") String loginAdmin, @PathVariable("mdpAdmin") String mdpAdmin){
		Admin admin = new Admin();
		int retour = admin.validConnex(loginAdmin, mdpAdmin);
		return retour;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/statByStatut/mande/{designationRegion}" , method = RequestMethod.GET)
	public List<Signalement> statByStatut(@PathVariable("designationRegion") String designationRegion){
		Signalement stat = new Signalement();
		List<Signalement> list = stat.pourcentageByStat(designationRegion);
		System.out.println(list.size());
		return list;
	}

	// http://localhost:9000/inscrire/InscriUtilisateur/Hafa/hafa/2
//	@CrossOrigin(origins = "*")
//	@RequestMapping(value = "/inscrire/InscriUtilisateur/{login}/{password}/{idRegion}" , method = RequestMethod.GET)
//	public String inscrire(@PathVariable("login") String login, @PathVariable("password") String password,@PathVariable("idRegion") int idRegion){
//		Utilisateur utilisateur = new Utilisateur();
//		String valiny = "";
//		try {
//			valiny = utilisateur.inscrir(login, password, idRegion);
//		} catch (Exception e) {
//			e.getMessage();
//		}
//		return valiny;
//	}


	// http://localhost:9000/seConnecertUtilisateur/Manda/manda
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/seConnecertUtilisateur/{login}/{password}" , method = RequestMethod.GET)
	public String inscrire(@PathVariable("login") String login, @PathVariable("password") String password){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		try {
			valiny = utilisateur.seConnecter(login, password);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny != 0){
			retour = "BIENVENUE";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}


	// http://localhost:9000/updateUtilisateur/2/1
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateUtilisateur/{idRegion}/{idUtilisateur}/{token}" , method = RequestMethod.GET)
	public String inscrire(@PathVariable("idRegion") int idRegion, @PathVariable("idUtilisateur") int idUtilisateur){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		try {
			valiny = utilisateur.updateUti(idRegion, idUtilisateur);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}

	// http://localhost:9000/deleteUtilisateur/23
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteUtilisateur/{idUtilisateur}/{token}" , method = RequestMethod.GET)
	public String inscrire(@PathVariable("idUtilisateur") int idUtilisateur){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		try {
			valiny = utilisateur.deleteUti(idUtilisateur);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}


	// http://localhost:9000/insertSignalement/1/1002/1002/Tsy/1/sary.jpg
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/insertSignalement/signaler" , method = RequestMethod.GET ,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String inscrire(@RequestPart("file") MultipartFile file,@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("coordonneX") double coordonneX,@RequestPart("coordonneY") double coordonneY,@RequestPart("descriptionProbleme") String descriptionProbleme,@RequestPart("idProbleme") int idProbleme)throws IOException{
		Signalement signalement = new Signalement();
		int valiny = 0;
		String retour = "";
		try {
			valiny = signalement.insertSignalement(idUtilisateur,coordonneX,coordonneY,descriptionProbleme,idProbleme,file.getOriginalFilename());
			String FILE_DIRECTORY="./";
			File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}


	// http://localhost:9000/mySignalement/2
	// @CrossOrigin(origins = "*")
	// @RequestMapping(value = "/mySignalement/{idUtilisateur}/{token}" , method = RequestMethod.GET)
	// public List<Signalement> mySignalement(@PathVariable("idUtilisateur") int idUtilisateur){
	// 	Signalement signalement = new Signalement();
	// 	List<Signalement> list = signalement.signalementByIdUtilisateur(idUtilisateur);
	// 	return list;
	// }

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/affectationParRegion/{idProbleme}/{idRegion}" , method = RequestMethod.GET)
	public String affectationParRegion(@PathVariable("idProbleme") int idProbleme,@PathVariable("idRegion") int idRegion){
		ProblemeRegion problemeRegion = new ProblemeRegion();
		String valiny = problemeRegion.affecterSignalement(idProbleme,idRegion);
		return valiny;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/delete/deleteUtilisateur/{idUtilisateur}/{token}" , method = RequestMethod.GET)
	public String inscrire(@PathVariable("idUtilisateur") int idUtilisateur,@PathVariable("token") String token){
		Utilisateur utilisateur = new Utilisateur();
		int valiny = 0;
		String retour = "";
		if(Utilisateur.testToken(token, idUtilisateur)==true) {
			try {
				valiny = utilisateur.deleteUti(idUtilisateur);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}

	// http://localhost:9000/signalementInsert/insertSignalement/1/1002/1002/Tsy/1/sary.jpg
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/signalementInsert/insertSignalement/{idUtilisateur}/{coordonneX}/{coordonneY}/{descriptionProbleme}/{idProbleme}/{photo}/{token}" , method = RequestMethod.GET)
	public String inscrire(@PathVariable("idUtilisateur") int idUtilisateur,@PathVariable("coordonneX") double coordonneX,@PathVariable("coordonneY") double coordonneY,@PathVariable("descriptionProbleme") String descriptionProbleme,@PathVariable("idProbleme") int idProbleme,@PathVariable("photo") String photo,@PathVariable("token") String token){
		Signalement signalement = new Signalement();
		int valiny = 0;
		String retour = "";
		if(Utilisateur.testToken(token, idUtilisateur)==true) {
			try {
				valiny = signalement.insertSignalement(idUtilisateur,coordonneX,coordonneY,descriptionProbleme,idProbleme,photo);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		if(valiny == 1){
			retour = "SUCCESS";
		}
		else {
			retour = "ERROR";
		}
		return retour;
	}

	// http://localhost:9000/vasy/mySignalement/2/sign1Admin
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/vasy/mySignalement/{idUtilisateur}/{token}" , method = RequestMethod.GET)
	public List<Signalement> mySignalement(@PathVariable("idUtilisateur") int idUtilisateur,@PathVariable("token") String token){
		Signalement signalement = new Signalement();
		List<Signalement> list = new ArrayList();
		if(Utilisateur.testToken(token, idUtilisateur)==true) {
			list = signalement.signalementByIdUtilisateur(idUtilisateur);
		}
		return list;
	}

	// http://localhost:9000/InscriUtilisateur/mandaaroniaina2001@gmail/Aroniaina/1
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/InscriUtilisateur/{login}/{password}/{idRegion}" , method = RequestMethod.GET)
	public String inscrire(@PathVariable("login") String login, @PathVariable("password") String password,@PathVariable("idRegion") int idRegion){
		Utilisateur utilisateur = new Utilisateur();
		String valiny = "";
		try {
			valiny = utilisateur.inscrirN(login, password, idRegion);
		} catch (Exception e) {
			valiny = "Erreur le insertion";
			e.getMessage();
		}
		return valiny;
	}
	
	// http://localhost:9000/seConnecertUtilisateur/Manda/manda
	@CrossOrigin(origins = "*")
	@GetMapping("/connectUtilisateur/{login}/{password}")
    public int seConnecter(@PathVariable("login")String login,@PathVariable("password")String password)
    {
		Utilisateur utilisateur=new Utilisateur();
		int reponse=utilisateur.seConnecter(login, password);
        return reponse;
    }
}

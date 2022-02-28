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
	int idR=0;
	int idChef=0;
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/affectationParRegion/{idProbleme}/{idR}" , method = RequestMethod.GET )
	public String affectation(@PathVariable("idProbleme") String idProbleme,@PathVariable("idR") String idR){
		ProblemeRegion problemeRegion = new ProblemeRegion();
		System.out.println("okkkkkkkkkk");
		String valiny = problemeRegion.affecterSignalement(Integer.parseInt(idProbleme.trim()),Integer.parseInt(idR.trim()));
		return valiny;
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
		int p=0;
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
	@RequestMapping(value = "/insertRegion", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void insertRegion(@RequestPart("designationRegion")String designationRegion, @RequestPart("coordonneX")String coordonneX, @RequestPart("coordonneY")String coordonneY, @RequestPart("coordonneX1")String coordonneX1, @RequestPart("coordonneY1")String coordonneY1){
		Region region = new Region();
		double cx = Double.parseDouble(coordonneX);
		double cy = Double.parseDouble(coordonneY);
		double cx1 = Double.parseDouble(coordonneX1);
		double cy1 = Double.parseDouble(coordonneY1); 
		region.insertRegion(designationRegion, cx, cy, cx1, cy1);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listeliste", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<ProblemeRegion> listeliste(@RequestPart("id")int id){
		ProblemeRegion problemeRegion = new ProblemeRegion();
        List<ProblemeRegion> liste = problemeRegion.getPourcentageParRegion(id);
		return liste;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/statByStatut", method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Signalement> statByStatut(@RequestPart("id")int id){
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
		String temp="";
		System.out.println(motAChercher);
		if(motAChercher.equalsIgnoreCase("")==false) {
			if(motAChercher.split("=")[0].equalsIgnoreCase("")==false) {
				blem=motAChercher.split("=")[0];
				System.out.println(blem);
			}
			//http://localhost:9000/listeRecherchePro/Tapaka jiro=?!
			if(motAChercher.split("=").length>1) {
				if(motAChercher.split("=")[1].equalsIgnoreCase("")==false) {
					temp=motAChercher.split("=")[1];
					if(region.split("!")[0].trim().equalsIgnoreCase("")==false) {
						region=temp.split("!")[0];
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
		System.out.println(blem+"-"+region+"-"+statut);
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
	@RequestMapping(value = "/listeRechercheProblemeParDate/{d1}/{d2}", method = RequestMethod.GET)
	public List<Probleme> listeRechercheProblemeParDate(@PathVariable("d1")String date1,@PathVariable("d2")String date2){
		Probleme region = new Probleme();
		List<Probleme> liste=null;
		try {
			liste = region.searchSignaleByDate(date1,date2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@RequestMapping(value = "/updateSignalement/{idSignalement}/{idStatut}", method = RequestMethod.GET)
	public boolean updateSignalement(@PathVariable("idSignalement") int idSignalement, @PathVariable("idStatut")int idStatut){
		boolean valiny=true;
		try {
			Signalement signalement = new Signalement();
			signalement.updateSignalement(idSignalement, idStatut);
			String login=Signalement.login(idSignalement);
			String etat="";
			if(idStatut==3) {
				etat="envoyée au chef region responssable en ce moment";
			}
			if(idStatut==1) {
				etat="en cours de traitement ";
			}
			if(idStatut==2) {
				etat="à présent résolue";
			}
			System.out.println("mail"+login);
			Mailling.send("ampitaomada@gmail.com","MaNaMi2022", login,etat);
		}catch(Exception e) {
			valiny=false;
		}
		return valiny;
		
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
	@RequestMapping(value = "/valideConnex" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public int valideConnex(@RequestPart("loginAdmin") String loginAdmin, @RequestPart("mdpAdmin") String mdpAdmin){
		Admin admin = new Admin();
		int retour = admin.validConnex(loginAdmin, mdpAdmin);
		return retour;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/statByStatut/mande" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Signalement> statByStatut(@RequestPart("designationRegion") String designationRegion){
		Signalement stat = new Signalement();
		List<Signalement> list = stat.pourcentageByStat(designationRegion);
		System.out.println(list.size());
		return list;
	}

	// http://localhost:9000/seConnecertUtilisateur/Manda/manda
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/connexionUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String connexion(@RequestPart("login") String login, @RequestPart("password") String password){
		Utilisateur utilisateur = new Utilisateur();
		String valiny = "";
		String retour = "";
		try {
			valiny = utilisateur.seConnecter(login, password);
		} catch (Exception e) {
			e.getMessage();
		}
		if(valiny != ""){
			retour = "ERROR";
		}
		return retour;
	}


	// http://localhost:9000/updateUtilisateur/2/1
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String update(@RequestPart("idRegion") int idRegion, @RequestPart("idUtilisateur") int idUtilisateur){
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
	@RequestMapping(value = "/deleteUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String deleteUtilisateur(@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("token") String token){
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
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/proche/{x}/{y}" )
	public List<Region> proche(@PathVariable("x") String x,@PathVariable("y") String y){
		List<Region> valiny = Region.proche(-Double.parseDouble(x.substring(1).trim())  ,Double.parseDouble(y.trim()));
		return valiny;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/delete/deleteUtilisateur" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String delete(@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("token") String token){
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

	// http://localhost:9000/vasy/mySignalement/2/sign1Admin
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/vasy/mySignalement" , method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public List<Signalement> mySignalement(@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("token") String token){
		Signalement signalement = new Signalement();
		List<Signalement> list = new ArrayList();
		if(Utilisateur.testToken(token, idUtilisateur)==true) {
			list = signalement.signalementByIdUtilisateur(idUtilisateur);
		}
		return list;
	}
	
	// http://localhost:9000/regionChef/chefregion
	 @CrossOrigin(origins = "*")
	 @RequestMapping("/regionChef/chefregion")
	 public List<ChefRegion> listeChefRegion(){
		 ChefRegion regionChef = new ChefRegion();
		 List<ChefRegion> liste = regionChef.listeChef();
		 return liste;
	 }
	 
	// http://localhost:9000/regionChef/chefregion
		 @CrossOrigin(origins = "*")
		 @RequestMapping(value ="/regionChef/infoChefRegion/{id}",method = RequestMethod.GET,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
		 public ChefRegion infoChefRegion(@RequestPart("id")int id){
			 ChefRegion regionChef = new ChefRegion();
			 ChefRegion liste = regionChef.infoChefRegionById(id);
			 return liste;
		 }
		 
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/insertSignalement/signaler" , method = RequestMethod.POST ,consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public String insertSignalement(@RequestPart("file") MultipartFile file,@RequestPart("idUtilisateur") int idUtilisateur,@RequestPart("coordonneX") double coordonneX,@RequestPart("coordonneY") double coordonneY,@RequestPart("descriptionProbleme") String descriptionProbleme,@RequestPart("idProbleme") int idProbleme, @RequestPart("token") String token)throws IOException{
		Signalement signalement = new Signalement();
		int valiny = 0;
		String retour = "";
		try {
			if(Utilisateur.testToken(token, idUtilisateur)==true) {
				valiny = signalement.insertSignalement(idUtilisateur,coordonneX,coordonneY,descriptionProbleme,idProbleme,file.getOriginalFilename());
				String FILE_DIRECTORY="./";
				File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
				myFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(myFile);
				fos.write(file.getBytes());
				fos.close();
			}
			
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getSignalementByRegion" , method = RequestMethod.GET)
	public List<Signalement> getSignalementParRegion()
	{
		Signalement signalement = new Signalement();
		List<Signalement> liste = signalement.signalementParRegion(""+idR);
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getSignalementRegion" , method = RequestMethod.GET)
	public List<Signalement> getSignalementRegion()
	{
		Signalement signalement = new Signalement();
		List<Signalement> liste = signalement.signalementRegion(""+idR);
		System.out.println(liste.size());
		return liste;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/valideConnexChef/{loginChef}/{mdpChef}" , method = RequestMethod.GET)
	public Region valideConnexChef(@PathVariable("loginChef") String loginChef, @PathVariable("mdpChef") String mdpChef){
		Region retour=null;
		try {
			retour = ChefRegion.validConnex(loginChef, mdpChef);
			ChefRegion chef = ChefRegion.chefbylogin(loginChef);
			idR=retour.getIdRegion();
			idChef=chef.getIdChefRegion();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return retour;
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/infoChef" , method = RequestMethod.GET)
	public ChefRegion infoChef(){
		ChefRegion chef=null;
		if(idChef!=0) {
			try {
				chef = ChefRegion.infoChefRegionById(idChef);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return chef;
	}
}
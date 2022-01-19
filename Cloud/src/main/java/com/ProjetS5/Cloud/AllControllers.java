package com.ProjetS5.Cloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import classmapping.Admin;
import classmapping.Probleme;
import classmapping.ProblemeRegion;
import classmapping.Region;
import classmapping.Signalement;
import classmapping.Statut;
import classmapping.Utilisateur;

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
		Signalement region = new Signalement();
		List<Signalement> liste = region.allSignalementEtat();
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
				if(motAChercher.split("-")[0].equalsIgnoreCase("")==false) {
					blem=motAChercher.split("-")[0];
				}
			}
			if(motAChercher.split("-").length>1) {
				if(motAChercher.split("-")[1].equalsIgnoreCase("")==false) {
					region=motAChercher.split("-")[1];
					if(region.split("!")[0].equalsIgnoreCase("")==false) {
						region=region.split("!")[0];
					}
				}
			}
			if(motAChercher.split("!").length>1) {
				if(motAChercher.split("!")[1].equalsIgnoreCase("")==false) {
					statut=motAChercher.split("!")[1];
				}
			}
		}
		Probleme probleme = new Probleme();
		List<Probleme> liste = probleme.recherchePro(blem,region,statut);
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
}

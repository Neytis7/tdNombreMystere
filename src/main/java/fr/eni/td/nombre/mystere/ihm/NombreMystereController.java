package fr.eni.td.nombre.mystere.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import fr.eni.td.nombre.mystere.bll.GameService;
import fr.eni.td.nombre.mystere.bo.Joueur;

@Controller
@SessionAttributes("joueur")
public class NombreMystereController {

	private GameService gameService;
	private int nbMystere;

	@Autowired
	public NombreMystereController(GameService gameService) {
		super();
		this.gameService = gameService;
	}

	@GetMapping({ "", "/nbMystere" })
	public String plateau() {
		return "plateau";
	}

	@PostMapping({ "", "/nbMystere" })
	public String jouer(@RequestParam int number, Model modele) {
		Joueur joueur = (Joueur) modele.getAttribute("joueur");
		if(joueur == null) {
			this.nbMystere = this.gameService.tirageNombreMystere();
			System.out.println("nombre mystere  = " + this.nbMystere);
			joueur = new Joueur(number,1);
			if(joueur.getEssaie()== this.nbMystere) {
				String result = "Vous avez gagnez !!";
				modele.addAttribute("result", result);
			}else if(joueur.getEssaie() > this.nbMystere) {
				String result = "C'est -";
				modele.addAttribute("result", result);
			}else if(joueur.getEssaie() < this.nbMystere) {
				String result = "C'est +";
				modele.addAttribute("result", result);
			}
			modele.addAttribute("joueur",joueur);
		}else {
			joueur.setEssaie(number);
			joueur.setTentative(joueur.getTentative()+1);
			if(joueur.getTentative()<10) {
				if(joueur.getEssaie()== this.nbMystere) {
					String result = "Vous avez gagnez !!";
					modele.addAttribute("result", result);
				}else if (joueur.getEssaie() > this.nbMystere) {
					String result = "C'est -";
					modele.addAttribute("result", result);
				} else if (joueur.getEssaie() < this.nbMystere) {
					String result = "C'est +";
					modele.addAttribute("result", result);
				}
			}else {
				String result = "Vous avez perdu !!";
				modele.addAttribute("result", result);
			}
		}
		return "plateau";
	}
	
	@RequestMapping("/supprimerSession")
	public String supprimerSession(SessionStatus session) {
		System.out.println("kill");
		session.setComplete();
		return "redirect:/";
	}

}

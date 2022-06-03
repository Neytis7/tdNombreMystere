package fr.eni.td.nombre.mystere.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter  
@ToString(of= {"essaie","tentative"})
public class Joueur {
	
	private int essaie;
	private int tentative;
}

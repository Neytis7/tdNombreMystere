package fr.eni.td.nombre.mystere.bll;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service("GameService")
public class GameServiceImpl implements GameService {
	
	@Override
	public int tirageNombreMystere() {
		Random random = new Random();
		int value = (random.nextInt(50 + 1)+1);
		return value;
	}
}

package GAME.Spaces;

import GAME.Dice;
import GAME.Player;

public class GoPrison extends Space {
	GoPrison() {
		super("Vá para a Prisão");
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

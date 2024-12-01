package GAME.Spaces;

import GAME.Bank;
import GAME.Game;
import GAME.Player;

public class Luck extends Space {
	public Luck() {
		super("Sorte/Revés");
	}

	@Override
	void onPlayerStop(Player player) {
		int value = Game.random.nextInt(-80, 151);
		Bank.increasePlayerBalance(player, value);
	}

	@Override
	void onPlayerGoesOver(Player player) {

	}
}

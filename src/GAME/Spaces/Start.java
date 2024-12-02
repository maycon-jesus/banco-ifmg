package GAME.Spaces;

import GAME.Bank;
import GAME.Dice;
import GAME.Player;

public class Start extends Space {
	public Start() {
		super("Partida | Receba R$200");
	}

	@Override
	void onPlayerStop(Player player, Dice dice) {

	}

	@Override
	void onPlayerGoesOver(Player player, Dice dice) {
		Bank.increasePlayerBalance(player, 200);
	}
}

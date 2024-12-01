package GAME.Spaces;

import GAME.Bank;
import GAME.Player;

public class Start extends Space {
	public Start() {
		super("Partida | Receba R$200");
	}

	@Override
	void onPlayerStop(Player player) {

	}

	@Override
	void onPlayerGoesOver(Player player) {
		Bank.increasePlayerBalance(player, 200);
	}
}

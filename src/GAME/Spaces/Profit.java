package GAME.Spaces;

import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class Profit extends Space {
	public Profit() {
		super("Lucros ou Dividendos");
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		super.onPlayerStop(player, dice);
		player.increaseBalance(200);
		Messages.addMessage("Você ganhou R$200 de lucros ou dividendos!");
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

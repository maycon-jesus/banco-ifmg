package GAME.Spaces;

import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class IncomeTax extends Space {

	IncomeTax(Player player) {
		super("Imposto de Renda");
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		player.decreaseBalance(200);
		Messages.addMessage("Você teve que R$200 de imposto para o leão!");
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

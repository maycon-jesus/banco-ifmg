package GAME.Spaces;

import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class IncomeTax extends Space {

	public IncomeTax() {
		super("Imposto de Renda");
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		super.onPlayerStop(player, dice);
		player.decreaseBalance(200);
		Messages.addMessage("Você pagou R$200 de imposto para o leão!");
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

package GAME.Spaces;

import GAME.Bank;
import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class Start extends Space {
	public Start() {
		super("Partida | Receba R$200");
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		Messages.addMessage("Você passou pela partida e recebeu R$200!");
		Bank.increasePlayerBalance(player, 200);
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {
		Messages.addMessage("Você passou pela partida e recebeu R$200!");
		Bank.increasePlayerBalance(player, 200);
	}
}

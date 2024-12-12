package GAME.Spaces;

import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class ServiceCompany extends Space {
	private int buyValue = 0;
	private int rentValue = 0;
	private Player owner = null;

	public ServiceCompany(String name, int buyValue, int rentValue) {
		super(name);
		this.buyValue = buyValue;
		this.rentValue = rentValue;
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		if (this.owner != null && this.owner == player) {
			int rentTotal = this.rentValue * dice.getDicesSum();
			player.decreaseBalance(rentTotal);
			Messages.addMessage("Você pagou R$" + rentTotal + " de aluguel para " + this.owner.getEmojiName() + "!");
		}
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

package GAME.Spaces;

import GAME.Bank;
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
		super.onPlayerStop(player, dice);
		if (this.owner != null && this.owner == player) {
			int rentTotal = this.rentValue * dice.getDicesSum();
			player.decreaseBalance(rentTotal);
			Messages.addMessage("Você pagou R$" + rentTotal + " de aluguel para " + this.owner.getEmojiName() + "!");
		}
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}

	public int getBuyValue() {
		return this.buyValue;
	}

	public boolean isPurchasable() {
		return this.owner == null;
	}

	public boolean buyProperty(Player owner) {
		if (!isPurchasable()) throw new Error("Esta propriedade ja possui um dono");
		if (owner.getBalance() < this.getBuyValue()) throw new Error("Você não possui saldo suficiente!");

		this.owner = owner;
		Bank.decreasePlayerBalance(owner, this.getBuyValue());
		return true;
	}
}

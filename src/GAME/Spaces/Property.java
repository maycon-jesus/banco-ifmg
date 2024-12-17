package GAME.Spaces;

import GAME.Bank;
import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class Property extends Space {
	private int upgrades = 0;
	private Player owner = null;
	private final int[] rentValues;
	private final int buyPrice;

	public Property(String name,int buyPrice, int[] rentValues) {
		super(name);
		this.purchasable = true;
		this.buyPrice = buyPrice;
		this.rentValues = rentValues;
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		super.onPlayerStop(player, dice);
		if (this.owner != null && player != this.owner) {
			Bank.decreasePlayerBalance(player, this.getRentValue());
			this.owner.increaseBalance(this.getRentValue());
			Messages.addMessage("Você pagou R$" + this.getRentValue() + " de aluguel para " + this.owner.getEmojiName() + "!");
		}
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}

	public int getBuyValue() {
		return this.buyPrice;
	}

	public boolean isPurchasable() {
		return this.owner == null;
	}

	public void buyProperty(Player owner) {
		if (!isPurchasable()) throw new Error("Esta propriedade ja possui um dono");
		if (owner.getBalance() < this.getBuyValue()) throw new Error("Você não possui saldo suficiente!");

		this.owner = owner;
		Bank.decreasePlayerBalance(owner, this.getBuyValue());
	}

	public int getNextUpgradePrice() {
		return this.getRentValue();
	}

	public boolean isUpgradable() {
		return this.upgrades < 2;
	}

	public boolean upgrade() {
		if (this.owner == null) throw new Error("Esta propriedade precisa possuir um dono para poder fazer upgrades!");
		if (this.upgrades == 2) throw new Error("Essa propriedade ja atingiu o limite de upgrades!");
		int upgradePrice = this.getNextUpgradePrice();
		if (owner.getBalance() < upgradePrice) throw new Error("Você não possui saldo suficiente!");
		this.upgrades++;
		Bank.decreasePlayerBalance(owner, upgradePrice);
		return true;
	}

	public int getRentValue() {
		return this.rentValues[this.upgrades];
	}

	public String getUpgradeName(){
		return switch (this.upgrades) {
			case 1 -> "Pousada";
			case 2 -> "Hotel";
			default -> "Casa";
		};
	}

	public int getUpgradeLevel(){
		return this.upgrades;
	}

	public Player getOwner() {
		return this.owner;
	}
}

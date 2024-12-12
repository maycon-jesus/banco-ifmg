package GAME.Spaces;

import GAME.Bank;
import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class Property extends Space {
	private String color;
	private int upgrades = 0;
	private Player owner = null;
	private int[] upgradePrices = {};
	private int rentValueBase = 0;

	public Property(String name, String color, int[] upgradePrices, int rentValueBase) {
		super(name);
		this.color = color;
		this.purchasable = true;
		this.upgradePrices = upgradePrices;
		this.rentValueBase = rentValueBase;
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
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
		return this.upgradePrices[0];
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

	public int getNextUpgradePrice() {
		return upgradePrices[this.upgrades + 1];
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
		int sum = this.rentValueBase;
		for (int i = 1; i < this.upgrades; i++) {
			sum += this.upgradePrices[i];
		}
		return sum;
	}

	public Player getOwner() {
		return this.owner;
	}
}

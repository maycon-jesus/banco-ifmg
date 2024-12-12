package GAME.Spaces;

import GAME.*;

public class Luck extends Space {
	public Luck() {
		super("Sorte/Revés");
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		boolean winMoney = Game.random.nextBoolean();

		if (winMoney) {
			int value = Game.random.nextInt(1, 151);
			Bank.increasePlayerBalance(player, value);
			Messages.addMessage("Você parou no " + this.getName() + " e ganhou " + value);
		} else {
			int value = Game.random.nextInt(1, 81);
			Bank.decreasePlayerBalance(player, value);
			Messages.addMessage("Você parou no " + this.getName() + " e perdeu " + value);
		}

	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

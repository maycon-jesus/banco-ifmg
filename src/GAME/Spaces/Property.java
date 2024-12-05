package GAME.Spaces;

import GAME.Dice;
import GAME.Player;

public class Property extends Space {
	private String color;

	public Property(String name, String color) {
		super(name);
		this.color = color;
		this.purchasable = true;
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {

	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

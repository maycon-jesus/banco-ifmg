package GAME.Spaces;

import GAME.Player;

public class Property extends Space {
	private String color;

	public Property(String name, String color) {
		super(name);
		this.color = color;
		this.purchasable = true;
	}

	@Override
	void onPlayerStop(Player player) {

	}

	@Override
	void onPlayerGoesOver(Player player) {

	}
}

package GAME.Spaces;

import GAME.Player;

public abstract class Space {
	protected boolean purchasable = false;
	private String name;

	Space(String name) {
		this.name = name;
	}

	abstract void onPlayerStop(Player player);

	abstract void onPlayerGoesOver(Player player);
}

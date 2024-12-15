package GAME.Spaces;

import GAME.Dice;
import GAME.Player;

public class BlankSpace extends Space {
	public BlankSpace(String name) {
		super(name);
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		super.onPlayerStop(player, dice);
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

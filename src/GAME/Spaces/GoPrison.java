package GAME.Spaces;

import GAME.Dice;
import GAME.Messages;
import GAME.Player;

public class GoPrison extends Space {
	private Space prisonSpace = null;

	public GoPrison() {
		super("Vá para a Prisão");
	}

	public GoPrison setPrisonSpace(Space prisonSpace) {
		this.prisonSpace = prisonSpace;
		return this;
	}

	@Override
	public void onPlayerStop(Player player, Dice dice) {
		super.onPlayerStop(player, dice);
		this.prisonSpace.addPlayer(player);
		this.removePlayer(player);
		Messages.addMessage("Você foi para a prisão!");
	}

	@Override
	public void onPlayerGoesOver(Player player, Dice dice) {

	}
}

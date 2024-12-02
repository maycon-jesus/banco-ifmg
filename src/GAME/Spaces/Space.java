package GAME.Spaces;

import GAME.Dice;
import GAME.Player;

import java.util.ArrayList;

public abstract class Space {
	protected boolean purchasable = false;
	private ArrayList<Player> players = new ArrayList<Player>();
	private String name;

	Space(String name) {
		this.name = name;
	}

	abstract void onPlayerStop(Player player, Dice dice);

	abstract void onPlayerGoesOver(Player player, Dice dice);

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void removePlayer(Player player) {
		players.remove(player);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean playerIsHere(Player player) {
		return players.contains(player);
	}

	public String getName() {
		return name;
	}
}

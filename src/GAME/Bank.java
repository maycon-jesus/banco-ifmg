package GAME;

public class Bank {
	public static void decreasePlayerBalance(Player player, int value) {
		int newBalance = player.decreaseBalance(value);
	}
	public static void increasePlayerBalance(Player player, int value) {
		int newBalance = player.increaseBalance(value);
	}
}

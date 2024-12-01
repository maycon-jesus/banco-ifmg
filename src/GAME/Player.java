package GAME;

public class Player {
	private String name;
	private String emoji;
	private int balance = 1500;

	Player(String name, String emoji) {
		this.name = name;
		this.emoji = emoji;
	}

	public String getName() {
		return name;
	}

	public String getEmoji() {
		return emoji;
	}

	public int getBalance() {
		return balance;
	}

	public int decreaseBalance(int value){
		balance -= value;
		return balance;
	}

	public int increaseBalance(int value){
		balance += value;
		return balance;
	}
}

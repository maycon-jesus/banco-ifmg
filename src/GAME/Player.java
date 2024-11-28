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
}
package GAME;

public class Dice {
	private final int number1;
	private final int number2;

	public Dice() {
		number1 = Game.random.nextInt(1, 6);
		number2 = Game.random.nextInt(1, 6);
	}

	public int getDice1() {
		return number1;
	}

	public int getDice2() {
		return number2;
	}

	public int getDicesSum() {
		return number1 + number2;
	}
}

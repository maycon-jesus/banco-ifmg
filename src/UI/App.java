package UI;

import GAME.Game;
import GAME.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	static Scanner scanner = new Scanner(System.in);

	public static void startMenu() {
		System.out.println("""

				$$$$$$$\\                                                $$$$$$\\ $$$$$$$$\\ $$\\      $$\\  $$$$$$\\ \s
				$$  __$$\\                                               \\_$$  _|$$  _____|$$$\\    $$$ |$$  __$$\\\s
				$$ |  $$ | $$$$$$\\  $$$$$$$\\   $$$$$$$\\  $$$$$$\\          $$ |  $$ |      $$$$\\  $$$$ |$$ /  \\__|
				$$$$$$$\\ | \\____$$\\ $$  __$$\\ $$  _____|$$  __$$\\         $$ |  $$$$$\\    $$\\$$\\$$ $$ |$$ |$$$$\\\s
				$$  __$$\\  $$$$$$$ |$$ |  $$ |$$ /      $$ /  $$ |        $$ |  $$  __|   $$ \\$$$  $$ |$$ |\\_$$ |
				$$ |  $$ |$$  __$$ |$$ |  $$ |$$ |      $$ |  $$ |        $$ |  $$ |      $$ |\\$  /$$ |$$ |  $$ |
				$$$$$$$  |\\$$$$$$$ |$$ |  $$ |\\$$$$$$$\\ \\$$$$$$  |      $$$$$$\\ $$ |      $$ | \\_/ $$ |\\$$$$$$  |
				\\_______/  \\_______|\\__|  \\__| \\_______| \\______/       \\______|\\__|      \\__|     \\__| \\______/\s
				                                                                                                \s
				                                                                                                \s
				                                                                                                \s
				""");
		menu();
	}

	public static void menu() {
		ArrayList<Player> players = Game.getPlayers();
		if (!players.isEmpty()) {
			System.out.println("====[JOGADORES CADASTRADOS]====");
			for (Player player : players) {
				System.out.println("- " + player.getName());
			}
		}

		System.out.println("====[MENU]====");
		System.out.println("1 - Adicionar jogador");
		System.out.println("2 - Iniciar jogo");
		System.out.println("0 - Sair");
		System.out.print("Selecione uma opção: ");

		int choice = scanner.nextInt();
		switch (choice) {
			case 1: {
				promptNewUser();
				menu();
			}
			default: {
				System.out.println("Opção inválida!!!");
				menu();
			}
		}
	}

	public static void promptNewUser() {
		System.out.print("Nome do jogador: ");
		String playerName = scanner.next();
		try {
			Game.newPlayer(playerName);
		} catch (Error err) {
			System.out.println(err.getMessage());
		}
	}
}

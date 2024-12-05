package UI;

import GAME.Game;
import GAME.Player;
import GAME.Spaces.Space;

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
				break;
			}
			case 2: {
				Game.initGame();
				break;
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

	private static String makeBoardSpaceTitle(Space[] board, int currentIndex) {
		System.out.println(String.valueOf(currentIndex+1) + " - " + board[currentIndex].getName());
		Space space = board[currentIndex];

		if (space.getPlayers().isEmpty()) return String.valueOf(currentIndex+1);

		StringBuilder title = new StringBuilder(String.valueOf(currentIndex+1) + " - ");
		for (Player p : space.getPlayers()) {
			title.append(p.getEmoji());
		}
		return title.toString();
	}

	public static void printBoard() {
		Space[] board = Game.getBoard();
		ArrayList<ArrayList<String>> boardArr = new ArrayList<ArrayList<String>>();

		System.out.println("Legenda:");
		System.out.println("- Jogadores:");
		for (Player player : Game.getPlayers()) {
			System.out.println(player.getEmoji() + " " + player.getName());
		}

		System.out.println("- Casas:");
		for (int lineIndex = 0; lineIndex < 11; lineIndex++) {
			ArrayList<String> line = new ArrayList<>();
			for (int columnsIndex = 0; columnsIndex < 11; columnsIndex++) {
				line.add("");
			}
			boardArr.add(line);
		}

		int currentIndex = 0;

//		Ultima linha
		for (int columnsIndex = 10; columnsIndex >= 0; columnsIndex--) {
			boardArr.get(10).set(columnsIndex, makeBoardSpaceTitle(board, currentIndex));
			currentIndex++;
		}

//		Coluna esquerda
		for (int columnsIndex = 9; columnsIndex >= 1; columnsIndex--) {
			boardArr.get(columnsIndex).set(0, makeBoardSpaceTitle(board, currentIndex));
			currentIndex++;
		}

// 		Primeira linha
		for (int columnsIndex = 0; columnsIndex <= 10; columnsIndex++) {
			boardArr.get(0).set(columnsIndex, makeBoardSpaceTitle(board, currentIndex));
			currentIndex++;
		}


//		Coluna direita
		for (int columnsIndex = 1; columnsIndex <= 9; columnsIndex++) {
			boardArr.get(columnsIndex).set(10, makeBoardSpaceTitle(board, currentIndex));
			currentIndex++;
		}

		new BeautyTable().printArrayList(boardArr);
	}
}

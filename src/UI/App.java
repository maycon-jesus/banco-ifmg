package UI;

import GAME.Dice;
import GAME.Game;
import GAME.Messages;
import GAME.Player;
import GAME.Spaces.Property;
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

	public static void waitPlayerAction(String message) {
		System.out.print(message);
		scanner.nextLine();
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
				App.printBoard();
				while (Game.getLivePlayers().size() > 1) {
					ArrayList<Player> livePlayers = Game.getLivePlayers();
					for (Player player : livePlayers) {
						waitPlayerAction("Vez do jogador " + player.getEmojiName() + "! Pressione enter para jogar o dado.");
						Dice dice = new Dice();
						Space spaceStopped = Game.walkPlayer(player, dice);
						App.printBoard();
						Messages.showAllMessagesStacked();
						if(spaceStopped instanceof Property){

							if(((Property) spaceStopped).isPurchasable()){
								boolean buy = promptBooleanResponse("Deseja comprar essa propriedade?");
								if(!buy) continue;
								try{
									((Property) spaceStopped).buyProperty(player);
									System.out.println("Propriedade comprada com sucesso!");
								}catch(Error err){
									System.out.println(err.getMessage());
								}
							}
						}
						if (player.getBalance() < 0) {
							System.out.println("O jogador " + player.getEmojiName() + " foi eliminado por estar com saldo negativo!");
						}
					}
				}
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

	public static boolean promptBooleanResponse(String message) {
		System.out.print(message+" (s/n)");
		String resp = App.scanner.next();
		if(resp.equals("s")){
			return true;
		}else if(resp.equals("n")){
			return false;
		}
		return promptBooleanResponse(message);
	}

	private static String makeBoardSpaceTitle(Space[] board, int currentIndex) {
		System.out.println(String.valueOf(currentIndex + 1) + " - " + board[currentIndex].getName());
		Space space = board[currentIndex];


		StringBuilder title = new StringBuilder(String.valueOf(currentIndex + 1));

		if(space instanceof Property){
			if(((Property) space).getOwner() != null){
				String ownerEmoji = ((Property) space).getOwner().getEmoji();
				title.append(" [DONO: ").append(ownerEmoji).append("]");
			}
		}

		if(!space.getPlayers().isEmpty()) title.append(" - ")

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
			System.out.println(player.getEmoji() + " " + player.getName() + ": R$" + player.getBalance());
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

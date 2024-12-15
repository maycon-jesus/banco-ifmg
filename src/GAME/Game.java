package GAME;


import GAME.Spaces.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	public static Random random = new Random();
	public static int boardSize = 40;
	private static Space[] board = new Space[40];
	private static ArrayList<Player> players = new ArrayList<>();


	public static void makeBoard() {
		board[0] = new Start();
		board[1] = new Property("Leblon", "purple", new int[]{80, 24, 240}, 8);
		board[2] = new Luck();
		board[3] = new Property("Avenida Presidente Vargas", "purple", new int[]{80, 18, 180}, 6);
		board[4] = new Property("Av. Nossa Sonhora de Copacabana", "purple", new int[]{80, 21, 210}, 7);
		board[5] = new ServiceCompany("Companhia Ferroviária", 200, 50);


		board[6] = new Property("Avenida Brigadeiro Faria Lima", "cyan", new int[]{80, 21, 210}, 7);
		board[7] = new ServiceCompany("Companhia de Viação", 200, 50);
		board[8] = new Property("Avenida Rebouças", "cyan", new int[]{80, 21, 210}, 7);
		board[9] = new Property("Avenida 9 de Julho", "cyan", new int[]{80, 21, 210}, 7);
		board[10] = new BlankSpace("Cadeia");
		board[11] = new Property("Avenida Europa", "pink", new int[]{80, 21, 210}, 7);
		board[12] = new Luck();
		board[13] = new Property("Rua Augusta", "pink", new int[]{80, 21, 210}, 7);
		board[14] = new Property("Avenida Pacaembu", "pink", new int[]{80, 21, 210}, 7);
		board[15] = new ServiceCompany("Companhia de Táxi", 200, 50);
		board[16] = new Luck();
		board[17] = new Property("Interlagos", "orange", new int[]{80, 21, 210}, 7);
		board[18] = new Profit();
		board[19] = new Property("Morumbi", "orange", new int[]{80, 21, 210}, 7);
		board[20] = new BlankSpace("Parada Livre");
		board[21] = new Property("Bangu", "red", new int[]{80, 21, 210}, 7);
		board[22] = new Luck();
		board[23] = new Property("Botafogo", "red", new int[]{80, 21, 210}, 7);
		board[24] = new IncomeTax();
		board[25] = new ServiceCompany("Companhia de Navegação", 200, 50);
		board[26] = new Property("Av. Brasil", "yellow", new int[]{80, 21, 210}, 7);
		board[27] = new Luck();
		board[28] = new Property("Avenida Paulista", "yellow", new int[]{80, 21, 210}, 7);
		board[29] = new Property("Jardim Europa", "purple", new int[]{80, 21, 210}, 7);
		board[30] = new GoPrison();
		board[31] = new Property("Copacabana", "green", new int[]{80, 21, 210}, 7);
		board[32] = new ServiceCompany("Companhia de Aviação", 200, 50);
		board[33] = new Property("Av. Vieira Souto", "purple", new int[]{80, 21, 210}, 7);
		board[34] = new Property("Av. Atlântica", "purple", new int[]{80, 21, 210}, 7);
		board[35] = new ServiceCompany("Companhia de Táxi Aéreo", 200, 50);
		board[36] = new Property("Ipanema", "purple", new int[]{80, 21, 210}, 7);
		board[37] = new Luck();
		board[38] = new Property("Jardim Paulista", "purple", new int[]{80, 21, 210}, 7);
		board[39] = new Property("Brooklin", "purple", new int[]{80, 21, 210}, 7);
		for (int i = 0; i < boardSize; i++) {
			int nextSpaceIndex = (i + 1) % (boardSize);
			board[i].setNextSpace(board[nextSpaceIndex]);
		}
	}

	static Space findSpaceByPlayer(Player player) {
//		for(int i = 0; i< boardSize;i++){
//			Space space = board[i];
//			if(space.playerIsHere(player)) return new PlayerPosition(space, i);
//		}
		for (Space space : board) {
			if (space.playerIsHere(player)) return space;
		}
		return null;
	}


	public static Space walkPlayer(Player player, Dice dice) {
		Space playerPositionFrom = findSpaceByPlayer(player);
		Space playerPosition = playerPositionFrom;
		assert playerPosition != null;

		for (int i = 0; i < dice.getDicesSum(); i++) {
			playerPosition = playerPosition.getNextSpace();

			if (i + 1 == dice.getDicesSum()) {
//				Quando o player para na casa
				playerPosition.addPlayer(player);
				playerPositionFrom.removePlayer(player);
				playerPosition.onPlayerStop(player, dice);
				return playerPosition;
			} else {
//				Quando o player passa por cima da casa mas não para
				playerPosition.onPlayerGoesOver(player, dice);
			}
		}

		throw new Error("ERROR IMPOSSIBLE 001");
	}

	public static void initGame() {
		makeBoard();
		for (Player p : players) {
			board[0].addPlayer(p);
		}
	}

	public static void newPlayer(String name) {
		if (players.size() == 6) throw new Error("Limite de 6 jogadores atingido!");
		String[] playerEmojis = {"🔵", "\uD83D\uDFE1", "\uD83D\uDD34", "\uD83D\uDFE2", "\uD83D\uDFE0", "\uD83D\uDFE3"};
		Player nPlayer = new Player(name, playerEmojis[players.size()]);
		players.add(nPlayer);
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static ArrayList<Player> getLivePlayers() {
		ArrayList<Player> playersLive = new ArrayList<>();
		for (Player p : players) {
			if (p.getBalance() > 0) playersLive.add(p);
		}
		return playersLive;
	}

	public static Space[] getBoard() {
		return board;
	}
}

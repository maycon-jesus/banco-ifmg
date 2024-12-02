package GAME;


import GAME.Spaces.*;
import UI.App;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	public static Random random = new Random();
	private static Space[] board = new Space[40];
	private static ArrayList<Player> players = new ArrayList<>();

	public static void makeBoard() {
		board[0] = new Start();
		board[1] = new Property("Leblon", "purple");
		board[2] = new Luck();
		board[3] = new Property("Avenida Presidente Vargas", "purple");
		board[4] = new Property("Av. Nossa Sonhora de Copacabana", "purple");
		board[5] = new ServiceCompany("Companhia Ferroviária");
		board[6] = new ServiceCompany("Companhia Ferroviária");
		board[7] = new ServiceCompany("Companhia Ferroviária");
		board[8] = new ServiceCompany("Companhia Ferroviária");
		board[9] = new ServiceCompany("Companhia Ferroviária");
		board[10] = new ServiceCompany("Companhia Ferroviária");
		board[11] = new ServiceCompany("Companhia Ferroviária");
		board[12] = new ServiceCompany("Companhia Ferroviária");
		board[13] = new ServiceCompany("Companhia Ferroviária");
		board[14] = new ServiceCompany("Companhia Ferroviária");
		board[15] = new ServiceCompany("Companhia Ferroviária");
		board[16] = new ServiceCompany("Companhia Ferroviária");
		board[17] = new ServiceCompany("Companhia Ferroviária");
		board[18] = new ServiceCompany("Companhia Ferroviária");
		board[19] = new ServiceCompany("Companhia Ferroviária");
		board[20] = new ServiceCompany("Companhia Ferroviária");
		board[21] = new ServiceCompany("Companhia Ferroviária");
		board[22] = new ServiceCompany("Companhia Ferroviária");
		board[23] = new ServiceCompany("Companhia Ferroviária");
		board[24] = new ServiceCompany("Companhia Ferroviária");
		board[25] = new ServiceCompany("Companhia Ferroviária");
		board[26] = new ServiceCompany("Companhia Ferroviária");
		board[27] = new ServiceCompany("Companhia Ferroviária");
		board[28] = new ServiceCompany("Companhia Ferroviária");
		board[29] = new ServiceCompany("Companhia Ferroviária");
		board[30] = new ServiceCompany("Companhia Ferroviária");
		board[31] = new ServiceCompany("Companhia Ferroviária");
		board[32] = new ServiceCompany("Companhia Ferroviária");
		board[33] = new ServiceCompany("Companhia Ferroviária");
		board[34] = new ServiceCompany("Companhia Ferroviária");
		board[35] = new ServiceCompany("Companhia Ferroviária");
		board[36] = new ServiceCompany("Companhia Ferroviária");
		board[37] = new ServiceCompany("Companhia Ferroviária");
		board[38] = new ServiceCompany("Companhia Ferroviária");
		board[39] = new ServiceCompany("Companhia Ferroviária");
	}

	public static void initGame() {
		makeBoard();
		for (Player p : players) {
			board[0].addPlayer(p);
		}
		App.printBoard();
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

	public static Space[] getBoard() {
		return board;
	}
}

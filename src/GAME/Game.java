package GAME;


import GAME.Spaces.*;

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
}

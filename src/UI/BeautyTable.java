package UI;

import java.util.ArrayList;

// Classe responsável por montar tabelas
class BeautyTable {
	public void printArrayList(ArrayList<ArrayList<String>> arr) {
		if (arr.isEmpty()) return;
		int[] columnsWidth = new int[arr.getFirst().size()];

//        Laço responsável por mapear a largura das colunas
		for (int i = 0; i < arr.size(); i++) {
			ArrayList<String> line = arr.get(i);
			for (int j = 0; j < arr.get(i).size(); j++) {
				String cell = line.get(j);
				if (cell.length() > columnsWidth[j]) {
					columnsWidth[j] = cell.length();
				}
			}
		}

//        Laço que vai montar a tabela
		for (int i = 0; i < arr.size(); i++) {
			ArrayList<String> line = arr.get(i);

//        Laço que monta a borda superior da tabela
			for (int j = 0; i == 0 && j < arr.get(i).size(); j++) {
				int currentColumnWidth = columnsWidth[j];

				if (j == 0) {
					System.out.print("┌");
				}

				System.out.print("─".repeat(currentColumnWidth + 2));

				if (j == line.size() - 1) {
					System.out.print("┐");
				} else {
					System.out.print("┬");
				}
			}
			if (i == 0) {
				System.out.println();
			}

//            Laço que monta as linhas de dados
			for (int j = 0; j < arr.get(i).size(); j++) {
				String cell = line.get(j);
				int currentColumnWidth = columnsWidth[j];
				int whiteSpaces = currentColumnWidth - cell.length();
				if (j == 0) {
					System.out.print("│ ");
				}
				System.out.print(cell + " ".repeat(whiteSpaces) + " │ ");
			}
			System.out.println();

//            Laço que monta a borda inferior de cada celula ou da tabela
			for (int j = 0; j < arr.get(i).size(); j++) {
				int currentColumnWidth = columnsWidth[j];

				if (i == arr.size() - 1 && j == 0) {
//                    Borda inferior da tabela
					System.out.print("└");
				} else if (j == 0) {
//                    Borda inferior de celula
					System.out.print("├");
				}

				System.out.print("─".repeat(currentColumnWidth + 2));

//                    Borda inferior da tabela
				if (i == arr.size() - 1 && j == line.size() - 1) {
					System.out.print("┘");
					continue;
				} else if (i == arr.size() - 1) {
					System.out.print("┴");
					continue;
				}

				// Borda inferior das celulas
				if (j == line.size() - 1) {
					System.out.print("┤");
				} else {
					System.out.print("┼");
				}
			}

			System.out.println();
		}
	}
}
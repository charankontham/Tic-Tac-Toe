import java.util.Scanner;

public class TicTacToe {
	public static String game[][] = new String[3][3];

	public static boolean userTurnNotCompleted = true;
	public static String userSymbol = "";
	public static String computerSymbol = "";
	public static String winner = "";
	public static int count = 0;
	public static int computerScore = 0;
	public static int userScore = 0;
	public static boolean startNewGame = true;

	public static void main(String args[]) {
		Scanner scannerInput = new Scanner(System.in);
		do {
			if (startNewGame) {
				System.out.println("==== New game started ====");
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						game[i][j] = " ";
					}
				}
				firstPlay();
				startNewGame = false;
				userTurnNotCompleted = true;
				count = 0;
			}
			System.out.println("tictactoe> ");
			String[] personValue = scannerInput.nextLine().split(" ");
			if (personValue[0].equalsIgnoreCase("move") && personValue.length == 2 && personValue[1].length() == 3
					&& personValue[1].charAt(1) == ',' && Character.isDigit(personValue[1].charAt(0))
					&& Character.isDigit(personValue[1].charAt(2))
					&& ((int) personValue[1].charAt(0) >= 48 && (int) personValue[1].charAt(0) <= 50)
					&& ((int) personValue[1].charAt(2) >= 48 && (int) personValue[1].charAt(2) <= 50)) {
				int i = Integer.parseInt(personValue[1].substring(0, 1));
				int j = Integer.parseInt(personValue[1].substring(2));
				if (game[i][j] != " ") {
					System.out.println("Invalid move, place already taken");
				} else {
					moveCommand(i, j);
					displayGameBoard();
					winner = checkwin();
					userTurnNotCompleted = false;
					if (winner != null) {
						displayWinner(winner);
						userScore++;
						startNewGame = true;
						userTurnNotCompleted = true;
					}
					if (count == 9 && winner == null) {
						System.out.println("The Game is a Draw");
					}

				}

			} else if (personValue[0].equalsIgnoreCase("scoreboard")) {
				System.out.println("|Computer|User|");
				System.out.println("|" + computerScore + "       |" + userScore + "   |");
			} else if (personValue[0].equalsIgnoreCase("quit")) {
				System.out.println("Quit");
				System.out.println("Computer Wins");
				System.exit(0);
			} else {
				System.out.println("Invalid Input, Try again");
			}
			if (userTurnNotCompleted == false) {
				computerTurn();
				System.out.println("tictactoe> Computer turn");
				displayGameBoard();
				winner = checkwin();
				if (winner != null) {
					displayWinner(winner);
					computerScore++;
					startNewGame = true;
				}
				if (count == 9 && winner == null) {
					System.out.println("The Game is a Draw");
					startNewGame = true;
				}
				userTurnNotCompleted = true;
			}
		} while (true);
	}

	public static void firstPlay() {
		if (((int) (Math.random() * 2) + 0) == 1) {
			System.out.println("tictactoa> Computer starts the game ");
			userSymbol = "O";
			computerSymbol = "X";
			computerTurn();
			displayGameBoard();
		} else {
			userSymbol = "X";
			computerSymbol = "O";
			System.out.println("User starts the game");
			displayGameBoard();
		}
	}

	public static void moveCommand(int i, int j) {
		game[i][j] = userSymbol;
		count++;
	}

	public static void displayWinner(String winner) {
		System.out.println(winner + " Wins ");
	}

	public static void computerTurn() {
		boolean success = false;
		do {
			int computerValue = (int) (Math.random() * 8) + 0;
			if (game[computerValue / 3][computerValue % 3].equals(" ")) {
				game[computerValue / 3][computerValue % 3] = computerSymbol;
				success = true;
			}
		} while (success == false);
		count++;
	}

	public static String checkwin() {
		String rowcolumn = "";
		String leftDiagonal = "";
		String rightDiagonal = "";
		// checking columns
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				rowcolumn += game[j][i];
			}
			if (checkWinnerName(rowcolumn) == null) {
				rowcolumn = "";
			} else {
				return checkWinnerName(rowcolumn);
			}
		}
		// checking rows
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				rowcolumn += game[i][j];
			}
			if (checkWinnerName(rowcolumn) == null) {
				rowcolumn = "";
			} else {
				return checkWinnerName(rowcolumn);
			}
		}
		// checking diagonals
		leftDiagonal = game[0][0] + game[1][1] + game[2][2];
		if (checkWinnerName(leftDiagonal) == null) {
			rightDiagonal = game[0][2] + game[1][1] + game[2][0];
			return checkWinnerName(rightDiagonal);
		}
		return checkWinnerName(leftDiagonal);
	}

	public static String checkWinnerName(String name) {
		if (name.equals("XXX")) {
			if (computerSymbol.equals("X")) {
				return "Computer";
			} else {
				return "User";
			}
		} else if (name.equals("OOO")) {
			if (userSymbol.equals("O")) {
				return "User";
			} else {
				return "Computer";
			}
		}
		return null;
	}

	public static void displayGameBoard() {
		for (int i = 0; i < 3; i++) {
			System.out.print("|");
			for (int j = 0; j < 3; j++) {
				System.out.print(game[i][j] + "|");
			}
			System.out.println("");
		}
	}
}

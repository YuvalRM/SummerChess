import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Game {
	public static void main(String[] args) {
		GameState game=new GameState();
		Scanner inputScanner = new Scanner(System.in);
		String input;
		boolean isValid;
		boolean anymoves=true;
		boolean check=false;

		while(anymoves) {
			System.out.println(game);
			Optional<GameState> moving=Optional.empty();
			if(check) {
				System.out.println("Check!");
			}
			System.out.println(game.getTurn() +"'s turn");
			System.out.println("Enter a move to play:");
			input=inputScanner.nextLine();
			isValid=game.inputValid(input);
			if(isValid) {
			moving=game.moveValid(input);
			}
			while(!isValid || moving.isEmpty()) {
				if(!isValid) {
					System.out.println("You're input is invalid enter a valid input:");
					}
				else {
					System.out.println("You're move is invalid enter a valid move:");
				}
				input=inputScanner.nextLine();
				isValid=game.inputValid(input);
				if(isValid) {
					moving=game.moveValid(input);
				}
			}
			game=moving.get().copy();
			Optional<Pawn> ispromo=game.promo();
			
			if(!ispromo.isEmpty()) {
				Set<String> legalPromo=new HashSet<String>();
		    	Collections.addAll(legalPromo, "Q","N","B","R");
				System.out.println("Which piece to you want to promote to, Q - Queen, N - Knight, B - Bishop, R - Rook");
				input=inputScanner.nextLine();
				while(!legalPromo.contains(input)){
					System.out.println("Illegal input, Q - Queen, N - Knight, B - Bishop, R - Rook");
					input=inputScanner.nextLine();
				}
				ispromo.get().promotion(input, game);
			}
			game.changeTurn();
			anymoves=game.anyMove();
			check=game.isCheck();
			
		}
		if(!anymoves&&check) {
			System.out.println("Game Over " + game.getTurn().opposite() +"Won!");
		}
		else {
			System.out.println("there's a draw");
		}
		inputScanner.close();
	}

}

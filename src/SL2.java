import java.util.HashMap; //implements an associative array to compute index positions and maps keys to values
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
 
public class SL2{

	public static void main(String[] args) {
		SnakeNLadder s = new SnakeNLadder();
		s.startGame();

	}

}

class SnakeNLadder
{
	
	final static int FINISHLINE = 100;
	
	
	static Map<Integer,Integer> snake = new HashMap<Integer,Integer>();
	static Map<Integer,Integer> ladder = new HashMap<Integer,Integer>();
	
	{
		snake.put(99,54);
		snake.put(70,55); // since hashmaps map keys to values. the first coordinate(a key)
		snake.put(52,42); //  is the head of the snake which leads to the (value) tail of the snake
		snake.put(25,2);  
		snake.put(95,72);
		
		ladder.put(6,25);
		ladder.put(11,40); // similarly with ladders, stores the starting of a ladder as key and when
		ladder.put(60,85); // index value in array is reached, the players move up to the top of the
		ladder.put(46,90); // ladder(higher value)
		ladder.put(17,69);
	}
	
	
	
	public int rollDice()
	{
		int n = 0;
		Random r = new Random();
		n=r.nextInt(7);
		return (n==0?1:n);
	}
	public void playerTurn(int players){ // method to display message i-e which player is going to roll
		String s = "";
		if(players==1){
			s="FIRST";	// for player1 display FIRST
		}
		else if(players==2){
			s="SECOND"; // for player2 diplay SECOND
		}
		else if(players==3){
			s="THIRD"; // for player3 display THIRD
		}
		System.out.println(s+" PLAYER TURN: "); // followed by PLAYER TURN: 
	}
	public void startGame()
	{
		int player1 =0, player2=0, player3=0; // three players
		boolean isP1 = true, isP2 = false, isP3 = false; // booleans to know which player is rolling
		// at first Player1 will roll therefore it's true others are false
		Scanner s = new Scanner(System.in);
		String str;
		int diceValue =0;
		do
		{
			if(isP1){ // if P1 is rolling if isP1 is true then display the text 
				playerTurn(1);
			}
			else if(isP2){
				playerTurn(2); // if P2 is rolling if isP2 is true then display the text 	
			}
			else if(isP3){
				playerTurn(3);	// if P3 is rolling if isP3 is true then display the text 
			}
			// then print roll msg and roll the dice

			System.out.println("Press r to roll Dice");
			str = s.next();
			diceValue = rollDice();
			

			// if it was P1's turn then
			if(isP1)
			{
				isP1 = false; // turn is over therefore make it false
				isP2 = true; // after p1 always comes the turn of player2 therefore making it true
				player1 = calculatePlayerValue(player1,diceValue);
				System.out.println("First Player :: " + player1);
				System.out.println("Second Player :: " + player2);
				System.out.println("Third Player :: " + player3);
				System.out.println("------------------");
				if(isWin(player1))
				{
					System.out.println("First player wins");
					return;
				}
				
			}
			
			else if(isP2)
				
		
			{
				isP2 = false; // turn is over therefore making it false and moving to next player's turn
				isP3 = true; // it's 3rd player's turn, after player2 comes player3
				player2 = calculatePlayerValue(player2,diceValue);
				System.out.println("First Player :: " + player1);
				System.out.println("Second Player :: " + player2);
				System.out.println("Third Player :: " + player3);
				System.out.println("------------------");
				if(isWin(player2))
				{
					System.out.println("Second player wins");
					return;
				}
			}
			else if(isP3)
			{
				isP3 = false; // turn is over, all three players rolled, now again loop back firstPlayer will start rolling
				isP1 = true;// therefore making it true
				player3 = calculatePlayerValue(player3,diceValue);
				System.out.println("First Player :: " + player1);
				System.out.println("Second Player :: " + player2);
				System.out.println("Third Player :: " + player3);
				System.out.println("------------------");
				if(isWin(player3))
				{
					System.out.println("Third player wins");
					return;
				}

			}
			

			
			
		}while("r".equals(str));
	}
	
	
	public int calculatePlayerValue(int player, int diceValue)
	{
		player = player + diceValue;
		
		if(player > FINISHLINE)
		{
			player = player - diceValue; // if the value of the player is more than the finishline(100)
			return player;              // then return function is used to display the winner
		}
		
		if(snake.get(player)!=null)
		{
			System.out.println("\nswallowed by snake");
			player= snake.get(player);
		}
		
		if(ladder.get(player)!=null)
		{
			System.out.println("climb up the ladder");
			player= ladder.get(player);
		}
		return player;
	}
	
	public boolean isWin(int player)
	{
		return FINISHLINE == player;
	}
	
}

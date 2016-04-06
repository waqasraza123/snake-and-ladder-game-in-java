import java.util.Scanner;

public class SnakesAndLadders {

	//class level variables
	private int currentPlayer = 1 ;
	private int result = 0;
	private int numberOfSnakes = 5;
	private int numberOfLadders = 6;
	private int[][] snakes = new int[numberOfSnakes][2] ;
	private int[][] ladders = new int[numberOfLadders][2] ;
	private int numberOfPlayers = 4 ;
	int[] playersPositions;
	private int[] playersWon;
	private int[] waitingPlayer;
	private int rank = 0;
	private long memory = 0;
	private static long totalMemory;
	private boolean finish = false;
	private int rounds = 0;
	private static int totalRounds = 0;
	private static SnakesAndLadders game;
	private static Scanner scanner;
	private static int numberOfRoundsToPlay = 0;
	
	
	//main thread starts here ===========================================
	public static void main(String args[]){
		
		System.out.println("Please enter number of times to play the game");
		scanner = new Scanner(System.in);
		numberOfRoundsToPlay = (int)scanner.nextInt();
		
		
		//Playing the game numberOfRoundsToPlay times =================================
		for(int i=0; i<numberOfRoundsToPlay; i++){
			
			System.out.println("Game Begins.... #" + (i+1));
			game = new SnakesAndLadders();
			totalRounds+=game.rounds;
			totalMemory += game.memory;
			System.out.println("==========\nNumber of Players " + game.numberOfPlayers);
			System.out.println("==========\nGame Rounds " + game.rounds);
			System.out.println("==========\nMemory consumption" + game.memory+ "\n\n");
		}
		
		System.out.println("\n");
		System.out.println("Total rounds for games are "+ totalRounds);
		System.out.println("memory usage "+ totalMemory);
		System.out.println("Average number of rounds"+ totalRounds/numberOfRoundsToPlay);
		System.out.println("Average memory occupied "+ totalMemory/numberOfRoundsToPlay);
		
	}
	
	//initialize things and variables ========================================
	SnakesAndLadders(){		
		
		//number of players 4 ================================================
 		numberOfPlayers= (int) ((Math.random() * 3) +2);
		playersPositions = new int[numberOfPlayers];
		waitingPlayer = new int[numberOfPlayers];		
		playersWon = new int[numberOfPlayers];
		
		//populating snake and ladder =========================================
		populateSnake();								
		populateLadder();								
		
		play();
		
	}
	/* constructor that takes number of players as input to populate fields*/
	SnakesAndLadders(int n){
		numberOfPlayers= n;
		playersPositions = new int[numberOfPlayers];
		waitingPlayer = new int[numberOfPlayers];
		playersWon = new int[numberOfPlayers];
		populateSnake();
		populateLadder();
		
		play();
		
	}
	
	
	/*function to change turnns of players and increasing number of rounds when all players have taken turn*/
	private void changeTurn(){
		if(currentPlayer == numberOfPlayers){
			rounds++;
			currentPlayer = 0;
		}
		currentPlayer++;

		/*If all players have won except one.*/
		if(playersWon[currentPlayer-1] != 0){
			if(rank >= (numberOfPlayers -1) ){
				finish= true;  /*Game finished*/
			} 
			changeTurn();
		}
		
	}
	
	
	//place players and inititate their positions as well as setting won players and struck players to 0
	void placePlayers(){
		for (int i=0 ; i < playersPositions.length;i++){
			playersPositions[i] = 0;
		}
		for (int i=0 ; i < waitingPlayer.length;i++){
			waitingPlayer[i] = 0;
		}
		for (int i=0 ; i < playersWon.length;i++){
			playersWon[i] = 0;
		}
	}
	
	//play game ============================================
	private void play(){
		placePlayers();
		while(!finish){ //while not game finished
			throwDice();
			changeTurn();
			
			
			// Get the Java runtime to calculate memory usage
		    Runtime runtime = Runtime.getRuntime();
		    // Run the garbage collector
		    runtime.gc();
		    // Calculate the used memory and store in global variable
		    memory = runtime.totalMemory() - runtime.freeMemory();
		}
	}
	

	//evaluate dice thrown results
	private void evaluateResult(){
	
		//if reslult < numberOfRoundsToPlay 
		if(playersPositions[currentPlayer-1]+result <= 100){
			playersPositions[currentPlayer-1]+=result;
		} 
		//if player has reached numberOfRoundsToPlay
		if(playersPositions[currentPlayer-1] == 100){
			rank ++;
			playersWon[currentPlayer-1] = rank;
			//System.out.println("Player "+ currentPlayer + " won");
			if(rank == (numberOfPlayers -1) ){
			//System.out.println("All Players Have Finished");
			finish= true;
			}
		}
		//2nd turn when player has 6
		if(result == 6 ){
			//if player is struck when bitten by snake and 6 occurs
			if(waitingPlayer[currentPlayer-1] ==1){
				waitingPlayer[currentPlayer-1] =0;
			}
			throwDice();
		}
		//Check snake and ladders ============================================
		checkSnake();
		checkLadder();
	}
	
	// check if player has landed on a snake ================================
	private boolean checkSnake(){
		
		for(int i=0;i<snakes.length;i++){
			if(snakes[i][1] == playersPositions[currentPlayer-1]){
				playersPositions[currentPlayer-1] = snakes[i][0];
				waitingPlayer[currentPlayer-1] = 1;
				
				return true;
			}
		}
		return false;
	}
	
	//check if player has landed on a ladder ===========================================
	private boolean checkLadder(){
		
		for(int i=0;i<ladders.length;i++){
			if(ladders[i][0] == playersPositions[currentPlayer-1]){
				playersPositions[currentPlayer-1] = ladders[i][1];
				throwDice();
				return true;
			}
		}
		return false;
	}
	//populate random snakes ==========================================
	private void populateSnake(){
		for(int i=0;i<snakes.length;i++){
			snakes[i][0] = (int) ((Math.random() * 40) +1);
			snakes[i][1] = (int) ((Math.random() * 40) +60);
		}
	}
	//populate random ladders =======================================
	private void populateLadder(){
		for(int i=0;i<ladders.length;i++){
			ladders[i][0] = (int) ((Math.random() * 40) +1);
			ladders[i][1] = (int) ((Math.random() * 40) +60);
		}
	}

	
	//throw dice and return numbers from 1 to 6 =====================================
	private int throwDice(){
		result = (int) (( Math.random() * 6 )+1);
		evaluateResult();
		return result;
	}
}
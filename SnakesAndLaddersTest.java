import static org.junit.Assert.*;

public class SnakesAndLaddersTest {

	
	//checking if players are place correctly through unit test.
	@org.junit.Test
	public void test() {
		SnakesAndLadders bg = new SnakesAndLadders();
		bg.placePlayers();
		int player1Postion = bg.playersPositions[0];
		assertEquals(player1Postion,0);
	}

}

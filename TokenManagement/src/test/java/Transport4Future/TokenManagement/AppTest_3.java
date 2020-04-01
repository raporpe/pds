package Transport4Future.TokenManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest_3 {

	private static TokenManager tokenManager;

	@BeforeAll
	static void TM_RF_03() {
		tokenManager = new TokenManager();
	}

	/**
	 * A simple graph drawing might suggest that five test must be made, but it is important to notice that
	 * there is a logical dependency in the code: if a token is found inside the loop that is inside the find function,
	 * then the check performed in the if -> "if (tokenFound != null) {}" will unavoidably be executed.
	 *
	 * There is a hidden logical dependency:
	 *
	 * IF AND ONLY IF the "if" inside the search loop is triggered THEN the if that checks the nullity
	 * of the 'tokenFound' variable will be triggered.
	 *
	 * This dependency can be represented in the graph, which simplifies it a lot.
	 * After this simplification, we recalculate the McCabe complexity and it yields four.
	 * Performing five tests regarding the structure would have been redundant since it would no have been possible to
	 * find the combination in which a token is found and the idValid() function inside the if is not executed, as formerly
	 * demonstrated.
	 *
	 *
	 * Regarding loop testing, we found some particularities.
	 * There are two loops.
	 * The first one is infinite because it will iterate as many times as tokens there are in the database.
	 * Therefore, there is not a maximum value and we have only tested the zero case (the loop is skipped) and
	 * the case in which it is executed once.
	 *
	 * The second loop traverses the list that was filled by the first loop.
	 * If the first loop iterated N times, so will the second loop do: the second one depends on the first.
	 * The second loop has a equivalent behaviour to the first one and testing the first one will cause the same tests
	 * to be performed on the second one.
	 *
	 * In conclusion, we only need to perform a test in which we go trough the first loop twice (and thus the second one)
	 * /

	@Test
	static void malformedJSON(){

	}


	@Test
	static void checkInvalidTokenWhenStoreEmpty(){

	}

	@Test
	static void checkInvalidTokenWhenStoreNotEmpty(){

	}

	@Test
	static void checkValidTokenWhenStoreNotEmpty(){

	}


	//Check the loops

}

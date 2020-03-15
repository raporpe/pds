package Transport4Future.TokenManagement;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	
	/* Test case: CP_RF1_01
	* Equivalence class or boundary value considered: <CE-RF1-V-01
	* Testing technique: Equivalence Class
	* Expected value: Pass 
	*/

	@Test
	public void CP_RF1_01()
	{
		String filePath = "resources/CP-RF1-01.json";
		TokenManager tm = new TokenManager();
		Assertions.assertThrows(TokenManagementException.class, ()-> tm.readTokenRequestFromJSON(filePath));
		assertNotNull(filePath);
	}
	
	@Test
	public void TM_RF1_01_P1()
	{
		String filePath = "src/resources/CP-RF1-01.json";
		TokenManager tm = new TokenManager();
		try {
			TokenRequest tr = tm.readTokenRequestFromJSON(filePath);
			Assertions.assertTrue(tr.getDeviceName().length()<20);
			Assertions.assertTrue(tr.getDeviceName().length()>=1);
			Assertions.assertTrue(tr.getTypeDevice().equals("Sensor") || tr.getTypeDevice().equals("Actuator"));
			Assertions.assertTrue(tr.getDriverVersion().matches("([0-9])(\.[0-9])*"));
			Assertions.assertTrue(tr.getSerialNumber().matches("[0-z]+(\/[0-z]+)+"));
			Assertions.assertTrue(tr.getDriverVersion().length()<50);
			Assertions.assertTrue(tr.getMacAddress().matches("[0-Z][0-Z])(:[0-Z][0-Z]+");
			Assertions.assertTrue(tr.getMacAddress().length()==17;
		}catch(TokenManagementException a) {
			System.out.println(a.getMessage());
		}
	}
	
	@Test
	public void CP_RF1_02_I1()
	{
		String filePath = "src/resources/CP-RF1-01.json";
		TokenManager tm = new TokenManager();
		try {
			TokenRequest tr = tm.readTokenRequestFromJSON(filePath);
			Assertions.assertTrue(tr.getDeviceName().length()<20);
			Assertions.assertTrue(tr.getDeviceName().length()>=1);
			Assertions.assertTrue(tr.getTypeDevice().equals("Sensor") || tr.getTypeDevice().equals("Actuator"));
			Assertions.assertTrue(tr.getDriverVersion().matches("([0-9])(\.[0-9])*"));
			Assertions.assertTrue(tr.getSerialNumber().matches("[0-z]+(\/[0-z]+)+"));
			Assertions.assertTrue(tr.getDriverVersion().length()<50);
			Assertions.assertTrue(tr.getMacAddress().matches("[0-Z][0-Z])(:[0-Z][0-Z]+");
			Assertions.assertTrue(tr.getMacAddress().length()==17;
		}catch(TokenManagementException a) {
			System.out.println(a.getMessage());
		}
	}
	
	
}

package Transport4Future.TokenManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenRequest {

	private String deviceName;
	private String typeDevice;
	private String driverVersion;
	private String email;
	private String serialNumber;
	private String macAddress;
	private String password;

	/**
	 * @param deviceName
	 * @param typeDevice
	 * @param driverVersion
	 * @param serialNumber
	 * @param macAddress
	 */
	public TokenRequest(String deviceName, String typeDevice, String driverVersion, String email,
			String serialNumber, String macAddress) throws TokenManagementException {
		super();

		if (deviceName.length() < 20 && deviceName.length() > 1) {
			this.deviceName = deviceName;

		} else {
			throw new TokenManagementException("Error: deviceName uses an invalid format.");
		}

		if (typeDevice.equals("Sensor") | typeDevice.equals("Actuator")) {
			this.typeDevice = typeDevice;
		} else {
			throw new TokenManagementException("Error: typeDevice uses an invalid format.");
		}

		if (driverVersion.matches("([0-9]{4})(-[0-9]{2}){2}")) {
			this.driverVersion = driverVersion;
		} else {
			throw new TokenManagementException("Error: driverVersion uses an invalid format.");
		}

		if (email.matches(
				"^[A-Z0-9._%+a-z]+@[A-Z0-9.a-z]+\\.[A-Za-z]{2,6}$")) {
			this.email = email;
		} else {
			throw new TokenManagementException("Error: email is in an invalid format.");
		}

		if (serialNumber.matches("^[A-Za-z0-9-]+$")) {
			this.serialNumber = serialNumber;
		} else {
			throw new TokenManagementException("Error: serialNumber uses an invalid format.");
		}

		if (macAddress.matches("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$") && macAddress.length() == 17) {
			this.macAddress = macAddress;
		} else {
			throw new TokenManagementException("Error: macAddress uses an invalid format.");
		}
	}

	public String getHash() throws TokenManagementException {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("The MD5 algorithm was not found");
		}
		String input = this.password + "-" + this.toString();

		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it
		// should be "%064x"
		String hex = String.format("%32x", new BigInteger(1, digest));
		return hex;
	}

	@Override
	public String toString() {
		return "TokenRequest [deviceName=" + deviceName + ", typeDevice=" + typeDevice + ", driverVersion="
				+ driverVersion + ", email=" + email + ", serialNumber=" + serialNumber + ", macAddress="
				+ macAddress + "]";
	}
	
	//Getters and setters
	
	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @return the typeDevice
	 */
	public String getTypeDevice() {
		return typeDevice;
	}

	/**
	 * @return the driverVersion
	 */
	public String getDriverVersion() {
		return driverVersion;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}
}

package Transport4Future.TokenManagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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

		if (this.deviceName.length() < 20 && this.deviceName.length() > 1) {
			this.deviceName = deviceName;

		} else {
			throw new TokenManagementException("Error: deviceName uses an invalid format.");
		}

		if (this.typeDevice.equals("Sensor") | this.typeDevice.equals("Actuator")) {
			this.typeDevice = typeDevice;
		} else {
			throw new TokenManagementException("Error: typeDevice uses an invalid format.");
		}

		if (this.driverVersion.matches("([0-9]{4})(-[0-9]{2}){2}")) {
			this.driverVersion = driverVersion;
		} else {
			throw new TokenManagementException("Error: driverVersion uses an invalid format.");
		}

		if (this.email.matches(
				"^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")) {
			this.email = email;
		} else {
			throw new TokenManagementException("Error: email uses an invalid format.");
		}

		if (this.serialNumber.length() < 50) {
			this.serialNumber = serialNumber;
		} else {
			throw new TokenManagementException("Error: serialNumber uses an invalid format.");
		}

		if (this.macAddress.matches("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$") && this.macAddress.length() == 17) {
			this.macAddress = macAddress;
		} else {
			throw new TokenManagementException("Error: macAddress uses an invalid format.");
		}
	}

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

	public String TokenRequestGeneration() throws TokenManagementException {

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
}

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
	 * @param deviceName the name of the device.
	 * @param typeDevice the type can be either a Sensor or an Actuator.
	 * @param driverVersion the version with a maximum of 25 elements separated by '.'
	 * @param serialNumber combination of 50 letters, numbers and bars without spaces.
	 * @param macAddress six octets separated by ':'
	 */
    TokenRequest(String deviceName, String typeDevice, String driverVersion, String email,
                 String serialNumber, String macAddress) throws TokenManagementException {
		super();

		if (deviceName.length() <= 20 && deviceName.length() > 1) {
			this.deviceName = deviceName;

		} else {
			throw new TokenManagementException(ErrorMessage.deviceNameInvalidFormat);
		}

		if (typeDevice.equals("Sensor") | typeDevice.equals("Actuator")) {
			this.typeDevice = typeDevice;
		} else {
			throw new TokenManagementException(ErrorMessage.typeDeviceInvalidFormat);
		}

		if (driverVersion.matches("^[0-9]+(.[0-9]+){0,24}$")) {
			this.driverVersion = driverVersion;
		} else {
			throw new TokenManagementException(ErrorMessage.driverVersionInvalidFormat);
		}

		if (email.matches(
				"^[A-Z0-9._%+a-z]+@[A-Z0-9.a-z]+\\.[A-Za-z]{2,6}$")) {
			this.email = email;
		} else {
			throw new TokenManagementException(ErrorMessage.emailInvalidFormat);
		}

		if (serialNumber.matches("^[A-Za-z0-9-]{1,50}$")) {
			this.serialNumber = serialNumber;
		} else {
			throw new TokenManagementException(ErrorMessage.serialNumberInvalidFormat);
		}

		if (macAddress.matches("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$")) {
			this.macAddress = macAddress;
		} else {
			throw new TokenManagementException(ErrorMessage.macAddressInvalidFormat);
		}
	}

    /**
     * Calculates the MD5 hash
     * @return the MD5 hash
     * @throws TokenManagementException
     */
	String getHash() throws TokenManagementException {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ErrorMessage.md5AlgorithmNotFound);
		}
		String input = this.password + "-" + this.toString();

		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		String hex = String.format("%32x", new BigInteger(1, digest));
		return hex;
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

    @Override
    public String toString() {
        return "TokenRequest [deviceName=" + deviceName + ", typeDevice=" + typeDevice + ", driverVersion="
                + driverVersion + ", email=" + email + ", serialNumber=" + serialNumber + ", macAddress="
                + macAddress + "]";
    }
}

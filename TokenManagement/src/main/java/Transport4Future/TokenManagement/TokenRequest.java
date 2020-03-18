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
	public TokenRequest(String deviceName, String typeDevice, String driverVersion, String serialNumber,
			String macAddress) {
		super();
		this.deviceName = deviceName;
		this.typeDevice = typeDevice;
		this.driverVersion = driverVersion;
		this.serialNumber = serialNumber;
		this.macAddress = macAddress;
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

	public String getToken() throws NoSuchAlgorithmException {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e) {
			throw e;
		}
		String input =  this.password + "-" + this.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		return hex;
	}

	@Override
	public String toString() {
		return "TokenRequest [deviceName=" + deviceName + ", typeDevice=" + typeDevice + ", driverVersion="
				+ driverVersion + ", serialNumber=" + serialNumber + ", macAddress=" + macAddress + "]";
	}
	
	
}

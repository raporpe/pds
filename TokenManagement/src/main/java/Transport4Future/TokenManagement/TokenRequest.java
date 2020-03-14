package Transport4Future.TokenManagement;


public class TokenRequest {
	
	private String deviceName;
	private String typeDevice;
	private String driverVersion;
	private String serialNumber;
	private String macAddress;
	
	
	
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



	@Override
	public String toString() {
		return "TokenRequest [deviceName=" + deviceName + ", typeDevice=" + typeDevice + ", driverVersion="
				+ driverVersion + ", serialNumber=" + serialNumber + ", macAddress=" + macAddress + "]";
	}
	
	
}

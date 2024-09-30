package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class Constants {

	private static Properties prop;
	private static final Map<String, String> ConfigMap = new HashMap<>();
	public static String OSName, OSArchitecture, OSVersion, OSBit;
	public static String totalTestCasesCount, passedTestCasesCount, failedTestCasesCount, skippedTestCasesCount;
	File src;

	/**
	 * This method is for read the configuration file
	**/
	public void readConfigFile() {
			src = new File("./src/main/resources/configFiles/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				ConfigMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			}
		} catch (Exception e) {
			System.out.println("Exception is : " + e.getMessage());
		}
	}

	/**
	 * This method returns the value from the configuration file by passing the name
	 * of the key
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String get(String key) throws Exception {
		if (Objects.isNull(key) || Objects.isNull(ConfigMap.get(key))) {
			throw new Exception("property name " + key + " is not found. Please check config.properties file");
		}
		return ConfigMap.get(key).trim();
	}

	/**
	 * Initializes Configuration Variables
	 */
	public void initConfig() {
		OSName = System.getProperty("os.name");
		OSArchitecture = System.getProperty("os.arch");
		OSVersion = System.getProperty("os.version");
		OSBit = System.getProperty("sun.arch.data.model");
	}
}
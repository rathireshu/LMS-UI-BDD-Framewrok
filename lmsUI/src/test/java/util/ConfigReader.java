package util;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties pro=null; 
	
	private static void readConfigFile(){

	try {
		pro = new Properties();
		FileReader file = new FileReader("src/test/resources/Config/config.properties");
		pro.load(file);
	   } catch (Exception e) {
		      System.out.println(e.getMessage());
        }
    }
	
	public static Properties getPropObject() {
		
		if( pro == null ) {
			readConfigFile();
		}
		return pro;
	}

	
}

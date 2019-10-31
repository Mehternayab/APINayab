package Twitter_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropFile {
	
	public Properties prop() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Online Test\\Desktop\\Mehter Nayab\\TwitterAPI\\src\\Twitter_Test\\data.properties");
		prop.load(fis);
		return prop;
	}

}

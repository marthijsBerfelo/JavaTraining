package testsuite;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

public class TestCommon {
	
	static{
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	    System.setProperty("current.date", dateFormat.format(new Date()));
	}
	
	final static Logger logger = Logger.getLogger(TestCommon.class.getName());
			
	@BeforeSuite
	public void InitTest()
		{
		PropertyConfigurator.configure("src/log4j.properties");
	
		logger.info("Logging initialized");
		}
	

}

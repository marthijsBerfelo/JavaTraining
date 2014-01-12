package testsuite;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;


@Test
public class Log4JTest extends TestCommon{

	
	Log4JTest() {
		
		logger.debug("Hello, this is a DEBUG message");
		logger.info("Hello, this is an INFO message");
		logger.warn("Hello this is a WARNING message");
		logger.error("Hello, this is an ERROR message");

	}
	
	
	static Logger logger = Logger.getLogger(Log4JTest.class);
	
	public static void main(String[] args) throws IOException, SQLException{
		
		logger.debug("Hello, this is a DEBUG message");
		logger.info("Hello, this is an INFO message");
		logger.warn("Hello this is a WARNING message");
		logger.error("Hello, this is an ERROR message");
	}

}

package tools;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class DataProviders {
	
	@DataProvider
	static Object[][] LoginTestInput() throws IOException{
		
		CSVReader reader = new CSVReader(new FileReader("src/input/LoginTestInputFile.csv"), ';');
		List<String[]> myEntries = reader.readAll();
	
		Object[][] returnObject = new Object[myEntries.size()][5];
		for(int i = 0;i<myEntries.size();i++){
			String testCase = myEntries.get(i)[0];
			String username = myEntries.get(i)[1];
			String password = myEntries.get(i)[2];
			String loginSuccessful = myEntries.get(i)[3];
			String description = myEntries.get(i)[4];
			returnObject[i][0] = testCase;
			returnObject[i][1] = username;
			returnObject[i][2] = password;
			returnObject[i][3] = loginSuccessful;
			returnObject[i][4] = description;
			
		}
		
		return returnObject;
	}

}

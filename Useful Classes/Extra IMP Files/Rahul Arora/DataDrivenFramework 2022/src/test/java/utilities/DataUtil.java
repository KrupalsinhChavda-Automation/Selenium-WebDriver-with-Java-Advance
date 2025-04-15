package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataUtil extends BaseTest{
	
	
	@DataProvider(name="dp1")
	public Object[][] getData(Method m) {
	
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
			
		Object[][] data = new Object[rows-1][cols];
	
		
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			
			for(int colNum=0; colNum<cols;colNum++) {
				
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				
			}
			
		}
		
		
		return data;
		
		
	}
	

}

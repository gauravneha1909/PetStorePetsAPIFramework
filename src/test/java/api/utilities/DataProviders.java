package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
	@DataProvider(name="PetData")
	
	public String[][] getAllData() throws IOException 
	
	{
		String path=System.getProperty("user.dir")+"//TestData//PetsData.xlsx";
		
		XLUtility xl= new XLUtility(path);
		
		int rownum= xl.getRowCount("Sheet1");
		int cellnum= xl.getCellCount("Sheet1",1);
		
		String apidata[][]=new String[rownum][cellnum];
		
		
		for (int i=1;i<=rownum;i++)
			
		{
			for (int j=0;j<cellnum;j++)
			
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
				
				
			}
			
			
			
			
			
		}
		
		return apidata;
	}
	
	
	
	@DataProvider(name="PetID")
	
	public String[] getpetid() throws IOException 
	
	{
		String path=System.getProperty("user.dir")+"//TestData//PetsData.xlsx";
		
		XLUtility xl= new XLUtility(path);
		
		int rownum= xl.getRowCount("Sheet1");

		
		String apidata[]=new String[rownum];
		
		
		for (int i=1;i<=rownum;i++)
			
		{
			
				apidata[i-1]=xl.getCellData("Sheet1", i, 0);
				
	
		}
		
		return apidata;
	}

}

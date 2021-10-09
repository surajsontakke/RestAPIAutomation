package com.java.qa.sccm.Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.Test;



public class ExcelUtils {

	private final static String excelReport= System.getProperty("user.dir")+"//TestData//TestData.xlsx";
	public static Properties OR;
	static XLS_Reader xls=new XLS_Reader(excelReport);
	public static FileInputStream fis=null;
	public static HSSFWorkbook workbook;
    public static HSSFSheet worksheet;
    public String ColName="Result";
    public int col_num;
	
	//This function reads data from excel and returns as hash table
	public static Object[][] getData(String sheetName, XLS_Reader xls){
		int rowCount = xls.getRowCount(sheetName)-1;
		int columnCount=xls.getColumnCount(sheetName);
		System.out.println("rowCount="+rowCount);
		int c=0;
		for(int k=2;k<rowCount+2;k++){
			if(!xls.getCellData(sheetName, 0, k).equalsIgnoreCase("Y"))
				c++;
		}
		int d=0;
		int row=rowCount-c;
		System.out.println(row+"--"+columnCount);
		Object[][] data = new Object[row][1];
		Hashtable<String,String> table=null;
		for(int i=2;i<=rowCount+1;i++){
			if(xls.getCellData(sheetName, 0, i).equalsIgnoreCase("Y")){
				table=new Hashtable<String,String>();
				for(int j=1;j<columnCount;j++){
					String key=xls.getCellData(sheetName, j, 1).trim();
					String val=xls.getCellData(sheetName, j, i).trim();//.split(",")[0];
					//System.out.println(key+"--"+val);
					table.put(key, val);
				}
				//System.out.println(d);
				data[d][0]=table;
				d++;
			}
		}
		return data;
	}
	
	public static String getTestData(Hashtable<String,String> testdata,String columnName) throws IOException{
		String data="";
		try{
			data=testdata.get(columnName);
		}catch(Exception e){
			System.out.println(columnName+"does not exist in datasheet");
			e.printStackTrace();
		}
		return data;
	}

	@Test
    public void WriteResult(String SheetName , String Ress, int DR) throws Exception
    {
        FileInputStream file_input_stream= new FileInputStream("/home/users/shivani.kukreti/Documents/akeneo-product.xls");
        workbook=new HSSFWorkbook(file_input_stream);
        worksheet=workbook.getSheet(SheetName);
        HSSFRow Row=worksheet.getRow(0);
 
        int sheetIndex=workbook.getSheetIndex(SheetName);
        DataFormatter formatter = new DataFormatter();
        if(sheetIndex==-1)
        {
            System.out.println("No such sheet in file exists");
        } else      {
            col_num=-1;
                for(int i=0;i<Row.getLastCellNum();i++)
                {
                    HSSFCell cols=Row.getCell(i);
                    String colsval=formatter.formatCellValue(cols);
                    if(colsval.trim().equalsIgnoreCase(ColName.trim()))
                    {
                        col_num=i;
                        break;
                    }
                }
//              
                Row= worksheet.getRow(DR);
                try
                    {
                    //get my Row which is equal to Data  Result and that colNum
                        HSSFCell cell=worksheet.getRow(DR).getCell(col_num);
                        // if no cell found then it create cell
                        if(cell==null) {
                            cell=Row.createCell(col_num);                           
                        }
                        //Set Result is pass in that cell number
                        cell.setCellValue(Ress);
                                         
                         
                    }
                catch (Exception e)
                {
                    System.out.println(e.getMessage()); 
                } 
    
        }
            FileOutputStream file_output_stream=new FileOutputStream("/home/users/shivani.kukreti/Documents/akeneo-product.xls");
            workbook.write(file_output_stream);
            file_output_stream.close();
            if(col_num==-1) {
                System.out.println("Column you are searching for does not exist");
            }
    }
	
}
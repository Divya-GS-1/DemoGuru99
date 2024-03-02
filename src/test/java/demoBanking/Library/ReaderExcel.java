package demoBanking.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReaderExcel {
	XSSFSheet sheet;
	File file;
	XSSFWorkbook workbook;
	FileOutputStream fos;
	
	ReaderExcel(String file_path, String sheetName){
		
		try {
			file=new File(file_path);
			FileInputStream fis =new FileInputStream(file);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println("Exceptions occured: "+ e.getMessage());
		}
	}
	
	public int getRowCount() {
		return sheet.getLastRowNum();
	}
	
	public int getLastColumn() {
		return sheet.getRow(0).getLastCellNum();
	}
	
	public String getCellData(int rowNum, int colNum) {
		return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	}
	
	public void setCellData(int rowNum, int colNum, String Data) {
		XSSFRow row = sheet.getRow(rowNum);
		if(row!=null) {
			row.createCell(colNum).setCellValue(Data);
		}else {
			sheet.createRow(rowNum).createCell(colNum).setCellValue(Data);
		}
		
		
		try {
			fos=new FileOutputStream(file);
			workbook.write(fos);
		} catch (Exception e) {
			System.out.println("Exceptions occured: "+ e.getMessage());
		}
	}
}

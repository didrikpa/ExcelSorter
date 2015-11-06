package excel_sorter.Controller;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by didrikpa on 02.11.15.
 */
public class ExcelWriter {
    private String filename;
    private String sheetName;


    public ExcelWriter(String filename, String sheetName){
        this.filename = filename;
        this.sheetName = sheetName;
    }

    public void makeNewExcel(HashMap<String, Integer> contestantsParticipations){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        sheet.createRow(0);
        sheet.createRow(1);
        sheet.getRow(1).createCell(1);
        sheet.getRow(1).createCell(2);
        sheet.getRow(1).createCell(3);
        sheet.getRow(1).getCell(1).setCellValue("Etternavn");
        sheet.getRow(1).getCell(2).setCellValue("Fornavn");
        sheet.getRow(1).getCell(3).setCellValue("Antall deltakelser");
        Iterator<String> setIterator = contestantsParticipations.keySet().iterator();
        int rowCounter = 2;
        while (setIterator.hasNext()){
            sheet.createRow(rowCounter);
            sheet.getRow(rowCounter).createCell(1);
            sheet.getRow(rowCounter).createCell(2);
            sheet.getRow(rowCounter).createCell(3);
            String name = setIterator.next();
            String[] names = name.split("_");
            String firstName = names[1];
            String lastName = names[0];
            sheet.getRow(rowCounter).getCell(1).setCellValue(lastName);
            sheet.getRow(rowCounter).getCell(2).setCellValue(firstName);
            sheet.getRow(rowCounter).getCell(3).setCellValue(contestantsParticipations.get(name));
            rowCounter++;
        }
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            wb.write(fos);
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}



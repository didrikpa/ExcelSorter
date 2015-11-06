package excel_sorter.Controller;

/**
 * Created by didrikpa on 02.11.15.
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    private int sheetNumber;
    public String file;

    public ExcelReader(int sheetNumber, String file){
        this.sheetNumber = sheetNumber;
        this.file = file;
    }

    public HashMap<String, Integer> readContestantsFile(String file){
        HashMap<String, Integer> contestantsParticipations = new HashMap<>();
        ArrayList<String> lastNameIndex = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(sheetNumber - 1);
            fis.close();

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    String cellCoord = cell.getColumnIndex()+"";
                    if (cell.toString().equals("Etternavn")) {
                        lastNameIndex.add(cellCoord);
                    }
                    String key = cell.toString() + "_" + sheet.getRow(cell.getRowIndex()).getCell(Integer.parseInt(cellCoord) + 1);
                    if (lastNameIndex.contains(cellCoord) && !cell.toString().equals("Etternavn")){
                        if (sheet.getRow(cell.getRowIndex()).getCell(Integer.parseInt(cellCoord)+2).toString().equals("F")){
                            if (hasKey(key, contestantsParticipations)){
                                addParticipation(key, contestantsParticipations);
                            }
                            else {
                                addContestant(cell.toString(), sheet.getRow(cell.getRowIndex()).getCell(Integer.parseInt(cellCoord)+1).toString(), contestantsParticipations);
                            }
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return contestantsParticipations;
    }

    private boolean hasKey(String key, HashMap<String, Integer> contestantsParticipations){
        if (contestantsParticipations.containsKey(key)){
            return true;
        }
        return false;
    }

    private HashMap<String, Integer> addContestant(String lastName, String firstName, HashMap<String, Integer> contestantsParticipations){
        String key = lastName+"_"+firstName;
        contestantsParticipations.put(key, 1);
        return contestantsParticipations;
    }

    private HashMap<String, Integer> addParticipation(String key, HashMap<String, Integer> contestantsParticipations){
        int participations = contestantsParticipations.get(key) + 1;
        contestantsParticipations.put(key, participations);
        return contestantsParticipations;
    }
}

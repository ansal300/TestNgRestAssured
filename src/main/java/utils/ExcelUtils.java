package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelUtils {

    public static List<LinkedHashMap<String, String>> ReturnDataFromExcelAsMap(String fileName,String sheetName) throws IOException {
        List<LinkedHashMap<String,String>> data=new ArrayList<>();

        Workbook workbook= WorkbookFactory.
                create(new File(fileName));
        Sheet sheet=workbook.getSheet(sheetName);

        int rows=sheet.getPhysicalNumberOfRows();
        List<String>keys=new ArrayList<>();
        LinkedHashMap<String,String>map;
        DataFormatter dataFormatter=new DataFormatter();

        for(int i=0;i<rows;i++)
        {
            if(i==0) {
                int columns = sheet.getRow(0).getPhysicalNumberOfCells();
                for (int j = 0; j < columns; j++) {
                    keys.add(sheet.getRow(0).getCell(j).getStringCellValue());
                }
            }
            else
            {
                map=new LinkedHashMap<>();
                int columns = sheet.getRow(i).getPhysicalNumberOfCells();
                for (int j = 0; j < columns; j++) {
                    String value=dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                    map.put(keys.get(j),value);
                }

                data.add(map);
            }
        }

        return data;
    }
}

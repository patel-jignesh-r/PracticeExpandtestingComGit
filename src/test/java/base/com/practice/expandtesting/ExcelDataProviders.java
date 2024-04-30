package base.com.practice.expandtesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProviders {

	@DataProvider(name = "excelReader")
	public static Iterator<Object[]> excelReader(Method method) {
		List<Object[]> list = new ArrayList<>();
		String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
				+ "dataproviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
				+ method.getName() + ".xlsx";
		File file = new File(pathname);
		try {
			FileInputStream inputStream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row dataRow = sheet.getRow(i);
				if (dataRow != null) {
					Map<String, String> testData = new HashMap<>();
					for (int j = 0; j < dataRow.getLastCellNum(); j++) {
						Cell cell = dataRow.getCell(j);
						String key = sheet.getRow(0).getCell(j).getStringCellValue(); // Assuming first row contains
																						// keys

						if (cell != null) {
							switch (cell.getCellType()) {
							case STRING:
								testData.put(key, cell.getStringCellValue());
								break;
							case NUMERIC:
								// Handle numeric values appropriately, e.g., convert to string
								testData.put(key, String.valueOf(cell.getNumericCellValue()));
								break;
							// Handle other cell types if necessary
							default:
								testData.put(key, ""); // Empty string for unsupported types
							}
						} else {
							testData.put(key, ""); // Empty string for null cells
						}
					}
					list.add(new Object[] { testData });
				}
			}
			workbook.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathname + " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathname + " file.\n" + e.getStackTrace().toString());
		}

		return list.iterator();
	}
}

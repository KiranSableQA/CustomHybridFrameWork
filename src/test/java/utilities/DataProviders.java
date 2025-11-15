package utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders  
{
	
@DataProvider(name="LoginData")
public String [][] getData() throws IOException
{
    String path = ".\\testData\\TestData.xlsx"; // taking xl file from testData

    ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

    int totalrows = xlutil.getRowCount("Sheet1");
    int totalcols = xlutil.getCellCount("Sheet1", 1);

    String logindata[][] = new String[totalrows][totalcols]; // created for two dimension array which can store data

    for(int i=1; i<=totalrows; i++) // the data from xl storing in two dimensional array
    {
        for(int j=0; j<totalcols; j++) // i is rows, j is col
        {
            logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
        }
    }

    return logindata; // returning two dimension array
}

// 2. Static Data Provider
@DataProvider(name = "staticData")
public Object[][] getStaticData() {
    return new Object[][] {
        { "user1", "pass1" },
        { "user2", "pass2" },
        { "user3", "pass3" }
    };
}

// 3. Iterator-based Data Provider
@DataProvider(name = "iteratorData")
public Iterator<Object[]> getDataIterator() {
    List<Object[]> data = new ArrayList<>();
    data.add(new Object[] { "admin", "admin123" });
    data.add(new Object[] { "manager", "manager123" });
    return data.iterator();
}

// 4. CSV File Data Provider
@DataProvider(name = "csvData")
public Object[][] getCSVData() throws IOException {
    String path = ".\\testData\\login.csv"; // make sure this file exists
    List<Object[]> records = new ArrayList<>();

    BufferedReader reader = new BufferedReader(new FileReader(path));
    String line;
    while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",");
        records.add(fields);
    }
    reader.close();

    return records.toArray(new Object[0][]);
}

// 5. Data Provider with Custom Object
@DataProvider(name = "userData")
public Object[][] getUserData() {
    return new Object[][] {
        { new User("john", "john123") },
        { new User("alice", "alice321") }
    };
}

// 6. Excel File Data Provider (using Apache POI)
@DataProvider(name = "excelData")
public Object[][] readExcelData() throws Exception {
    FileInputStream fis = new FileInputStream(".\\testData\\data.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheet("Sheet1");

    int rowCount = sheet.getPhysicalNumberOfRows();
    int colCount = sheet.getRow(0).getLastCellNum();

    Object[][] data = new Object[rowCount - 1][colCount];

    for (int i = 1; i < rowCount; i++) {
        for (int j = 0; j < colCount; j++) {
            data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
        }
    }
    workbook.close();
    fis.close();

    return data;
}

// 7. Sample Test Method using any DataProvider
@Test(dataProvider = "staticData")
public void testStaticData(String username, String password) {
    System.out.println("Username: " + username + ", Password: " + password);
}

@Test(dataProvider = "userData")
public void testUserObject(User user) {
    System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
}

// You can add more test methods for other data providers similarly

// --- Helper class for user object ---
public static class User 
{
    private String username;
    private String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

}
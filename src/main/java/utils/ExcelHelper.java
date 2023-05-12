package utils;
import entity.Student;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;

public class ExcelHelper {
    // 读取单元格的值
    private String getValue(Cell cell) {
        String result = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                result = cell.getBooleanCellValue() + "";
                break;
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                result = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                // 可能是普通数字，也可能是日期
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    result = DateUtil.getJavaDate(cell.getNumericCellValue())
                            .toString();
                } else {
                    result = cell.getNumericCellValue() + "";
                }
                break;
        }
        return result;
    }

    /***
     * 这种方法支持03，和07版本的excel读取
     * 但是对于合并的单元格，除了第一行第一列之外，其他部分读取的值为空
     * @param is
     */
    public ArrayList<Student> importXlsx(InputStream is) {
        ArrayList<Student> users = new ArrayList<>();

        try {
            Workbook wb = WorkbookFactory.create(is);
            for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
                Sheet sheet = wb.getSheetAt(i);
                for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                    Student student = new Student();
                    if (sheet == null) {
                        return null;
                    }
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        return null;
                    }
                    student.setId((int)row.getCell(0).getNumericCellValue());
                    student.setName(getValue(row.getCell(1)));
                    student.setSex(getValue(row.getCell(2)));
                    student.setGrade(getValue(row.getCell(3)));
                    student.setAge((int)row.getCell(4).getNumericCellValue());
                    student.setScore((int)row.getCell(5).getNumericCellValue());

                    users.add(student);
                    //user=null;
                    System.out.println(users);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(users);
        return users;
    }
}


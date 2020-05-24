package com.hzzx.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// https://www.jianshu.com/p/9d9d783fe8de
// https://blog.csdn.net/apollo687/article/details/82156361
// https://www.cnblogs.com/sprinkle/p/6426204.html
// https://www.cnblogs.com/Dreamer-1/p/10469430.html
public class ExcelUtil {
    /**
     * 修改最后一列的数据颜色
     */
    public static void write2Excel(String filePath, String sheetName, int rowNum) {
        try {
            FileOutputStream outputStream = null;
            FileInputStream inputStream = new FileInputStream(filePath);
            String fileType = getFileType(filePath);
            Workbook workbook = getWorkbook(inputStream, fileType);
            // 通过sheetName参数，生成Sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                if (firstRowNum + rowNum <= lastRowNum) {
                    Row row = sheet.getRow(firstRowNum + rowNum);
                    if (row != null) {
                        // 获取指定行最后一个cell
                        int lastCellNum = row.getPhysicalNumberOfCells();
                        Cell cell = row.getCell(lastCellNum -  1);
                        CellStyle cellStyle = workbook.createCellStyle();
                        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

                        cell.setCellStyle(cellStyle);
                        outputStream = new FileOutputStream(filePath);
                        workbook.write(outputStream);
                    }
                }
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object[][] readExcel(String filePath, String sheetName){
        Object[][] results = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            String fileType = getFileType(filePath);
            Workbook workbook = getWorkbook(inputStream, fileType);
            // 通过sheetName参数，生成Sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return null;
            }
            // 获取当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            // 创建名为records的list对象来存储从Excel数据文件读取的数据
            List<Object[]> records = new ArrayList<Object[]>();
            // 从第firstRowNum+1行，第firstRowNum行是数据列名称
            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                // 获取行对象
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                // 获取当前行的开始列
                int firstCellNum = row.getFirstCellNum();
                // 获取当前行的列数
                int lastCellNum = row.getPhysicalNumberOfCells();
                String cells[] = new String[row.getPhysicalNumberOfCells()];
                // 循环当前行
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                records.add(cells);
            }
            results = new Object[records.size()][];
            for (int i = 0; i < records.size(); i++) {
                results[i] = records.get(i);
            }
            inputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 获取Workbook
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equals("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileType.equals("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        return workbook;
    }
    public static Workbook getWorkbook(String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equals("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (fileType.equals("xls")) {
            workbook = new HSSFWorkbook();
        }
        return workbook;
    }

    /**
     * 获取文件类型，
     * @param filePath
     * @return xlsx或者xls
     */
    public static String getFileType(String filePath) {
        String fileExtensionName = filePath.lastIndexOf(".") == -1 ? "" : filePath
                .substring(filePath.lastIndexOf(".") + 1);
        return fileExtensionName;
    }

    /**
     * 获取单元格数据
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 把数字当成string来读，避免1读成1.0情况
        if(cell.getCellType() == CellType.NUMERIC){
            cell.setCellType(CellType.STRING);
        }
        // 判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}

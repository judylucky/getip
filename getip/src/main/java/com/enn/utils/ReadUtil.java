package com.enn.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.enn.vo.IpVo;

public class ReadUtil {
	private static Log log = LogFactory.getLog(ReadUtil.class);

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public List<IpVo> readXlsx(String path, String filename) {
		log.info("需要读取的文件路径为  : " + path);
		String source2Exel = "";
		InputStream is = null;
		XSSFWorkbook xssfWorkbook = null;
		
		List<IpVo> listeb = new ArrayList<IpVo>();
		try {
			is = new FileInputStream(path);
			xssfWorkbook = new XSSFWorkbook(is);
			// Read the Sheet
			for (int numSheet = 1; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
				// 1.根据excl名不同,跳过不需要处理的sheet
				if (filename.equals("ENN_IP Manage For LFDC.xlsx")) {
					if (numSheet == 1 || numSheet == 5) {
						continue;
					}
				}
				if (filename.equals("ENN_IP Manage For YiZhuang&YanJiaoDC.xlsx")) {
					if (numSheet == 1 || numSheet == 2) {
						continue;
					}
				}
				if (filename.equals("润泽新DC-IP信息规划.xlsx")) {
					if (numSheet == 1 || numSheet == 2 || numSheet == 3 || numSheet == 4) {
						continue;
					}
				}
				// 2.同一个exel每个sheet处理数据位置不同
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				// Read the Row
				if (filename.equals("ENN_IP Manage For LFDC.xlsx")) {
					int[] getCols = { 0, 4};
					String sheetName = xssfWorkbook.getSheetName(numSheet);
					source2Exel = "ENN_IP Manage For LFDC";
					
					int begin = 1;
					// 特殊sheet
					if (numSheet == 2) {
						begin = 4;
						getCols[0] = 2;
						getCols[1] = 3;
					}
					if (numSheet == 3 || numSheet == 4) {
						begin = 3;
						getCols[0] = 2;
						getCols[1] = 3;
					}
					if(sheetName.contains(ConstantUtils.SUFFIX)){
						if(sheetName.equals("10.11.192.0")){
							continue;
						}
						if(sheetName.equals("10.36.1") || sheetName.equals("10.36.2") || sheetName.equals("10.36.5") 
								||sheetName.equals("10.36.6") || sheetName.equals("10.36.8") || sheetName.equals("10.36.12")){
							getCols[0] = 0;
							getCols[1] = 1;
						}
					}
					for (int rowNum = begin; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
						// 定义取哪些列的值
						content2Bean(listeb, xssfSheet, rowNum, getCols,source2Exel);
					}
				}
				if (filename.equals("ENN_IP Manage For YiZhuang&YanJiaoDC.xlsx")) {
					int[] getCols = { 0, 4};
					source2Exel = "ENN_IP Manage For YiZhuang&YanJiaoDC";
					for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
						// 定义取哪些列的值
						content2Bean(listeb, xssfSheet, rowNum, getCols,source2Exel);
					}
				}
				if (filename.equals("润泽新DC-IP信息规划.xlsx")) {
					int[] getCols = { 0, 4};
					int begin =1;
					if(numSheet == 5 || numSheet == 6 || numSheet == 7 ){
						getCols[0] = 0;
						getCols[1] = 1;
					}
					if(numSheet == 8 || numSheet == 9){
						begin = 2;
						getCols[0] = 1;
						getCols[1] = 4;
					}
					source2Exel = "润泽新DC-IP信息规划";
					for (int rowNum = begin; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
						// 定义取哪些列的值
						content2Bean(listeb, xssfSheet, rowNum, getCols,source2Exel);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is) {
					is.close();
					is = null;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listeb;
	}

	public List<IpVo> readXls(String path, String filename) {
		log.info("需要读取的文件路径为  : " + path);
		List<IpVo> listeb = new ArrayList<IpVo>();
		InputStream is = null;
		HSSFWorkbook hssfWorkbook = null;
		try {
			is = new FileInputStream(path);
			hssfWorkbook = new HSSFWorkbook(is);
			// Read the Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// Read the Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					//content2Bean(listeb, hssfSheet, rowNum);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is) {
					is.close();
					is = null;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listeb;
	}

	private void content2Bean(List<IpVo> listeb, XSSFSheet xssfSheet, int rowNum, int[] getCols,String source2Exel) {
		XSSFRow xssfRow = xssfSheet.getRow(rowNum);
		IpVo ip = new IpVo();
		if (xssfRow != null) {
				if (null != xssfRow.getCell(getCols[0])) {
					//如果IP为空就没必要写此条记录
						ip.setIp(getValue(xssfRow.getCell(getCols[0])));
						if (null!=xssfRow.getCell(getCols[1])) {
							String value = getValue(xssfRow.getCell(getCols[1]));
							if(StringUtils.isEmpty(value)){
								value = ConstantUtils.IP_STATUE_FREE;
							}
							if(value.equals(ConstantUtils.IP_STATUE_F)){
								value = ConstantUtils.IP_STATUE_FREE;
							}
							if(value.equals(ConstantUtils.IP_STATUE_U)){
								value = ConstantUtils.IP_STATUE_USED;
							}
							ip.setStatus(value);
					}
						ip.setSourceExcel(source2Exel);
						listeb.add(ip);
				}
		}
	}

	private String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_FORMULA) {
			try {
				return String.valueOf(xssfRow.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(xssfRow.getRichStringCellValue());
			}
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

}

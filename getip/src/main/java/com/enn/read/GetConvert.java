package com.enn.read;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enn.vo.IpVo;

public class GetConvert {
	private static Log log = LogFactory.getLog(GetConvert.class);

	public static void main(String[] args) {
		String path = "F:\\qingcloud_excel";
			ReadExcel re = new ReadExcel();
			List<IpVo> readExcelContent = re.readExcelContent(path);
			System.out.println(readExcelContent);

	}

}

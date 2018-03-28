package com.enn.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enn.utils.CommonUtil;
import com.enn.utils.ConstantUtils;
import com.enn.utils.ReadUtil;
import com.enn.vo.IpVo;

/**
 * excel 读取
 * @author lxp
 * 2017年8月22日
 */
public class ReadExcel {
	private static Log log = LogFactory.getLog(ReadExcel.class);
	public List<IpVo> readExcelContent(String path){
		List<IpVo> total = new ArrayList<>();
		List<IpVo> r1 = new ArrayList<>();
		File dir = new File(path);
			if(dir.exists()){
				String[] list = dir.list();
				for (String f : list) {
					String p = path+"/"+f;
					 String postfix = CommonUtil.getPostfix(f);
			            if (!postfix.equals("")) {
			                	try {
			                		ReadUtil ru = new ReadUtil();
			                		 r1= ru.readXlsx(p,f);
			                		 //TODO  处理完一个excel应立即入库
			                		 total.addAll(r1);
			                		 r1.clear();
								} catch (Exception e) {
									log.error("读取excel异常 ： "+path,e);
								}
			                } else {
			            	log.error("文件格式不对..."+p);
			            }
				}
			}
		
		return total;
		
	}

}

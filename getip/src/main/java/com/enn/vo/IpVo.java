/**
 * 
 */
package com.enn.vo;

/**
 * @author lxp
 * 2017年8月23日
 */
public class IpVo {
	private String ip;
	private String status;
	private String sourceExcel;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSourceExcel() {
		return sourceExcel;
	}
	public void setSourceExcel(String sourceExcel) {
		this.sourceExcel = sourceExcel;
	}
	@Override
	public String toString() {
		return "IpVo [ip=" + ip + ", status=" + status + ", sourceExcel=" + sourceExcel + "]";
	}
	

}

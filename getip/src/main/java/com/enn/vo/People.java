package com.enn.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lxp
 * 2017年9月1日
 */
@Data
public  class People {
	@Setter@Getter
	private String name;
	private int age;

}

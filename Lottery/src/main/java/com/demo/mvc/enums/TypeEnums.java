package com.demo.mvc.enums;

/**
 * @author wubing
 *
 */
public enum TypeEnums {
	
	D3(0),assemble5(1);
	
	private final int code;
	
	TypeEnums(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

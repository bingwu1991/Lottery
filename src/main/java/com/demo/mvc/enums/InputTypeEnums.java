package com.demo.mvc.enums;

/**
 * @author wubing
 *
 */
public enum InputTypeEnums {

	first(1, "first"), second(2, "second"), last(3, "last"), four(4, "four"), five(5, "five");

	public final int code;
	public final String name;

	InputTypeEnums(int code, String name) {
		this.code = code;
		this.name = name;
	}
}

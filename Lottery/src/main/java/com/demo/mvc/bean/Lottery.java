package com.demo.mvc.bean;

import com.demo.mvc.enums.TypeEnums;

/**
 * lottery domain
 * 
 * @author wubing
 *
 */
public class Lottery {

	private Integer type;
	private Integer period;
	private Integer first;
	private Integer second;
	private Integer last;
	private Integer four;
	private Integer five;

	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Integer getType() {
		this.type = this.type == null ? TypeEnums.D3.getCode() : this.type;
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSecond() {
		return second;
	}
	public void setSecond(Integer second) {
		this.second = second;
	}
	public Integer getLast() {
		return last;
	}
	public void setLast(Integer last) {
		this.last = last;
	}
	public Integer getFour() {
		return four;
	}
	public void setFour(Integer four) {
		this.four = four;
	}
	public Integer getFive() {
		return five;
	}
	public void setFive(Integer five) {
		this.five = five;
	}
}

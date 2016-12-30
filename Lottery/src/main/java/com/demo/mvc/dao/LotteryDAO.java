package com.demo.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.mvc.bean.Lottery;

public interface LotteryDAO {

	/**
	 * insert one user info into DB
	 * 
	 * @param user
	 */
	public void add(Lottery user);

	/**
	 * delete specify user
	 * 
	 * @param user
	 * @param type
	 */
	public void delete(@Param("period") Integer period, @Param("type") Integer type);

	/**
	 * update specify user
	 * 
	 * @param lottery
	 */
	public void modify(Lottery lottery);

	/**
	 * query all lottery from DB
	 * 
	 * @param endPeriod
	 * @param startPeriod
	 * @param type
	 */
	public List<Lottery> fetch(@Param(value = "startPeriod") Integer startPeriod, @Param("endPeriod") Integer endPeriod,
			@Param("type") Integer type);

	/**
	 * query lottery by period
	 * 
	 * @param period
	 * @param type
	 * @return
	 */
	public Lottery fetchByPeriod(@Param("period") Integer period, @Param("type") Integer type);

	/**
	 * 
	 * query lottery by period count
	 * 
	 * @param count
	 * @param type
	 * @return
	 */
	public List<Lottery> fetchByCount(@Param("count") Integer count, @Param("type") Integer type);

}

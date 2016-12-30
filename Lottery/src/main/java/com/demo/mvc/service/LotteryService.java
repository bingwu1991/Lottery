package com.demo.mvc.service;

import java.util.List;

import com.demo.mvc.DTO.StatisticResult;
import com.demo.mvc.bean.Lottery;

public interface LotteryService {

	/**
	 * insert one lottery into DB
	 * 
	 * @param lottery
	 */
	public void add(Lottery lottery);

	/**
	 * delete specify lottery
	 * 
	 * @param period
	 * @param type
	 */
	public void delete(Integer period, Integer type);

	/**
	 * update specify lottery
	 * 
	 * @param lottery
	 */
	public void modify(Lottery lottery);

	/**
	 * query all history lottery from DB
	 * 
	 * @param endPeriod
	 * @param startPeriod
	 * @param type
	 * 
	 * @return
	 */
	public List<Lottery> fetch(Integer startPeriod, Integer endPeriod, Integer type);

	/**
	 * fetch latest n recorders
	 * 
	 * @param count
	 * @param type
	 * @return
	 */
	public List<Lottery> fetchByCount(Integer count, Integer type);

	/**
	 * analyse history data
	 * 
	 * @param count
	 * @param lottery
	 */
	public StatisticResult statistic(Integer count, Lottery lottery);

}

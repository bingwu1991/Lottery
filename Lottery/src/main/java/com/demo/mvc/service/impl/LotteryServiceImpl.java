package com.demo.mvc.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.mvc.DTO.StatisticResult;
import com.demo.mvc.bean.Lottery;
import com.demo.mvc.constants.InputTypeConstants;
import com.demo.mvc.dao.LotteryDAO;
import com.demo.mvc.enums.InputTypeEnums;
import com.demo.mvc.enums.TypeEnums;
import com.demo.mvc.service.LotteryService;
import com.demo.mvc.utils.MapValueComparator;

@Service("lotteryService")
public class LotteryServiceImpl implements LotteryService {

	@Resource
	private LotteryDAO lotteryDAO;

	@Override
	public void add(Lottery lottery) {
		if (lottery == null) {
			return;
		}
		if (lottery.getFirst() == null || lottery.getSecond() == null || lottery.getLast() == null) {
			return;
		}
		if (lottery.getType() == TypeEnums.assemble5.getCode()) {
			if (lottery.getFour() == null || lottery.getFirst() == null) {
				return;
			}
		}
		Lottery dbLottery = lotteryDAO.fetchByPeriod(lottery.getPeriod(), lottery.getType());
		if (dbLottery != null) {
			this.modify(lottery);
		} else {
			lotteryDAO.add(lottery);
		}
	}

	/**
	 * @see LotteryService#delete(Integer,Integer)
	 */
	@Override
	public void delete(Integer period, Integer type) {
		lotteryDAO.delete(period, type);
	}

	/**
	 * @see LotteryService#modify(Lottery)
	 */
	@Override
	public void modify(Lottery lottery) {
		lotteryDAO.modify(lottery);
	}

	/**
	 * @see LotteryService#fetch(Integer,Integer,Integer)
	 */
	@Override
	public List<Lottery> fetch(Integer startPeriod, Integer endPeriod, Integer type) {
		startPeriod = startPeriod == null ? 0 : startPeriod;
		endPeriod = endPeriod == null ? 0 : endPeriod;
		type = type == null ? TypeEnums.D3.getCode() : type;
		return lotteryDAO.fetch(startPeriod, endPeriod, type);
	}

	/**
	 * @see com.demo.mvc.service.LotteryService#fetchByCount(Integer,Integer)
	 */
	@Override
	public List<Lottery> fetchByCount(Integer count, Integer type) {
		type = type == null ? TypeEnums.D3.getCode() : type;
		List<Lottery> lotteries = lotteryDAO.fetchByCount(count, type);
		return lotteries;
	}

	/**
	 * @return
	 * @return
	 * @see com.demo.mvc.service.LotteryService#statistic(Integer, Integer,
	 *      Integer, Integer,Integer,Integer)
	 */
	@Override
	public StatisticResult statistic(Integer count, Lottery lottery) {
		List<Lottery> lotteries = this.fetchByCount(count, lottery.getType());
		Collections.reverse(lotteries);
		Integer type = lottery.getType();
		Map<String, Map<String, Integer>> firstResult = this.statistic(lotteries, lottery.getFirst(),
				InputTypeEnums.first.code, type);
		Map<String, Map<String, Integer>> secondResult = this.statistic(lotteries, lottery.getSecond(),
				InputTypeEnums.second.code, type);
		Map<String, Map<String, Integer>> lastResult = this.statistic(lotteries, lottery.getLast(),
				InputTypeEnums.last.code, type);

		Map<String, Map<String, Integer>> fourResult = null;
		Map<String, Map<String, Integer>> fiveResult = null;
		if (TypeEnums.assemble5.getCode() == lottery.getType()) {
			fourResult = this.statistic(lotteries, lottery.getFour(), InputTypeEnums.four.code, type);
			fiveResult = this.statistic(lotteries, lottery.getFive(), InputTypeEnums.five.code, type);
		}

		StatisticResult result = new StatisticResult(firstResult, secondResult, lastResult, fourResult, fiveResult);
		return result;
	}

	private Map<String, Map<String, Integer>> statistic(List<Lottery> lotteries, Integer inputValue, int inputType,
			Integer type) {

		int size = lotteries.size() - 1;
		Map<String, Integer> firstMatchedTime = new HashMap<String, Integer>();
		Map<String, Integer> secondMatchedTime = new HashMap<String, Integer>();
		Map<String, Integer> lastMatchedTime = new HashMap<String, Integer>();

		Map<String, Integer> fourMatchedTime = null;
		Map<String, Integer> fiveMatchedTime = null;
		if (TypeEnums.assemble5.getCode() == type) {
			fourMatchedTime = new HashMap<String, Integer>();
			fiveMatchedTime = new HashMap<String, Integer>();
		}
		for (int index = 0; index < size; index++) {
			Lottery lottery = lotteries.get(index);
			Lottery nextLottery = lotteries.get(index + 1);

			int value = -1;

			switch (inputType) {
				case InputTypeConstants.first :
					value = lottery.getFirst();
					break;
				case InputTypeConstants.second :
					value = lottery.getSecond();
					break;
				case InputTypeConstants.last :
					value = lottery.getLast();
					break;
				case InputTypeConstants.four :
					value = lottery.getFour();
					break;
				case InputTypeConstants.five :
					value = lottery.getFive();
					break;
				default :
					break;
			}

			if (inputValue == value) {
				sumMatchedTimes(firstMatchedTime, "a" + nextLottery.getFirst());
				sumMatchedTimes(secondMatchedTime, "a" + nextLottery.getSecond());
				sumMatchedTimes(lastMatchedTime, "a" + nextLottery.getLast());
				if (TypeEnums.assemble5.getCode() == type) {
					sumMatchedTimes(fourMatchedTime, "a" + nextLottery.getFour());
					sumMatchedTimes(fiveMatchedTime, "a" + nextLottery.getFive());
				}
			}
		}

		return this.assembleResult(firstMatchedTime, secondMatchedTime, lastMatchedTime, fourMatchedTime,
				fiveMatchedTime);
	}

	private Map<String, Map<String, Integer>> assembleResult(Map<String, Integer> first, Map<String, Integer> second,
			Map<String, Integer> last, Map<String, Integer> four, Map<String, Integer> five) {

		MapValueComparator firstCompare = new MapValueComparator(first);
		Map<String, Integer> treeFirst = new TreeMap<String, Integer>(firstCompare);
		treeFirst.putAll(first);

		MapValueComparator secondCompare = new MapValueComparator(second);
		Map<String, Integer> treeSecond = new TreeMap<String, Integer>(secondCompare);
		treeSecond.putAll(second);

		MapValueComparator lastCompare = new MapValueComparator(last);
		Map<String, Integer> treeLast = new TreeMap<String, Integer>(lastCompare);
		treeLast.putAll(last);

		Map<String, Map<String, Integer>> result = new HashMap<String, Map<String, Integer>>();
		result.put(InputTypeEnums.first.name, treeFirst);
		result.put(InputTypeEnums.second.name, treeSecond);
		result.put(InputTypeEnums.last.name, treeLast);

		if (four != null) {
			MapValueComparator fourCompare = new MapValueComparator(four);
			Map<String, Integer> treeFour = new TreeMap<String, Integer>(fourCompare);
			treeLast.putAll(four);
			result.put(InputTypeEnums.four.name, treeFour);
		}

		if (five != null) {
			MapValueComparator fiveCompare = new MapValueComparator(five);
			Map<String, Integer> treeFive = new TreeMap<String, Integer>(fiveCompare);
			treeLast.putAll(five);
			result.put(InputTypeEnums.five.name, treeFive);
		}

		return result;

	}

	private void sumMatchedTimes(Map<String, Integer> matchTimeMap, String matchedValue) {
		Integer times = matchTimeMap.get(matchedValue);
		times = times == null ? 1 : times + 1;
		matchTimeMap.put(matchedValue, times);
	}

}

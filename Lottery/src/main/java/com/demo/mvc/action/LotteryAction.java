package com.demo.mvc.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.demo.mvc.DTO.StatisticResult;
import com.demo.mvc.bean.Lottery;
import com.demo.mvc.service.LotteryService;

/**
 * lottery action
 * 
 * @author wubing
 *
 */
@Controller
@RequestMapping("/lottery")
public class LotteryAction {

	@Resource
	private LotteryService lotteryService;

	private static final Logger logger = Logger.getLogger(LotteryAction.class);

	/**
	 * statistic lottery data
	 * 
	 * @param count size of period
	 * @param first first number
	 * @param second second number
	 * @param last last number
	 * @return
	 */
	@RequestMapping("/statistic")
	@ResponseBody
	public Object statistic(Integer count, Lottery lottery) {
		logger.info("statistic");
		StatisticResult result = lotteryService.statistic(count, lottery);

		JSONObject json = new JSONObject();
		json.put("grandFirst", result.getFirstResult());
		json.put("grandSecond", result.getSecondResult());
		json.put("grandLast", result.getLastResult());
		json.put("grandFour", result.getFourResult());
		json.put("grandFive", result.getFiveResult());

		return json;
	}

	/**
	 * add period lottery data
	 * 
	 * @param period lottery period
	 * @param first first number
	 * @param second second number
	 * @param last last number
	 * @return
	 */
	@RequestMapping("/addPeriod")
	@ResponseBody
	public String addPeriod(Lottery lottery) {
		lotteryService.add(lottery);
		return "";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Integer period,Integer type) {
		lotteryService.delete(period,type);
		return "";
	}

	/**
	 * query all history data
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<Lottery> query(@RequestParam(required = false) Integer startPeriod,
			@RequestParam(required = false) Integer endPeriod,Integer type) {
		List<Lottery> lotteries = lotteryService.fetch(startPeriod, endPeriod,type);
		return lotteries;
	}

}

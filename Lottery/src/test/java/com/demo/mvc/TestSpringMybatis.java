package com.demo.mvc;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.mvc.bean.Lottery;
import com.demo.mvc.service.LotteryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestSpringMybatis {

	private static Logger logger = Logger.getLogger(TestSpringMybatis.class);

	@Resource
	private LotteryService userService;

	@Test
	public void TestAdd() {
		Lottery lottery = new Lottery();
		lottery.setPeriod(2016332);
		lottery.setFirst(1);
		lottery.setSecond(2);
		lottery.setLast(3);
		userService.add(lottery);
		logger.info("test junit");
	}
}
